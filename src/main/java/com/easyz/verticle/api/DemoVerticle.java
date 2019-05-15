package com.easyz.verticle.api;

import com.easyz.util.ResponseMessage;
import com.easyz.util.VertxResponce;
import com.easyz.verticle.dao.DemoDaoVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * @author z
 * @date 2019/1/17 10:52
 */
public class DemoVerticle extends BaseApiVerticle {

  @Override
  public void start() throws Exception {
    super.start();
  }

  @Override
  public Router requestApi(){
    //配置路由，并且设置处理器
    Router sub_router = Router.router(vertx);
    sub_router.route("/demo").handler(this::demoRequest);
    return sub_router;
  }

  private void demoRequest(RoutingContext rc){
    System.out.println("get in demo request verticle");
    eventBus.send(getDaoConsumerPath(DemoDaoVerticle.class, "demo"), "hello ! Dj!", res -> {
      System.out.println("res body:"+res.toString());
      if(res.succeeded()){
        VertxResponce.endJson(rc, ResponseMessage.success().pushData("result" , res.result().body()));

      }
      //rc.next();
    });

  }

  /**
   * 静态资源访问路径.
   * emmmmmmm.....  测试无效。 懒得调整了
   * @return
   */
  @Deprecated
  private Router mainRouter(){
    Router mainRouter = Router.router(vertx);
    mainRouter.route("/static/*").handler(StaticHandler.create());
    mainRouter.route("/").handler(rc -> rc.reroute("/static/index.html"));
    return mainRouter;
  }

}
