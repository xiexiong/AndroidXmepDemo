package com.xmep.xmepmodule

import android.app.Application

class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
        // 初始化（建议放在Application中）
//        FlutterCommHelper.init(applicationContext)
    }
}