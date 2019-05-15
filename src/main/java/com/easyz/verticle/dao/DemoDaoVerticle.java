package com.easyz.verticle.dao;

import com.easyz.util.JsonUtil;
import com.easyz.util.VertxResponce;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author z
 * @date 2019/1/23 11:25
 */
public class DemoDaoVerticle extends BaseDaoVerticle{


  public static final Logger logger = Logger.getLogger("DemoDaoVerticle");

  @Override
  public void start() throws Exception {
    super.start();
//    MessageConsumer<List<JsonObject>> consumer = eventBus.consumer(getDaoConsumerPath(this.getClass(), "demo"));
//    consumer.handler(msg -> busDemo(msg));
    eventBus.consumer(getDaoConsumerPath(this.getClass(), "demo")).handler(this::busDemo);
  }

  public void busDemo(Message message){
    logger.severe("dao rev message1 :" + message.body());
    mysqlClient.query("select * from test ", res -> {
      if(res.succeeded()){
        logger.severe("success!!!!!!!:");
        message.reply(JsonUtil.listToJsonArray(res.result().getRows()));
      }else{
        logger.severe("fail");
        System.out.println(res.cause());
      }
    });


    //    System.out.println("rec fans msg:"+message.body());
//    JsonObject jsonObject = new JsonObject()
//      .put("key", "demokey")
//      .put("value", "demovalue");
//    mongoClient.save("demotable", jsonObject, res -> {
//      if(res.failed())
//        message.reply("保存失败哦");
//      else
//        message.reply("保存成功了哦:"+res.result());
//    });
  }
}
