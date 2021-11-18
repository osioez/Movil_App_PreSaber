package com.unicaldas.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.unicaldas.login.R

class ActivityPruebaSaber : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebasaber)

        val btnCerrar = findViewById<Button>(R.id.btnCerrar)
        btnCerrar.setOnClickListener {
            finish()
        }
    }
}