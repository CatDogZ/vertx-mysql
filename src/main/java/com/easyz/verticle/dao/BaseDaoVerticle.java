package com.easyz.verticle.dao;

import com.easyz.util.MysqlDbUtils;
import com.easyz.verticle.api.BaseApiVerticle;
import com.google.gson.Gson;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.web.Router;

import java.util.List;

/**
 * @author z
 * @date 2019/1/23 11:34
 */
public abstract class BaseDaoVerticle extends BaseApiVerticle{


  protected AsyncSQLClient mysqlClient;

  protected EventBus eventBus;
  private static  Gson gson = new Gson();


  @Override
  public Router requestApi() {
    return null;
  }

  @Override
  public void start() throws Exception {
    super.start();
    eventBus = vertx.eventBus();
    mysqlClient = MysqlDbUtils.getAsynMysqlClientInstance(vertx, config().getJsonObject("mysqlconfig"));
  }

}
