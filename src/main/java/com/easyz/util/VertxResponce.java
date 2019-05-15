package com.easyz.util;

import io.netty.buffer.ByteBuf;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.io.UnsupportedEncodingException;

/**
 * 返回值封装
 */
public class VertxResponce {

   /**
   * 返回json数据
   * @param rc
   * @param object
   */
    public static void endJson(RoutingContext rc, Object object){
        endJson(rc.response(), object);
    }

    /**
     * 返回json数据
     * @param rc
     * @param object
     */
    public static void endJson(RoutingContext rc, String object){
        endJson(rc.response(), object);
    }


   /**
   * 返回json数据
   * @param response
   * @param object
   */
    public static void endJson(HttpServerResponse response, Object object){
        try {
            String data = "";
            if(object != null) data = JsonObject.mapFrom(object).toString();

            response.putHeader("Access-Control-Allow-Origin","*").putHeader("Content-length", data.getBytes("UTF-8").length + "").putHeader("Content-type", "application/json;charset=UTF-8").end(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回json数据
     * @param response
     * @param data
     */
    public static void endJson(HttpServerResponse response, String data){
        try {
            response.putHeader("Access-Control-Allow-Origin","*").putHeader("Content-length", data.getBytes("UTF-8").length + "").putHeader("Content-type", "application/json;charset=UTF-8").end(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /**
     * 返回图片数据
     * @param response
     * @param buffer
     */
    public static void endJpeg(HttpServerResponse response, Buffer buffer){
        response.putHeader("Access-Control-Allow-Origin","*").putHeader("Content-length", buffer.getBytes().length + "").putHeader("Content-type", "image/jpeg").putHeader("Connection", "Keep-Alive").write(buffer).end();
    }
    /**
     * 返回图片数据
     * @param response
     * @param buffer
     */
    public static void endJpeg(HttpServerResponse response, ByteBuf buffer){
        response.putHeader("Access-Control-Allow-Origin","*").putHeader("Content-length", Buffer.buffer(buffer).getBytes().length + "").putHeader("Content-type", "image/jpeg").putHeader("Connection", "Keep-Alive").write(Buffer.buffer(buffer)).end();
    }
}
