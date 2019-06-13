package opg.dmj.server.handler;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import opg.dmj.server.service.OccupationAsyncService;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:28
 */
public class OccupationHandler extends BaseHandler {
    private OccupationAsyncService occupationAsyncService;

    public OccupationHandler(Vertx vertx, OccupationAsyncService occupationAsyncService) {
        super(vertx);
        this.occupationAsyncService = occupationAsyncService;
    }

    public Router createRouter() {
        router.post("/").handler(this::save);
        router.get("/").handler(this::list);
        return router;
    }

    public void save(RoutingContext routingContext) {
        JsonObject reqParam = routingContext.getBodyAsJson();
        occupationAsyncService.save(reqParam, ar -> callBack(ar, routingContext));
    }

    public void list(RoutingContext routingContext) {
        occupationAsyncService.list(ar -> callBack(ar, routingContext));
    }
}
