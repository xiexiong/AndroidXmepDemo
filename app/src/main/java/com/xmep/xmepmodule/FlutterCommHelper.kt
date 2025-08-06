package com.xmep.xmepmodule


import android.content.Context
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

object FlutterCommHelper {
    private lateinit var flutterEngine: FlutterEngine
    private const val CHANNEL_NAME = "com.sharexm.flutter/native"
    private lateinit var methodChannel: MethodChannel

    fun init(context: Context) {
        if (!::flutterEngine.isInitialized) {
            flutterEngine = FlutterEngine(context).apply {
                dartExecutor.executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
                )
                methodChannel = MethodChannel(dartExecutor.binaryMessenger, CHANNEL_NAME)
            }
            FlutterEngineCache.getInstance().put("openXMAIFlutterEngine", flutterEngine)
        }
    }

    fun invokeMethod(
        method: String,
        params: Map<String, Any> = emptyMap(),
        callback: MethodChannel.Result
    ) {
        methodChannel.invokeMethod(method, params, callback)
    }

    fun clean(){
        flutterEngine.destroy()
    }
}
