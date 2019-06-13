package opg.dmj.server.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * @Description: 编译时会在classes文件夹下生成AsyncServiceVertxEBProxy.class和AsyncServiceVertxProxyHandler.class文件, 当这里新增函数时如果没有更新这两个文件则会出现莫名其妙的异常
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:13
 */
@ProxyGen
public interface UserAsyncService {
    String ADDRESS = UserAsyncService.class.getName();

    void save(JsonObject reqParam, Handler<AsyncResult<JsonObject>> resultHandler);

    void list(Handler<AsyncResult<JsonObject>> resultHandler);

    void get(Long id, Handler<AsyncResult<JsonObject>> resultHandler);

    void delete(Long id, Handler<AsyncResult<JsonObject>> resultHandler);
}
