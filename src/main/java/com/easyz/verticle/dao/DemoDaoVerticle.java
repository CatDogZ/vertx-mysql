package com.easyz.verticle.dao;

import com.easyz.util.JsonUtil;
import com.easyz.util.VertxResponce;
import com.google.gson.Gson;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonArray;
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
    eventBus.consumer(getDaoConsumerPath(this.getClass(), "demoQueryById")).handler(this::demoQueryById);
    eventBus.consumer(getDaoConsumerPath(this.getClass(), "demoInsertByJson")).handler(this::demoInsertByJson);
  }


  public void demoInsertByJson(Message message){
    JsonArray params = new JsonArray().add(message.body());
    mysqlClient.updateWithParams("insert into test_temp(name) values(?)", params, res -> {
      if(res.succeeded()){
        System.out.println("add succ key:"+res.result().getKeys());
        message.reply(res.result().getKeys());
      }else{
        System.out.println("insert into test temp fail:"+res.cause());
      }
    });
  }



  public void demoQueryById(Message message){
    JsonArray jsonArray = new JsonArray().add(message.body());
    logger.severe("dao rev message1 :" + message.body()+"  json param:"+jsonArray.toString());
    mysqlClient.queryWithParams("select * from user where id = ?", jsonArray, res -> {
      if(res.succeeded()){
        logger.severe("success!!!!!!!:"+res.result().getRows());
        message.reply(JsonUtil.listToJsonArray(res.result().getRows()));
      }else{
        logger.severe("fail");
        System.out.println(res.cause());
      }
    });


  }
}
