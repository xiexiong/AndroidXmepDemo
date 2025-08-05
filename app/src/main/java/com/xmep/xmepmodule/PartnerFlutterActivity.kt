package com.xmep.xmepmodule

import android.app.Activity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant


/**
 * 自定义情感伴侣页面
 */
class PartnerFlutterActivity : FlutterActivity() {



    companion object {
        //Channel名称
        private const val CHANNEL_NAME = "com.sharexm.flutter/native"
        /**
         * 打开Flutter页面
         */
        fun startActivity(activity: MainActivity) {
            activity.startActivity(
                NewEngineIntentBuilder(PartnerFlutterActivity::class.java).build(activity)
            )
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        val channel = MethodChannel(flutterEngine.dartExecutor, CHANNEL_NAME)
        channel.setMethodCallHandler { call, result ->
            if (call.method == "backToNative") {
                //关闭Flutter当前页面，返回到首页
                finish()
                result.success(null)
            } else {
                result.notImplemented()
            }
        }
        //透传登录参数
        val map = HashMap<String, Any?>()
        map["openUserId"] = "userid0001"
        map["openToken"] = "you are usertoken"
        map["baseUrl"] = "ApiUtils.getApiService()"
        channel.invokeMethod("openXmAi", map)
    }

}