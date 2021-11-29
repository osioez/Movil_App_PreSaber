package com.unicaldas.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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

class ActivityEditQuestions : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_questions)

        //Capturamos las variable enviada
        val datos = this.intent.extras
        val idPregunta =  datos!!.getInt("idPregunta")
        val idRol =  datos!!.getInt("idRol")
        verPregunta(idPregunta)


        val btnEditar= findViewById<Button>(R.id.btnEditarPregunta)
        val btnEliminar=findViewById<Button>(R.id.btnEliminarPregunta)
        val btnGuardar=findViewById<Button>(R.id.btnGuardarPregunta)

        if(idRol>0){
            btnEditar.visibility = View.INVISIBLE
            btnEliminar.visibility = View.INVISIBLE
            btnGuardar.visibility = View.INVISIBLE
        }

        btnEditar.setOnClickListener {
            activarPregunta()
        }
        btnEliminar.setOnClickListener{
            elimimarPregunta(idPregunta,idRol)
        }
        btnGuardar.setOnClickListener{
            guardarPregunta(idPregunta,idRol)
        }




    }

    private fun activarPregunta() {
        val edtPregunta = findViewById<EditText>(R.id.edtPregunta)
        val edtOpUno = findViewById<EditText>(R.id.edtOpcUno)
        val edtOpDos = findViewById<EditText>(R.id.edtOpcDos)
        val edtOpTres = findViewById<EditText>(R.id.edtOpcTres)
        val edtRes = findViewById<EditText>(R.id.edtRespuesta)
        val edtArea = findViewById<EditText>(R.id.edtArea)

        edtPregunta.setEnabled(true)
        edtOpUno.setEnabled(true)
        edtOpDos.setEnabled(true)
        edtOpTres.setEnabled(true)
        edtRes.setEnabled(true)
        edtArea.setEnabled(true)

    }

    private fun verPregunta(idPregunta: Int) {
        var pregunta: Pregunta = Pregunta(0, "", "", "", "", "", "")

        CoroutineScope(Dispatchers.IO).launch {
            val database=applicationContext?.let{ PruebasSaberDB.getDataBase( it ) }
            pregunta=database?.preguntasDao()?.getPregunta(idPregunta)!!

            val edtPregunta = findViewById<EditText>(R.id.edtPregunta)
            val edtOpUno = findViewById<EditText>(R.id.edtOpcUno)
            val edtOpDos = findViewById<EditText>(R.id.edtOpcDos)
            val edtOpTres = findViewById<EditText>(R.id.edtOpcTres)
            val edtRes = findViewById<EditText>(R.id.edtRespuesta)
            val edtArea = findViewById<EditText>(R.id.edtArea)
            edtPregunta.setText(pregunta.preguntaTexto)
            edtOpUno.setText(pregunta.opcionUno)
            edtOpDos.setText(pregunta.opcionDos)
            edtOpTres.setText(pregunta.opcionTres)
            edtRes.setText(pregunta.respuesta)
            edtArea.setText(pregunta.area)

            edtPregunta.setEnabled(false)
            edtOpUno.setEnabled(false)
            edtOpDos.setEnabled(false)
            edtOpTres.setEnabled(false)
            edtRes.setEnabled(false)
            edtArea.setEnabled(false)

        }
    }

    private fun guardarPregunta(idPregunta: Int,idRol: Int) {
        CoroutineScope( Dispatchers.IO ).launch {
            val database = applicationContext?.let{ PruebasSaberDB.getDataBase( it ) }
            if ( database != null){
                val pregunta=Pregunta(
                    idPregunta,
                    "${edtPregunta.text}",
                    "${edtOpcUno.text}",
                    "${edtOpcDos.text}"
                    ,"${edtOpcTres.text}",
                    "${edtRespuesta.text}",
                    "${edtArea.text}"
                )
                database.preguntasDao().actualizarPregunta(pregunta)
                val intentPruebas = Intent(applicationContext, ActivityPruebaSaber::class.java)
                intentPruebas.putExtra("idRol",idRol)
                startActivity(intentPruebas)
            }
        }
    }

    private fun elimimarPregunta(idPregunta: Int,idRol: Int) {
        CoroutineScope( Dispatchers.IO ).launch {
            val database =  applicationContext?.let { PruebasSaberDB.getDataBase(it) }
            val pregunta = Pregunta(idPregunta, "", "","","","", "")
            if ( database != null) {
                database?.preguntasDao()?.eliminarPregunta(pregunta)
                val intentPruebas = Intent(applicationContext, ActivityPruebaSaber::class.java)
                intentPruebas.putExtra("idRol",idRol)
                startActivity(intentPruebas)
            }
        }

    }




}