package opg.dmj.server;

import io.vertx.core.json.JsonObject;
import opg.dmj.server.model.User;
import opg.dmj.server.service.UserAsyncService;
import opg.dmj.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Autowired
    UserAsyncService userAsyncService;
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setId(1);
        user.setName("111");

        System.out.println(user.toJson().encodePrettily());
/*        userAsyncService.list(ret -> {
            if (ret.succeeded()) {
                System.out.println(ret.result());
            } else {
                ret.failed();
            }
        });*/
    }

}
