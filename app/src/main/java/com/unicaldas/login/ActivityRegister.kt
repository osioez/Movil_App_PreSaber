package com.unicaldas.login

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unicaldas.login.database.PruebasSaberDB
import com.unicaldas.login.model.Usuario
import com.unicaldas.login.dao.LoginDao
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityRegister : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnAdicionar = findViewById<Button>(R.id.btnAdicionar)
        btnAdicionar.setOnClickListener {
            registrarUsuario()
        }
        val checkProfesor= findViewById<CheckBox>(R.id.chkProfesor)
        val checkEstudiante= findViewById<CheckBox>(R.id.chkEstudiante)

        checkProfesor.setOnClickListener{
            if(checkEstudiante.isChecked){
                checkEstudiante.isChecked=false
            }
        }
        checkEstudiante.setOnClickListener{
            if(checkProfesor.isChecked){
                checkProfesor.isChecked=false
            }
        }
    }

    private fun registrarUsuario() {
        val checkProfesor= findViewById<CheckBox>(R.id.chkProfesor)
        val checkEstudiante= findViewById<CheckBox>(R.id.chkEstudiante)
        var rol=0
        Toast.makeText(this, "Mensaje " , Toast.LENGTH_SHORT).show()

        if(!checkProfesor.isChecked && !checkEstudiante.isChecked){
            Toast.makeText(this, "Por favor marque el check según su rol", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Mensaje " , Toast.LENGTH_SHORT).show()
        }
        else {
            if (checkEstudiante.isChecked) {
                rol = 1
            }
            val usuario = Usuario(
                0,
                "${edtNames.text}",
                "${edtLastNames.text}",
                "${edtEmail.text}",
                "${edtPass.text}",
                rol
            )
            CoroutineScope(Dispatchers.IO).launch {
                val database = applicationContext?.let { PruebasSaberDB.getDataBase(it) }
                if (database != null) {
                    database.loginDao().insert(usuario)
                    finish()
                }
            }
        }
    }

}