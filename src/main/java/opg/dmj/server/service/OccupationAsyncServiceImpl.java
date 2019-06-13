package opg.dmj.server.service;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import opg.dmj.server.helper.ResultJsonObject;
import opg.dmj.server.model.Occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-21:50
 */
@Component
public class OccupationAsyncServiceImpl implements OccupationAsyncService {

    @Autowired
    private OccupationService occupationService;

    @Override
    public void save(JsonObject reqParam, Handler<AsyncResult<JsonObject>> resultHandler) {
        Occupation occupation = occupationService.save(new Occupation(reqParam));
        JsonObject retJson = ResultJsonObject.createJsonResult(occupation, HttpResponseStatus.OK.code(), "Save Succeed");
        Future.succeededFuture(retJson).setHandler(resultHandler);
    }

    @Override
    public void list(Handler<AsyncResult<JsonObject>> resultHandler) {
        List<Occupation> occupations = occupationService.list();
        JsonObject retJson = ResultJsonObject.createJsonResult(occupations, HttpResponseStatus.OK.code(), "");
        Future.succeededFuture(retJson).setHandler(resultHandler);
    }
}
