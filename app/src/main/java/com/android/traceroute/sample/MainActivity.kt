package com.android.traceroute.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.android.traceroute.TraceRoute

class MainActivity : AppCompatActivity() {

    lateinit var text: AppCompatEditText

    lateinit var result: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val traceRoute = TraceRoute.traceRoute(arrayOf("traceroute", "--help"))

        text = findViewById(R.id.sample_text)
        result = findViewById(R.id.result)

        findViewById<AppCompatButton>(R.id.sample_button).setOnClickListener {
            doTraceRoute()
        }

    }

    private fun doTraceRoute() {
        result.text = ""
//        TraceRoute.setCallback(object : TraceRouteCallback {
//            override fun onSuccess(traceRouteResult: TraceRouteResult) {
//                result.append("\ntraceroute finish")
//            }
//
//            override fun onUpdate(text: String) {
//                result.append(text)
//            }
//
//            override fun onFailed(code: Int, reason: String) {
//                result.append("\ntraceroute failed")
//            }
//        })
        TraceRoute.setCallback {
            success { result.append("\ntraceroute finish") }
            update { text -> result.append(text) }
            failed { code, reason -> result.append("""\ntraceroute failed.code:$code, reason:$reason""") }
        }
        TraceRoute.traceRoute(text.text.toString(), true)
    }

}
