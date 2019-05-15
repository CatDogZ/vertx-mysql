package com.easyz.verticle.main;

import io.vertx.core.*;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 发布verticle
 * @author z
 * @date 2019/1/15 18:52
 */
public class MainVerticle extends AbstractVerticle {

  private static String CONFIGPATH = "src/main/resource/config.json";

  public static void main(String[] args) throws IOException {
    Vertx.vertx().deployVerticle(new MainVerticle(), new DeploymentOptions()
      .setConfig(new JsonObject(new String(Files.readAllBytes(Paths.get(CONFIGPATH))))
      ));
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    super.start(startFuture);
    JsonArray verticles = config().getJsonArray("verticles");

    List<Future> futures = verticles.stream()
      .map(name -> Future.<String>future(r ->
        vertx.deployVerticle(name.toString(), new DeploymentOptions().setConfig(config()), r))
      ).collect(Collectors.toList());

    CompositeFuture.all(futures).setHandler(res -> {
      System.out.println("发布"+futures.size()+"个verticle");
      if(res.succeeded()){
        System.out.println("所有服务启动成功");
      }else{
        System.out.println(res.result());
        res.cause().printStackTrace();

        System.out.println("最少有一个服务启动失败");
      }
    });
  }
}
