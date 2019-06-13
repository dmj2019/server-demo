package opg.dmj.server.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @Description:
 * @Author: 杜梦嘉
 * @Date: 2019-06-13-14:13
 */
@ProxyGen
public interface UserAsyncService {
    String ADDRESS = UserAsyncService.class.getName();

    void save(JsonObject reqParam, Handler<AsyncResult<JsonObject>> resultHandler);

    void list(Handler<AsyncResult<JsonObject>> resultHandler);
}
