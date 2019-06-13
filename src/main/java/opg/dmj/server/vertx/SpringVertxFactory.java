package opg.dmj.server.vertx;

import io.vertx.core.Verticle;
import io.vertx.core.spi.VerticleFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 杜梦嘉
 * @Date: 2019-06-13-14:43
 */
@Component
public class SpringVertxFactory implements VerticleFactory, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public boolean blockingCreate() {
        return true;
    }

    @Override
    public String prefix() {
        return "server-demo";
    }

    @Override
    public Verticle createVerticle(String verticleName, ClassLoader classLoader) throws Exception {
        String clazz = VerticleFactory.removePrefix(verticleName);
        return (Verticle) applicationContext.getBean(Class.forName(clazz));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
