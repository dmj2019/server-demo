package opg.dmj.server.service;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import opg.dmj.server.helper.ResultJsonObject;
import opg.dmj.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-14:14
 */
@Component
public class UserAsyncServiceImpl implements UserAsyncService {
    @Autowired
    private UserService userService;

    @Override
    public void save(JsonObject reqParam, Handler<AsyncResult<JsonObject>> resultHandler) {
        User user = userService.save(new User(reqParam));
        JsonObject retJson = ResultJsonObject.createJsonResult(user, HttpResponseStatus.OK.code(), "Save Succeed");
        Future.succeededFuture(retJson).setHandler(resultHandler);
    }

    @Override
    public void list(Handler<AsyncResult<JsonObject>> resultHandler) {
        List<User> users = userService.list();
        JsonObject retJson = ResultJsonObject.createJsonResult(users, HttpResponseStatus.OK.code(), "");
        Future.succeededFuture(retJson).setHandler(resultHandler);
    }

    @Override
    public void get(Long id, Handler<AsyncResult<JsonObject>> resultHandler) {
        User user = userService.get(id);
        JsonObject retJson = ResultJsonObject.createJsonResult(user, HttpResponseStatus.OK.code(), "");
        Future.succeededFuture(retJson).setHandler(resultHandler);
    }

    @Override
    public void delete(Long id, Handler<AsyncResult<JsonObject>> resultHandler) {
        userService.delete(id);
        JsonObject retJson = ResultJsonObject.createJsonResult(HttpResponseStatus.OK.code(), "Delete Succeed");
        Future.succeededFuture(retJson).setHandler(resultHandler);
    }
}
