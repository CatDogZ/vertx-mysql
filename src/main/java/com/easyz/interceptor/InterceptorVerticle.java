package com.easyz.interceptor;

import com.easyz.util.Constant;
import com.easyz.verticle.api.BaseApiVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class InterceptorVerticle extends BaseApiVerticle {


  @Override
  public Router requestApi() {
    Router router = Router.router(vertx);
    Route route = router.route().order(-1).handler(routingContext -> {
      System.out.println("##########get in ter ");
      routingContext.next();
    });
    return router;
  }

  public void interceptorHandler(RoutingContext routingContext){
    HttpServerRequest request = routingContext.request();
    System.out.println("get in interceptor, param is:"+request.getParam("param"));
    routingContext.next();
  }
}
