package opg.dmj.server.model;

import com.google.gson.Gson;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description:
 * @Author: 杜梦嘉
 * @Date: 2019-06-13-13:50
 */
@Entity
@Table(name = "t_user")
@DataObject(generateConverter = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    /**
     * Spring Data Jpa 支持的实体类需要一个空的构造函数
     */
    public User() {
    }

    /**
     * Vert.x@DataObject注解支持的类下需要包含JsonObject为参数的构造函数
     *
     * @param json
     */
    public User(JsonObject json) {
        Objects.requireNonNull(json);
        User user = json.mapTo(this.getClass());
        this.id = user.id;
        this.name = user.name;
    }

    /**
     * Vert.x@DataObject注解支持的类下需要一个toJson方法
     *
     * @return
     */
    public JsonObject toJson() {
        return new JsonObject(new Gson().toJson(this));
    }

    /**
     * 不要使用任何lombok方法生成get/set方法
     *
     * @return
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
