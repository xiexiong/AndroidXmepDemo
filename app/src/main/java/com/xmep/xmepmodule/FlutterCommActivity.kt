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
        var sourceInt = intent.getIntExtra("source",0)
        val initRoute = if (sourceInt == 0) "/threeLogin" else "/xmcs"

        var params: Map<String, String>? = null
        var  methodName = "openXmAi"
        if (sourceInt == 1) {
            params = mapOf(
                "openToken" to "sdds2sdfd",
                "appKey" to "GAB3gEPLZNJB6__-mnMtUt==",
                "serviceId" to "sasad2q323wsddsdsdsddssdsddsds"
            )
        }else{
            params = mapOf(
                "openUserId" to "382dba57cd528d901026b9b0dc674a656aa0dab4867763f35dff4b17d45264d6cb6bfb9a932b7ea30625796d8bfac7ae3ee18470aeee3734753032cf47f2fd8b16a95653802c1212e2d20764493c0af7cea06c7cf3b58e98bb489ef05fd9baf30ed933dfc291073da7f8c62f13b144737c4625398b0c0bcbce48259c32f56863bb625fcf32098a188a394f41a5a86100",
                "openToken" to "00059ee89296f8759fc0756aa8b5bbc4",
                "baseUrl" to "https://imfunc.sharexm.cn/api/im-portal/api"
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
        FlutterCommHelper.clean()
    }
}
