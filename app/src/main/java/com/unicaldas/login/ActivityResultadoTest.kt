package com.unicaldas.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.unicaldas.login.database.PruebasSaberDB
import com.unicaldas.login.model.Pregunta
import com.unicaldas.login.model.RespuestaTest
import com.unicaldas.login.model.Test
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ActivityResultadoTest : AppCompatActivity() {

    lateinit var test: Test
    lateinit var respuesta: RespuestaTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_test)

        val datos = this.intent.extras
        val idTest = datos!!.getInt("idTest")
        if (idTest > 0) {
            verTest(idTest)
        }
        val btnCerrar = findViewById<Button>(R.id.btnCerrar)
        btnCerrar.setOnClickListener{
            finish()
        }


    }

    private fun verTest(idTest: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            val database = applicationContext?.let { PruebasSaberDB.getDataBase(it) }
            test = database?.TestDao()?.getTest(idTest)!!
            val preguntaUno = database?.preguntasDao()?.getPregunta(test.preguntaUno)!!
            val preguntaDos = database?.preguntasDao()?.getPregunta(test.preguntaDos)!!
            val preguntaTres = database?.preguntasDao()?.getPregunta(test.preguntaTres)!!
            val preguntaCuatro = database?.preguntasDao()?.getPregunta(test.preguntaCuatro)!!
            val preguntaCinco = database?.preguntasDao()?.getPregunta(test.PreguntaCinco)!!
            respuesta = database?.RespuestaDao()?.getRespuestaTestId(idTest)!!

            val Pregunta1 = findViewById<TextView>(R.id.Pregunta1)
            val resPreg1 = findViewById<TextView>(R.id.resPreg1)
            val resCorrecta1 = findViewById<TextView>(R.id.resCorrecta1)
            Pregunta1.setText("${preguntaUno.preguntaTexto}, A)${preguntaUno.opcionUno}, B)${preguntaUno.opcionDos}, C)${preguntaUno.opcionTres}")
            resPreg1.setText("Respuesta Test: ${respuesta.respuestaUno}")
            resCorrecta1.setText("Respuesta Correcta: ${preguntaUno.respuesta}")

            val Pregunta2 = findViewById<TextView>(R.id.Pregunta2)
            val resPreg2 = findViewById<TextView>(R.id.resPreg2)
            val resCorrecta2 = findViewById<TextView>(R.id.resCorrecta2)
            Pregunta2.setText("${preguntaDos.preguntaTexto}, A)${preguntaDos.opcionUno}, B)${preguntaDos.opcionDos}, C)${preguntaDos.opcionTres}")
            resPreg2.setText("Respuesta Test: ${respuesta.respuestaDos}")
            resCorrecta2.setText("Respuesta Correcta: ${preguntaDos.respuesta}")

            val Pregunta3 = findViewById<TextView>(R.id.Pregunta3)
            val resPreg3 = findViewById<TextView>(R.id.resPreg3)
            val resCorrecta3 = findViewById<TextView>(R.id.resCorrecta3)
            Pregunta3.setText("${preguntaTres.preguntaTexto}, A)${preguntaTres.opcionUno}, B)${preguntaTres.opcionDos}, C)${preguntaTres.opcionTres}")
            resPreg3.setText("Respuesta Test: ${respuesta.respuestaTres}")
            resCorrecta3.setText("Respuesta Correcta: ${preguntaTres.respuesta}")

            val Pregunta4 = findViewById<TextView>(R.id.Pregunta4)
            val resPreg4 = findViewById<TextView>(R.id.resPreg4)
            val resCorrecta4 = findViewById<TextView>(R.id.resCorrecta4)
            Pregunta4.setText("${preguntaCuatro.preguntaTexto}, A)${preguntaCuatro.opcionUno}, B)${preguntaCuatro.opcionDos}, C)${preguntaCuatro.opcionTres}")
            resPreg4.setText("Respuesta Test: ${respuesta.respuestaCuatro}")
            resCorrecta4.setText("Respuesta Correcta: ${preguntaCuatro.respuesta}")

            val Pregunta5 = findViewById<TextView>(R.id.Pregunta5)
            val resPreg5 = findViewById<TextView>(R.id.resPreg5)
            val resCorrecta5 = findViewById<TextView>(R.id.resCorrecta5)
            Pregunta5.setText("${preguntaCinco.preguntaTexto}, A)${preguntaCinco.opcionUno}, B)${preguntaCinco.opcionDos}, C)${preguntaCinco.opcionTres}")
            resPreg5.setText("Respuesta Test: ${respuesta.respuestaCinco}")
            resCorrecta5.setText("Respuesta Correcta: ${preguntaCinco.respuesta}")

            val nota = findViewById<TextView>(R.id.nota)
            nota.setText("Nota Final: ${respuesta.notaFinal}")
        }
    }
}