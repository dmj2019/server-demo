package opg.dmj.server.service;

import com.google.gson.Gson;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import opg.dmj.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 杜梦嘉
 * @Date: 2019-06-13-14:14
 */
@Component
public class UserAsyncServiceImpl implements UserAsyncService {
    @Autowired
    private UserService userService;

    @Override
    public void save(JsonObject reqParam, Handler<AsyncResult<JsonObject>> resultHandler) {
        User user = new User(reqParam);
        JsonObject json = userService.save(user).toJson();
        JsonObject retJson = new JsonObject().put("result", 0).put("data", json);
        Future.succeededFuture(retJson).setHandler(resultHandler);
    }

    @Override
    public void list(Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonArray json = new JsonArray(new Gson().toJson(userService.list()));
        JsonObject retJson = new JsonObject().put("result", 0).put("data", json);
        Future.succeededFuture(retJson).setHandler(resultHandler);
    }
}
