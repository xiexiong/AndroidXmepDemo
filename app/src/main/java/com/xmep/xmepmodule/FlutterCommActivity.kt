package com.xmep.xmepmodule

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

class FlutterCommActivity : FlutterActivity() {
    // 关键配置：禁用自动引擎创建
    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return FlutterEngineCache.getInstance().get("openXMAIFlutterEngine")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var str = intent.getStringExtra("source")
        var params: Map<String, String>? = null
        val isOpenCS = str.equals("openAICS");
        var  methodName = ""
        if (isOpenCS) {
            methodName = "openXmAiCs";
            params = mapOf(
                "openToken" to "11212sds",
                "appKey" to "GAB3gEpJZNJB6__-mnMtUt==",
                "serviceId" to "sasad2q323wsddsdsdsddssdsddsds"
            )
        }else{
            methodName = "openXmAi";
            params = mapOf(
                "openUserId" to "11212sds",
                "openToken" to "GAB3gEpJZNJB6__-mnMtUt==",
                "baseUrl" to "sasad2q323wsddsdsdsddssdsddsds"
            )
        }


        FlutterCommHelper.invokeMethod(
            methodName.toString(),
            params,
            object : MethodChannel.Result {
                override fun success(result: Any?) {
                    //处理成功回调
                }
                override fun error(code: String, msg: String?, details: Any?) {
                    // 错误处理
                }
                override fun notImplemented() {
                    // 方法未实现
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
//        FlutterCommHelper.clean()
    }
}
