1、	添加下载仓库地址（settings.gradle）

  maven {
  
      url = uri("http://nexus.test.sharexm.cn/repository/xmai/")
      setAllowInsecureProtocol(true)
      credentials {
          username = "xmai"                    // 账号
          password = "C9beGFAFOxNnQQIo"         // 密码
      }
  }

2、	确保AndroidX兼容性（gradle.properties）

android.enableJetifier=true

android.useAndroidX=true

3、	添加flutter插件依赖、项目插件依赖。（app/gradle）

     //引擎版本
    def engineVersion = 'cf56914b326edb0ccb123ffdc60f00060bd513fa'
    //flutter 插件依赖
    Implementation 'io.flutter:flutter_embedding_release:1.0.0-' + engineVersion
    implementation 'io.flutter:armeabi_v7a_release:1.0.0-' + engineVersion
    implementation 'io.flutter:arm64_v8a_release:1.0.0-' + engineVersion
    implementation 'io.flutter:x86_64_release:1.0.0-' + engineVersion
    //情感伴侣
    implementation 'com.yofo.xmai:nuisdk:1.0.1'
    implementation 'com.yofo.xmai:xmepRelease:1.0.8'
    implementation 'com.yofo.xmai:aliyun_av_plugin:1.0.2'
    implementation 'com.yofo.xmai:audioplayers:1.0.1'
    implementation 'com.yofo.xmai:connectivity:1.0.1'
    implementation 'com.yofo.xmai:device_info_plus:1.0.1'
    implementation 'com.yofo.xmai:package_info_plus:1.0.1'
    implementation 'com.yofo.xmai:flutter_aliyun_nui:1.0.1'
    implementation 'com.yofo.xmai:xmcs:1.0.1'
    implementation 'com.yofo.xmai:path_provider:1.0.1'
    implementation 'com.yofo.xmai:shared_preferences:1.0.1'
    implementation 'com.yofo.xmai:url_launcher:1.0.1'
    implementation 'com.yofo.xmai:webview_flutter:1.0.1'
    implementation 'com.yofo.xmai:permission_handler:1.0.1'
    implementation 'com.yofo.xmai:sqflite:1.0.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.2.1'

4、	权限添加（AndroidManifest.xml）

  <uses-feature
        android:name="android.hardware.camera"
        android:required="false" />

    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="32" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"
        android:maxSdkVersion="32" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />

5、	在AndroidManifest.xml添加flutteractivity引用

        <activity android:name=".PartnerFlutterActivity"
            android:screenOrientation="portrait" android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale|layoutDirection|fontScale|screenLayout|density|uiMode"
            android:hardwareAccelerated="true"
            android:windowSoftInputMode="adjustResize"
            android:exported="true"/>

6、	实现跳转Flutter页面，参考（PartnerFlutterActivity）


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



          


