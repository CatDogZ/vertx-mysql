package com.easyz.verticle.api;

import com.easyz.util.ResponseMessage;
import com.easyz.util.VertxResponce;
import com.easyz.verticle.dao.DemoDaoVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class DemoTwoVerticle extends BaseApiVerticle{


  @Override
  public Router requestApi() {
    Router sub = Router.router(vertx);
    sub.route("/demoTwo").handler(this::demoRequest);
    return sub;
  }



  private void demoRequest(RoutingContext rc){
    System.out.println("get in demo request verticle");
    eventBus.send(getDaoConsumerPath(DemoDaoVerticle.class, "demo"), "hello ! Dj!", res -> {
      System.out.println("res body:"+res.toString());
      if(res.succeeded()){
        VertxResponce.endJson(rc, ResponseMessage.success().pushData("result" , res.result().body()));

      }
    });

  }
}
