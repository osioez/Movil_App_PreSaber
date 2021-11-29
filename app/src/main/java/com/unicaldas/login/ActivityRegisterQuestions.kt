package com.unicaldas.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.unicaldas.login.database.PruebasSaberDB
import com.unicaldas.login.dao.LoginDao
import com.unicaldas.login.model.Pregunta
import kotlinx.android.synthetic.main.activity_pruebasaber.*
import kotlinx.android.synthetic.main.activity_register_questions.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityRegisterQuestions : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_questions)


        val btnAdicionar = findViewById<Button>(R.id.btnAdicionarPregunta)
        btnAdicionar.setOnClickListener {
            registrarPregunta()
        }

    }

    private fun registrarPregunta() {

        val pregunta=Pregunta(0,"${edtPregunta.text}","${edtOpcUno.text}","${edtOpcDos.text}"
            ,"${edtOpcTres.text}","${edtRespuesta.text}","${edtArea.text}")

        CoroutineScope( Dispatchers.IO ).launch {
            val database = applicationContext?.let{ PruebasSaberDB.getDataBase( it ) }
            if ( database != null){
                database.preguntasDao().insertPregunta(pregunta)
                val intentPruebas = Intent(applicationContext, ActivityPruebaSaber::class.java)
                startActivity(intentPruebas)
            }
        }
    }

}