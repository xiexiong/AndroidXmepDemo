package com.xmep.xmailibrary

import android.app.Activity
import com.example.xm_video_player.XmVideoPlayerViewFactory
import io.flutter.FlutterInjector
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.loader.FlutterLoader
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugins.videoplayer.Messages
import io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
import io.flutter.plugins.videoplayer.VideoPlayerPlugin
import io.flutter.plugins.webviewflutter.WebViewFlutterPlugin

object XmaiSDK {

    const val CHANNEL_NAME = "com.sharexm.flutter/native"
    const val FLUTTER_ENGINE_ID = "flutter_engine"
    fun openModule(context : Activity, name: String, route: String, callback: XmAiCallback) {
        val initialRoute ="/${route}"

        // 创建FlutterEngine
        val flutterEngine = FlutterEngine(context).apply {
            navigationChannel.setInitialRoute(initialRoute)
            dartExecutor.executeDartEntrypoint(
//                DartExecutor.DartEntrypoint.createDefault()
                DartExecutor.DartEntrypoint(
                    FlutterInjector.instance().flutterLoader().findAppBundlePath(),"xmNativeMain"
                )
            )
        }
        flutterEngine
            .platformViewsController
            .registry
            .registerViewFactory(
                "xm_video_player/video_player",
                XmVideoPlayerViewFactory(flutterEngine.dartExecutor.binaryMessenger)
            )

        // 手动注册webview插件到flutter引擎
        flutterEngine.getPlugins().add(WebViewFlutterPlugin())

        FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_ID, flutterEngine)
        // 启动FlutterActivity
        context.startActivity(
            FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID)
                .build(context)
        )

        // 设置MethodChannel
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL_NAME).setMethodCallHandler { call, result ->
            when (call.method) {
                "backToNative" -> {
                    callback.onBackToNative()
                    FlutterEngineCache.getInstance().remove(FLUTTER_ENGINE_ID)
                    result.success(null)
                    context.finish()
                }
                "humanCustomerService" -> {
                    callback.onHumanCustomerService()
                    FlutterEngineCache.getInstance().remove(FLUTTER_ENGINE_ID)
                }
                else -> result.notImplemented()
            }
        }

        // 发送参数到Flutter

        val arguments = when (route) {
            "xmcs" -> mapOf(
                "appParams" to mapOf(
                    "openToken" to "sdds2sdfd",
                    "appKey" to "GAB3gDFLZNJB6__-mnMtUt==",
                    "serviceId" to "sasad2q323wsddsdsdsddssdsddsds"
                ),
                "appStyle" to mapOf(
                    "textScaler" to "1",
                    "iconScaler" to "1",
                    "titleScaler" to "1"
                )
            )
            "xmca" -> mapOf(
                "appParams" to mapOf(
                    "openToken" to "sds",
                    "appKey" to "GrA3gEpJZNJB7__-mnMtUg==",
                    "companyId" to "1",
                    "communityTopId" to "1",
                    "communityId" to "1",
                    "baseUrl" to "sss"
                ),
                "appStyle" to mapOf(
                    "textScaler" to "1",
                    "iconScaler" to "1",
                    "titleScaler" to "1"
                )
            )
            "xmdh" -> mapOf(
                "appParams" to mapOf(
                    "openToken" to "xiong",
                    "appKey" to "GrA91gEpJZNJB6__-mnMtUg==",
                    "companyId" to "1",
                    "baseUrl" to "http://baidu.com"
                ),
                "appStyle" to mapOf(
                    "textScaler" to "1",
                    "iconScaler" to "1",
                    "titleScaler" to "1"
                )
            )
            else -> mapOf(
                "appParams" to mapOf(
                    "openToken" to "382dba57cd528d901026b9b0dc674a656aa0dab4867763f35dff4b17d45264d6cb6bfb9a932b7ea30625796d8bfac7ae3ee18470aeee3734753032cf47f2fd8b16a95653802c1212e2d20764493c0af7cea06c7cf3b58e98bb489ef05fd9baf30ed933dfc291073da7f8c62f13b144737c4625398b0c0bcbce48259c32f56863bb625fcf32098a188a394f41a5a86100",
                    "openUserId" to "00059ee89296f8759fc0756aa8b5bbc4",
                    "baseUrl" to "https://imfunc.sharexm.cn/api/im-portal/api"
                ),
                "appStyle" to mapOf(
                    "textScaler" to "1",
                    "iconScaler" to "1",
                    "titleScaler" to "1"
                )
            )
        }

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL_NAME)
            .invokeMethod(name, arguments)
    }
}