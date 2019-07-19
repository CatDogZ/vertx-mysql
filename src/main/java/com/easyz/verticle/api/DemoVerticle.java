package com.easyz.verticle.api;

import com.easyz.util.ResponseMessage;
import com.easyz.util.VertxResponce;
import com.easyz.verticle.dao.DemoDaoVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
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
    sub_router.route("/demoInsertByJson").handler(this::demoInsertByJson);
    sub_router.route("/demo/:catalogue/:name").handler(this::demoAcceptUrlParam);
    sub_router.route("/demoAcceptUrlQueryParam").handler(this::demoAcceptUrlQueryParam);
    sub_router.route("/demoAcceptRequetBody").handler(this::demoAcceptRequetBody);
    sub_router.route("/demoAcceptFormData").handler(this::demoAcceptFormData);
    return sub_router;
  }

  private void demoAcceptFormData(RoutingContext rc){
    HttpServerRequest request = rc.request();
    //设置为true的时候， 下方的endhandler中才能读取表单属性
    request.setExpectMultipart(true);
    request.endHandler(v -> {
      // 请求体被完全读取，所以直接读取表单属性
      System.out.println("接收表单信息，formAttributes:"+request.formAttributes().get("chengshi"));
    });
    VertxResponce.endJson(rc, ResponseMessage.success());
  }

  private void demoAcceptRequetBody(RoutingContext rc){
    rc.request().bodyHandler(buffer -> {
      if(buffer.length() != 0 ){
        System.out.println("适用于请求头CONTENT是application/json的方式，参数格式为json的，但是参数为空的时候, 会报错。 使用前 先判断一下长度为不为0："+buffer.toJsonObject());
      }
    });
    VertxResponce.endJson(rc, ResponseMessage.success());
  }

  private void demoAcceptUrlQueryParam(RoutingContext rc){
    System.out.println("接受前端页面传参的方式(结果为问号后面这串，例：name=xiaolizi&sex=nan:"+rc.request().query());
    System.out.println("也可以直接这样接收:"+rc.request().getParam("name"));
    VertxResponce.endJson(rc, ResponseMessage.success());
  }

  private void demoAcceptUrlParam(RoutingContext rc){
    System.out.println("用占位符的方式，获取路径里面的参数：catalogue:"+rc.request().getParam("catalogue")+" name:"+rc.request().getParam("name"));
    VertxResponce.endJson(rc, ResponseMessage.success());
  }

  private void demoInsertByJson(RoutingContext rc){
    System.out.println("往数据库插入一条demo信息");
    eventBus.send(getDaoConsumerPath(DemoDaoVerticle.class, "demoInsertByJson"), "xiaozhupeiqi", res -> {
      System.out.println("res body:"+res.toString());
      if(res.succeeded()){
        VertxResponce.endJson(rc, ResponseMessage.success().pushData("result" , res.result().body()));
      }
    });
  }

  private void demoRequest(RoutingContext rc){
    System.out.println("查询id为1的user信息");
    eventBus.send(getDaoConsumerPath(DemoDaoVerticle.class, "demoQueryById"), 1, res -> {
      System.out.println("res body:"+res.toString());
      if(res.succeeded()){
        VertxResponce.endJson(rc, ResponseMessage.success().pushData("result" , res.result().body()));
      }
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
