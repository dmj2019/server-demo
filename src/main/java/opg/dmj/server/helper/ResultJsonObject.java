package opg.dmj.server.helper;

import com.google.gson.Gson;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

/**
 * @Description: 封装返回的json对象
 * @Author: 尉宇晚临江·鹧鸪天
 * @Date: 2019-06-13-20:41
 */
public class ResultJsonObject {

    private static Gson gson = new Gson();

    public static JsonObject createJsonResult(Object data, int statusCode, String message) {
        JsonObject dataJson = new JsonObject(gson.toJson(data));
        return new JsonObject().put("data", dataJson).put("statusCode", statusCode).put("message", message);
    }

    public static JsonObject createJsonResult(List data, int statusCode, String message) {
        JsonArray jsonArray = new JsonArray(gson.toJson(data));
        return new JsonObject().put("data", jsonArray).put("statusCode", statusCode).put("message", message);
    }

    public static JsonObject createJsonResult(int statusCode, String message) {
        return new JsonObject().put("statusCode", statusCode).put("message", message);
    }
}
