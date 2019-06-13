package opg.dmj.server;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import opg.dmj.server.vertx.SpringVertxFactory;
import opg.dmj.server.vertx.VertxFacade;
import opg.dmj.server.vertx.WorkerVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author 杜梦嘉
 */
@SpringBootApplication
public class ServerApplication {

    @Autowired
    private SpringVertxFactory springVertxFactory;

    @Value("${vertx.worker.pool.size}")
    int workerPoolSize;

    @Value("${vertx.springWorker.instances}")
    int springWorkerInstances;

    @Value("${vertx.max.eventloop.execute.time}")
    int maxEventLoopExecuteTime;

    @Value("${vertx.blocked.threa.check.interval}")
    int blockedThreadCheckInterval;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @EventListener
    public void deployVerticles(ApplicationReadyEvent event) {
        VertxOptions options = new VertxOptions()
                .setWorkerPoolSize(workerPoolSize)
                .setBlockedThreadCheckInterval(blockedThreadCheckInterval)
                .setMaxEventLoopExecuteTime(maxEventLoopExecuteTime);

        Vertx vertx = Vertx.vertx(options);
        vertx.registerVerticleFactory(springVertxFactory);

        CountDownLatch deployLatch = new CountDownLatch(2);
        AtomicBoolean failed = new AtomicBoolean(false);

        String vertxController = springVertxFactory.prefix() + ":" + VertxFacade.class.getName();

        vertx.deployVerticle(vertxController, res -> {
            if (res.failed()) {
                failed.compareAndSet(false, true);
            }
            deployLatch.countDown();
        });

        DeploymentOptions workerDeployOpt = new DeploymentOptions().setWorker(true).setInstances(springWorkerInstances);
        String vertxWorker = springVertxFactory.prefix() + ":" + WorkerVerticle.class.getName();

        vertx.deployVerticle(vertxWorker, workerDeployOpt, res -> {
            if (res.succeeded()) {
                deployLatch.countDown();
            } else {
                failed.compareAndSet(false, true);
            }
            deployLatch.countDown();
        });

        try {
            if (!deployLatch.await(5, SECONDS)) {
                throw new RuntimeException("Timeout waiting for verticle deployments");
            } else if (failed.get()) {
                throw new RuntimeException("Failure while deploying verticles");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
