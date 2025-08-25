package com.xmep.xmepmodule

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class MainActivity : ComponentActivity() {
    private companion object {
        const val CHANNEL_NAME = "com.sharexm.flutter/native"
        const val FLUTTER_ENGINE_ID = "flutter_engine"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        findViewById<Button>(R.id.btn_ai).setOnClickListener { openFlutter(0) }
        findViewById<Button>(R.id.btn_ai001).setOnClickListener { openFlutter(1) }
    }

    private fun openFlutter(type: Int) {
        val method = "openXmAi"
        val initialRoute = if (type == 0) "/threeLogin" else "/xmcs"

        // 创建FlutterEngine
        val flutterEngine = FlutterEngine(this).apply {
            navigationChannel.setInitialRoute(initialRoute)
            dartExecutor.executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )
        }
        FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_ID, flutterEngine)

        // 启动FlutterActivity
        startActivity(
            FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID)
                .build(this)
        )

        // 设置MethodChannel
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL_NAME).setMethodCallHandler { call, result ->
            when (call.method) {
                "backToNative" -> {
                    Toast.makeText(this,"backToNative111111", Toast.LENGTH_LONG).show()
                    finish()
                    FlutterEngineCache.getInstance().remove(FLUTTER_ENGINE_ID)
                    result.success(null)
                }
                else -> result.notImplemented()
            }
        }

        // 发送参数到Flutter
        val arguments = when (type) {
            0 -> mapOf(
                "openToken" to "382dba57cd528d901026b9b0dc674a656aa0dab4867763f35dff4b17d45264d6cb6bfb9a932b7ea30625796d8bfac7ae3ee18470aeee3734753032cf47f2fd8b16a95653802c1212e2d20764493c0af7cea06c7cf3b58e98bb489ef05fd9baf30ed933dfc291073da7f8c62f13b144737c4625398b0c0bcbce48259c32f56863bb625fcf32098a188a394f41a5a86100",
                "openUserId" to "00059ee89296f8759fc0756aa8b5bbc4",
                "baseUrl" to "https://imfunc.sharexm.cn/api/im-portal/api"
            )
            else -> mapOf(
                "openToken" to "sdds2sdfd",
                "appKey" to "GAB3gEPLZNJB6__-mnMtUt==",
                "serviceId" to "sasad2q323wsddsdsdsddssdsddsds"
            )
        }

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL_NAME)
            .invokeMethod(method, arguments)
    }
}
