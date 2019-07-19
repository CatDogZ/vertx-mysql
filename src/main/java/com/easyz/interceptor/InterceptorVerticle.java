package com.easyz.interceptor;

import com.easyz.verticle.api.BaseApiVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;

/**
 * 拦截器verticle
 */
public class InterceptorVerticle extends BaseApiVerticle {


  @Override
  public Router requestApi() {
    Router router = Router.router(vertx);
    router.route().order(-1).handler(this::interceptorHandler);
   // router.route().handler(CorsHandler.create("*"));
    return router;
  }

  public void interceptorHandler(RoutingContext routingContext){
    HttpServerRequest request = routingContext.request();
    System.out.println("get in interceptor, param is:"+request.getParam("catalogue"));
    routingContext.next();
  }
}
