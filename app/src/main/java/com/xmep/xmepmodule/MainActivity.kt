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
    }

    override fun onBackToNative() {
        Toast.makeText(this,"Callback backToNative", Toast.LENGTH_LONG).show()
    }

    override fun onHumanCustomerService() {
        Toast.makeText(this,"Callback HumanCustomerService", Toast.LENGTH_LONG).show()
    }

}
