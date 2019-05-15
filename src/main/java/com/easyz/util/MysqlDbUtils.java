package com.easyz.util;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;

public class MysqlDbUtils {

    private static AsyncSQLClient mySQLClient;


  /**
   * 获取mysqlclient对象
   * @param vertx
   * @param jsonObject
   * @return
   */
    public static AsyncSQLClient getAsynMysqlClientInstance(Vertx vertx, JsonObject jsonObject){
      if(mySQLClient == null){
        mySQLClient = MySQLClient.createShared(vertx, jsonObject);
        return mySQLClient;
      }
      return mySQLClient;
    }

}
