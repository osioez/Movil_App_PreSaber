package com.unicaldas.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            validarCredenciales()
        }

        /*val btnAdd = findViewById<Button>(R.id.btnAdicionar)
        btnAdd.setOnClickListener {
            val intentRegister = Intent(this, ActivityRegister::class.java)
            startActivity(intentRegister)
        }*/

    }

    private fun validarCredenciales() {
        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        val edtPass = findViewById<EditText>(R.id.edtPass)

        if (edtUsuario.text.toString() == "yhei@email.com") {
            if (edtPass.text.toString().equals("1234")) {
                val intentPruebaSaber = Intent(this, ActivityPruebaSaber::class.java)
                startActivity(intentPruebaSaber)
            } else
                Toast.makeText(this, "Contraseña Invalida", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Valide su usuario", Toast.LENGTH_LONG).show()
        }
    }


}