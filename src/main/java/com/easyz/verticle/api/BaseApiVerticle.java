package com.easyz.verticle.api;

import com.easyz.util.Constant;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * 所有verticle的父类。要是新建的verticle，请继承这个类
 * @author z
 * @date 2019/1/24 18:15
 */
public abstract class BaseApiVerticle extends AbstractVerticle {

  protected static Router router;
  private static HttpServer httpServer;
  protected static EventBus eventBus;

  private EventBus getEventBus(){
    if(eventBus == null) return vertx.eventBus();
    return eventBus;
  }

  /**
   * 获取主路由
   * @return
   */
  private Router getMainRouter(){
    if(router == null){
      return Router.router(vertx);
    }
    return router;
  }

  /**
   * 获取httpserver对象
   * @return
   */
  private HttpServer getHttpServerInstance(){
    if(httpServer == null){
      return vertx.createHttpServer();
    }
    return httpServer;
  }

  /**
   * 配置项目根目录路径，集成子路由
   * @param router
   * @param subRouter
   */
  private void mountSubRouter(Router router, Router subRouter){
    if(subRouter != null){
      router.mountSubRouter(Constant.ROOT_HEAD_PATH, subRouter);
    }
  }

  @Override
  public void start() throws Exception {
    //初始化主路由
    router = getMainRouter();
    //初始化eventbus
    eventBus = getEventBus();
    //配置访问项目根路径，并且集成二级路由
    mountSubRouter(router, requestApi());
    //获取配置信息
    JsonObject conf = config();
    //创建httpserver
    httpServer = getHttpServerInstance();
    //初始化服务并监听端口
    httpServer.requestHandler(router::accept).listen(conf.getInteger(Constant.SERVER_PORT));
  }

  /**
   * 获取eventbus的注册地址。
   * @param clazz   类的全类名
   * @param methodPath  方法入口
   * @return
   */
  public String getDaoConsumerPath(Class clazz, String methodPath){
    return clazz.getName() +"."+methodPath;
  }


  /**
   * router请求子路由。
   * @return
   */
  public abstract Router requestApi();
}
