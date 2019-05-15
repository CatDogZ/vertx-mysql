package com.easyz.util;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class JsonUtil {


  /**
   * 将list转换成jsonarray
   * @param list
   * @return
   */
  public static JsonArray listToJsonArray(List<JsonObject> list){
     return new JsonArray(list).copy();
  }
}
