package com.easyz.util;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Created by z on 2018/4/10.
 */
public class MongoDbTools {

//    private static MongoClient mongoClient;
//    private MongoDbTools(){}
//
//    public static MongoClient getInstanceClient(Vertx vertx, JsonObject mongoconfig){
//
//        if(mongoClient == null){
//            mongoClient = MongoClient.createShared(vertx, mongoconfig);
//            return mongoClient;
//        }
//        return mongoClient;
//    }
//
//    public static Class initClient(Vertx vertx, JsonObject mongoconfig){
//        if(mongoClient == null){
//            mongoClient = MongoClient.createShared(vertx, mongoconfig);
//        }
//        return MongoDbTools.class;
//    }
//
//
//    /**
//     * 多个jsonobject组成jsonarray并且返回。
//     * 如果参数列表为null， 或长度为0，返回一个空的数组
//     * @param jsonObjects
//     * @return jsonArray
//     */
//    public static JsonArray getArrayFromEach(JsonObject... jsonObjects){
//        if(jsonObjects == null || jsonObjects.length == 0) return new JsonArray();
//        JsonArray jsonArray = new JsonArray();
//        for(JsonObject jsonObject : jsonObjects){
//                jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
//
//    /**
//     * 获取or查询条件.
//     * 传入需要查询的or条件，返回封装好的对象。如果参数为null或者长度为0，返回一个空的$or对象
//     * 最终返回格式: {"$or":[{"paramA":AVALUE},{"paramB":BVALUE}]}
//     * @param jsonObjects
//     * @return
//     */
//    public static JsonObject getOrCond(JsonObject... jsonObjects){
//        if(jsonObjects == null || jsonObjects.length == 0) return new JsonObject().put("$or", new JsonArray());
//        JsonObject or = new JsonObject();
//        return or.put("$or", getArrayFromEach(jsonObjects));
//    }
//
//    /**
//     * 获取条件匹配match查询条件.
//     * 如果参数为空， 返回一个新的jsonobject
//     * 返回格式: {"$match": {...}}
//     * @param jsonObject
//     * @return
//     */
//    public static JsonObject getMatchCond(JsonObject jsonObject){
//        if(jsonObject == null) return new JsonObject();
//        return new JsonObject().put("$match", jsonObject);
//    }
//
//    /**
//     * 根据or条件，封装match条件匹配全对象
//     * 传入or条件中的所有可选对象，返回最终所需要的查询对象match
//     * 最终返回格式:{"$match":{"$or":[...]}}
//     * @param ors
//     * @return
//     */
//    public static JsonObject getMatchCondByOrCond(JsonObject... ors){
//        if(ors == null || ors.length == 0) return new JsonObject().put("$match", new JsonObject());
//        return getMatchCond(getOrCond(ors));
//    }
//
//
//    public static JsonObject getIdJsonObject(JsonObject jsonObject){
//       return new JsonObject().put("_id", jsonObject);
//    }
//
//    /**
//     * 根据分组查询数量(count)/求和(sum)
//     * @param statisticValue 需要求和的字段。如果要查询数量(count), 传入1即可
//     * @param statisticAlias 统计结果的别名。
//     * @param groupKeys json对象，存分组的key,可支持按多个字段进行分组
//     * @return {"$group":{"_id": groupKeys, "statisticAlias": {"$sum":1/statisticValue}}}
//     */
//    public static JsonObject getGroupStatistics(String statisticValue, String statisticAlias, JsonObject groupKeys){
//        if(statisticValue == null || statisticValue.equals("")) return new JsonObject();
//        JsonObject jsonObject = new JsonObject()
//                .put("_id", groupKeys)
//                .put(statisticAlias, new JsonObject().put("$sum", statisticValue.equals("1")? 1 : statisticValue));
//        return getGroupCount(jsonObject);
//    }
//
//    public static JsonObject getGroupCount(JsonObject jsonObject){
//        if(jsonObject == null) jsonObject = new JsonObject();
//        return new JsonObject().put("$group", jsonObject);
//    }
//
//    /**
//     * 简单封装vertx-mongodbclient的runCommand方法
//     * @param collection_name  要查询的集群名称
//     * @param pipeline 管道
//     * @param resultHandler 回调处理函数
//     */
//    public static void runCommand(String collection_name, JsonArray pipeline, Handler<AsyncResult<JsonObject>> resultHandler){
//        if(pipeline == null) pipeline = new JsonArray();
//        JsonObject command = new JsonObject()
//                .put("aggregate", collection_name)
//                .put("pipeline", pipeline);
//        MongoDbTools.mongoClient.runCommand("aggregate", command, resultHandler);
//    }
//
//    public static void main(String[] args) {
//        JsonObject one = new JsonObject().put("cusServerOpenid", "this is cusServieopenid");
//        JsonObject two = new JsonObject().put("pushFlag",  0);
//
//        System.out.println(getMatchCondByOrCond(one, two));
//    }
}
