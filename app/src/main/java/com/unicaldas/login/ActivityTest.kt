package com.unicaldas.login

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.unicaldas.login.database.PruebasSaberDB
import com.unicaldas.login.model.Pregunta
import com.unicaldas.login.model.Test
import kotlinx.android.synthetic.main.activity_pruebasaber.*
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ActivityTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val datos = this.intent.extras
        val idUser =  datos!!.getInt("idUser")
        var listaTest = emptyList<Test>()
        var listaPreguntas = emptyList<Pregunta>()

        val btnGenerate=findViewById<Button>(R.id.btnGenerate)
        val database = PruebasSaberDB.getDataBase( this )
        //Consulta las peliculas que estan almacenadas en la BDs
        database.TestDao().getAllTest().observe(this, Observer {
            listaTest = it
            val adapter = TestAdapter(this, listaTest)
            lvTest.adapter=adapter
        })

        btnGenerate.setOnClickListener{
            generateTest(idUser)
        }
        lvTest.setOnItemClickListener { parent, view, position, id ->
            val intentTest = Intent(applicationContext, resolverTest::class.java)

            intentTest.putExtra("idTest", listaTest[position].id)
            intentTest.putExtra("estadoTest", listaTest[position].estado)
            startActivity(intentTest)
            //intentTest.putExtra("listPregTest",listPregTest)
            //startActivity(intentTest)
            //intentEditPreg.putExtra("idRol",rol)
        }
    }

    private fun generateTest(idUser: Int) {

        CoroutineScope( Dispatchers.IO ).launch {
            val database = applicationContext?.let { PruebasSaberDB.getDataBase(it) }
            val listaPreg = database!!.preguntasDao().getAllPreguntasList()

            var listPregTest: ArrayList<Int> = ArrayList()
            var item= 1
            while (item < 6) {
                var pregunta = listaPreg.randomOrNull()
                if (pregunta != null) {
                    if (listPregTest.contains(pregunta.id)) {
                        item--
                    } else {
                        listPregTest.add(pregunta.id)
                    }
                }
                item++
            }
            //edtPregunta.setText(listPregTest.size)
            val test=Test(
                0,
                "Test de Preguntas",
                listPregTest[0],
                listPregTest[1],
                listPregTest[2],
                listPregTest[3],
                listPregTest[4],
                0,
                idUser
            )
            database.TestDao().insertTest(test)
            //val intentTest = Intent(applicationContext, ActivityTest::class.java)
            //intentTest.putExtra("listPregTest",listPregTest)
            //startActivity(intentTest)
            //Toast.makeText(this, "${pregunta}", Toast.LENGTH_LONG).show()
        }
    }
}