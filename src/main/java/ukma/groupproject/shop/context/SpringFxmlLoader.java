package ukma.groupproject.shop.context;

import ukma.groupproject.shop.controller.Controller;

public interface SpringFxmlLoader {

    Controller load(View view);

    Controller load(String view);
}
