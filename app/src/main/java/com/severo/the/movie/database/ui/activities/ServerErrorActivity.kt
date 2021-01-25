package com.severo.the.movie.database.ui.activities

import android.os.Bundle
import android.os.Process
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.severo.the.movie.database.R

class ServerErrorActivity : AppCompatActivity() {
    private var tvMessage: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_error)

        val bundle = intent.extras
        if (bundle != null) {
            tvMessage?.setText(bundle.getString("message"))
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        Process.killProcess(Process.myPid())
        System.exit(1)
    }
}