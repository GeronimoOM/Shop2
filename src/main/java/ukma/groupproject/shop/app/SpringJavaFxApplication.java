package ukma.groupproject.shop.app;

import javafx.application.Application;
import org.springframework.context.ApplicationContext;

public abstract class SpringJavaFxApplication extends Application {

    protected abstract ApplicationContext getApplicationContext();

    @Override
    public void init() throws Exception {
        getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
    }
}
