package opg.dmj.server.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.serviceproxy.ServiceProxyBuilder;
import opg.dmj.server.handler.UserHandler;
import opg.dmj.server.service.UserAsyncService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:39
 */
@Component
public class VertxFacade extends AbstractVerticle {

    @Value("${vertx.port}")
    private int vertxPort;
    private UserAsyncService userAsyncService;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        userAsyncService = new ServiceProxyBuilder(vertx).setAddress(UserAsyncService.ADDRESS).build(UserAsyncService.class);

        startServer(
                (http) -> completeStartup(http, startFuture)
        );
    }

    private void startServer(Handler<AsyncResult<HttpServer>> next) {
        Router apiRouter = Router.router(vertx);
        apiRouter.mountSubRouter("/user", new UserHandler(vertx, userAsyncService).createRouter());
        vertx.createHttpServer()
                .requestHandler(apiRouter)
                .listen(vertxPort, next::handle);
    }

    private void completeStartup(AsyncResult<HttpServer> http, Future<Void> future) {
        if (http.succeeded()) {
            future.complete();
        } else {
            future.fail(http.cause());
        }
    }
}
