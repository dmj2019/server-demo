package opg.dmj.server.handler;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import opg.dmj.server.service.UserAsyncService;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:28
 */
public class UserHandler {
    private Vertx vertx;
    private UserAsyncService userAsyncService;

    public UserHandler(Vertx vertx, UserAsyncService userAsyncService) {
        this.vertx = vertx;
        this.userAsyncService = userAsyncService;
    }

    public Router createRouter() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create())
                .consumes("application/json")
                .produces("application/json");
        router.post("/").handler(this::save);
        router.get("/").handler(this::list);
        return router;
    }

    public void save(RoutingContext routingContext) {
        JsonObject reqParam = routingContext.getBodyAsJson();
        userAsyncService.save(reqParam, ar -> {
            if (ar.succeeded()) {
                JsonObject result = ar.result();
                routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end(result.encodePrettily());
            } else {
                routingContext.fail(ar.cause());
            }
        });
    }

    public void list(RoutingContext routingContext) {
        userAsyncService.list(ar -> {
            if (ar.succeeded()) {
                JsonObject result = ar.result();
                routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end(result.encodePrettily());
            } else {
                routingContext.fail(ar.cause());
            }
        });
    }
}
