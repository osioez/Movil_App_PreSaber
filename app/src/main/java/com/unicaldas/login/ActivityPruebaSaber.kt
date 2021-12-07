package com.unicaldas.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.unicaldas.login.database.PruebasSaberDB
import com.unicaldas.login.model.Pregunta
import kotlinx.android.synthetic.main.activity_pruebasaber.*

class ActivityPruebaSaber : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebasaber)

        val datos = this.intent.extras
        val rol =  datos!!.getInt("idRol")
        val idUser =  datos!!.getInt("idUser")
        var listaPreguntas = emptyList<Pregunta>()
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnViewTest=findViewById<Button>(R.id.btnViewTest)

        if(rol > 0){
            btnAdd.visibility = View.INVISIBLE
        }

        //conectamos a la BDs
        val database = PruebasSaberDB.getDataBase( this )
        //Consulta las peliculas que estan almacenadas en la BDs
        database.preguntasDao().getAllPreguntas().observe( this, Observer {
            listaPreguntas = it

            val adapter = PreguntasAdapter( this, listaPreguntas )

            //mostrarlo en el ListView de Peliculas
            lvPreguntas.adapter = adapter
        })


        btnAdd.setOnClickListener {
            val intentRegisterPreg = Intent(this, ActivityRegisterQuestions::class.java)
            startActivity(intentRegisterPreg)
        }

        btnViewTest.setOnClickListener {
            val intentTest = Intent(this, ActivityTest::class.java)
            intentTest.putExtra("idUser",idUser)
            startActivity(intentTest)
        }

        val btnCerrar = findViewById<Button>(R.id.btnCerrar)
        btnCerrar.setOnClickListener {
            finish()
        }

        lvPreguntas.setOnItemClickListener { parent, view, position, id ->
            //Enviamos el id al detalle pregunta
            val intentEditPreg = Intent(this, ActivityEditQuestions::class.java)
            intentEditPreg.putExtra("idPregunta", listaPreguntas[position].id)
            intentEditPreg.putExtra("idRol",rol)
            startActivity(intentEditPreg)
        }
    }
}