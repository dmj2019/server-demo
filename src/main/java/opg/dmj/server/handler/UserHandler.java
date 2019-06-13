package opg.dmj.server.handler;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import opg.dmj.server.service.UserAsyncService;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:28
 */
public class UserHandler extends BaseHandler {
    private UserAsyncService userAsyncService;

    public UserHandler(Vertx vertx, UserAsyncService userAsyncService) {
        super(vertx);
        this.userAsyncService = userAsyncService;
    }

    public Router createRouter() {
        router.post("/").handler(this::save);
        router.get("/").handler(this::list);
        router.get("/:id").handler(this::get);
        router.delete("/:id").handler(this::delete);
        return router;
    }

    public void save(RoutingContext routingContext) {
        JsonObject reqParam = routingContext.getBodyAsJson();
        userAsyncService.save(reqParam, ar -> callBack(ar, routingContext));
    }

    public void list(RoutingContext routingContext) {
        userAsyncService.list(ar -> callBack(ar, routingContext));
    }

    public void get(RoutingContext routingContext) {
        Long id = Long.parseLong(routingContext.request().getParam("id"));
        userAsyncService.get(id, ar -> callBack(ar, routingContext));
    }

    public void delete(RoutingContext routingContext) {
        Long id = Long.parseLong(routingContext.request().getParam("id"));
        userAsyncService.delete(id, ar -> callBack(ar, routingContext));
    }

}
