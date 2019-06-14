package opg.dmj.server.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;
import opg.dmj.server.service.OccupationAsyncService;
import opg.dmj.server.service.UserAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:38
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class OccupationVerticle extends AbstractVerticle {

    @Autowired
    OccupationAsyncService occupationAsyncService;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        new ServiceBinder(vertx).setAddress(OccupationAsyncService.ADDRESS).register(OccupationAsyncService.class, occupationAsyncService).completionHandler(ar -> {
            if (ar.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(ar.cause());
            }
        });
    }
}
