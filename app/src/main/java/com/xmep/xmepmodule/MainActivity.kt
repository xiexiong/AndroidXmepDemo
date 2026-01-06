package com.xmep.xmepmodule

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.xmep.xmailibrary.XmAiCallback
import com.xmep.xmailibrary.XmaiSDK

class MainActivity : ComponentActivity(), XmAiCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        findViewById<Button>(R.id.btn_ai).setOnClickListener {
            XmaiSDK.openModule(this,"openXmai","xmep",this)
        }

        findViewById<Button>(R.id.btn_ai001).setOnClickListener {
            XmaiSDK.openModule(this,"openXmcs","xmcs",this)
        }

        findViewById<Button>(R.id.btn_ai002).setOnClickListener {
            XmaiSDK.openModule(this,"openXmca","xmca",this)
        }
        findViewById<Button>(R.id.btn_ai003).setOnClickListener {
            XmaiSDK.openModule(this,"openXmdh","xmdh",this)
        }
    }

    override fun onBackToNative() {
        Toast.makeText(this,"Callback backToNative", Toast.LENGTH_LONG).show()
    }

    override fun onHumanCustomerService() {
        Toast.makeText(this,"Callback HumanCustomerService", Toast.LENGTH_LONG).show()
    }

}


//engine.run 增加入口配置(Entrypoint)参数,值固定为xmNativeMain
//🌰 engine.run(withEntrypoint: "xmNativeMain", initialRoute: initRoute)
//
//FlutterMethod 变化:
//HumanCustomerService -> humanCustomerService (更新,头字母H->h)
//xmcaReferenceDetail 社群助手关联资料详情跳转(新增) 返回参数跟 @肖友强 对接
//xmdhShareVideo 数字人分享视频链接 (新增) 返回参数跟 @谢雄  对接
//
//openFlutterPage args
//类型由原来的[String, String] -> [String, Any]
//内容结构由原来的{}改为{"appParams":"","appStyle":""}
//🌰
//let appParams = [
//// 公共参数
//"openToken": "sdds2sdfd", // 享脉的code
//"appKey": "GAB3gDFLZNJB6__-mnMtUt==", // 分配的appkey
//"baseUrl": "sss", // 享脉对应环境的后端API baseUrl
//// 业务参数
//"serviceId": "e113589d-7be9-45a8-894d-84d3cfb6f87b", //客服ID
//]
//let appStyle = ["textScaler": "1", "iconScaler": "1", "titleScaler": "1"]
//let args = ["appParams": appParams, "appStyle": appStyle]
