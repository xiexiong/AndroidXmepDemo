package com.xmep.xmepmodule

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        findViewById<Button>(R.id.btn_ai).setOnClickListener {
            val intent = Intent(this, FlutterCommActivity::class.java).apply {
                putExtra("source", "openAI")
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_ai001).setOnClickListener {
            val intent = Intent(this, FlutterCommActivity::class.java).apply {
                putExtra("source", "openAICS")
            }
            startActivity(intent)
        }



    }

}

