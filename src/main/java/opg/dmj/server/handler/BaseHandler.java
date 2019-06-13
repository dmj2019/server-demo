package opg.dmj.server.handler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-22:04
 */
public class BaseHandler {

    Vertx vertx;
    Router router;

    public BaseHandler(Vertx vertx) {
        this.vertx = vertx;
        router = Router.router(vertx);
        router.route().handler(BodyHandler.create())
                .consumes("application/json")
                .produces("application/json");
    }

    protected void callBack(AsyncResult<JsonObject> ret, RoutingContext routingContext) {
        if (ret.succeeded()) {
            JsonObject result = ret.result();
            routingContext.response().setStatusCode(result.getInteger("statusCode")).end(result.encodePrettily());
        } else {
            routingContext.fail(ret.cause());
        }
    }
}
