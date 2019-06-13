package opg.dmj.server.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-21:47
 */
@ProxyGen
public interface OccupationAsyncService {
    String ADDRESS = OccupationAsyncService.class.getName();

    void save(JsonObject reqParam, Handler<AsyncResult<JsonObject>> resultHandler);

    void list(Handler<AsyncResult<JsonObject>> resultHandler);
}
