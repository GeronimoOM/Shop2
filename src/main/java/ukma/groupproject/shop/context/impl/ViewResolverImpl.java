package ukma.groupproject.shop.context.impl;

import org.springframework.stereotype.Component;
import ukma.groupproject.shop.context.View;
import ukma.groupproject.shop.context.ViewResolver;

import java.net.URL;

@Component
public class ViewResolverImpl implements ViewResolver{

    private static String PREFIX = "views/";
    private static String EXT = ".fxml";

    @Override
    public View resolve(String name) {
        String urlPath = PREFIX + name + EXT;
        URL url =  ViewResolverImpl.class.getClassLoader().getResource(urlPath);
        if(url == null) {
            throw new IllegalStateException("View has invalid URL: " + urlPath);
        }
        return new View(name, url);
    }

}
