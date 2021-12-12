package com.unicaldas.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.unicaldas.login.database.PruebasSaberDB
import com.unicaldas.login.model.Test
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.unicaldas.login.model.Pregunta
import com.unicaldas.login.model.RespuestaTest

class resolverTest : AppCompatActivity() {

    var nota = 0
    var nPregunta = 0
    var respPregunta = ""
    lateinit var test: Test
    lateinit var pregunta: Pregunta
    lateinit var respuestaTest: RespuestaTest
    lateinit var tviewNumPreg: TextView
    lateinit var tviewDesPreg: TextView
    lateinit var edtTest: EditText
    lateinit var RespA: RadioButton
    lateinit var RespB: RadioButton
    lateinit var RespC: RadioButton
    lateinit var resPreg: TextView
    lateinit var btnSiguiente: Button
    lateinit var btnCerrar: Button
    lateinit var listRespTest: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resolver_test)

        RespA=findViewById<RadioButton>(R.id.RespA)
        RespB=findViewById<RadioButton>(R.id.RespB)
        RespC=findViewById<RadioButton>(R.id.RespC)
        resPreg=findViewById<RadioButton>(R.id.resPreg)
        btnSiguiente=findViewById<Button>(R.id.btnSiguiente)
        btnCerrar=findViewById<Button>(R.id.btnCerrar)
        checksInvisible()
        btnCerrar.visibility = View.INVISIBLE
        listRespTest = ArrayList()

        val datos = this.intent.extras
        val idTest =  datos!!.getInt("idTest")
        val estadoTest =  datos!!.getInt("estadoTest")
        val btnResultado = findViewById<Button>(R.id.btnResultado)
        tviewNumPreg=findViewById<TextView>(R.id.numPreg)
        tviewDesPreg=findViewById<TextView>(R.id.desPreg)

        test= Test(0, "", 0, 0, 0, 0, 0,0,0)
        CoroutineScope(Dispatchers.IO).launch {
            verTest(idTest)
        }
        edtTest = findViewById<EditText>(R.id.edtTest)
        edtTest.setText(
            "$idTest,$estadoTest::"
        )
        if(estadoTest==1){
            tviewNumPreg.setText("El test ha sido resuelto anteriormente")
            tviewDesPreg.setText("Puede ver el resultado del test o resolverlo de nuevo")
            btnSiguiente.setText("Resolver Test")
        }

        RespA.setOnClickListener{
            checks()
        }
        RespB.setOnClickListener{
            checks()
        }
        RespC.setOnClickListener{
            checks()
        }
        btnSiguiente.setOnClickListener{
            RespA.visibility= View.VISIBLE
            RespB.visibility= View.VISIBLE
            RespC.visibility= View.VISIBLE
            btnSiguiente.setText("Next Questions")
            nuevaPregunta(test)
            if(nPregunta>4){
                btnSiguiente.setText("Send Questions")
            }
        }

        btnResultado.setOnClickListener{
            val intentTest = Intent(applicationContext, ActivityResultadoTest::class.java)
            intentTest.putExtra("idTest", idTest)
            startActivity(intentTest)
        }
        btnCerrar.setOnClickListener{
            finish()
        }



    }

    private fun nuevaPregunta(test: Test) {

        if(nPregunta==0) {
            Log.e("pregunta 0***"+ nPregunta.toString(),"probando ******")
            nPregunta += 1
            tviewNumPreg.setText("Pregunta No." + nPregunta)
            if(test.preguntaUno>0 && test.preguntaUno!=null){
                verPregunta(test.preguntaUno)
            }
        }
        if(RespA.isChecked() == false && RespB.isChecked() == false && RespC.isChecked()==false){
            Log.e("Elija una opción*** "+ nPregunta.toString(),"probando ******")
            Toast.makeText(this, "Elija una opción", Toast.LENGTH_SHORT).show()
        }
        else if(nPregunta==1) {
            if(RespA.isChecked){respPregunta="A"}
            if(RespB.isChecked){respPregunta="B"}
            if(RespC.isChecked){respPregunta="C"}
            validaRespuesta(respPregunta)
            checksNotClick()
            Log.e("pregunta 1***"+ nPregunta.toString(),"probando ******")
            nPregunta += 1
            tviewNumPreg.setText("Pregunta No." + nPregunta)
            if(test.preguntaDos>0 && test.preguntaDos!=null){
                verPregunta(test.preguntaDos)
            }
            checksNotClick()
        }
        else if(nPregunta==2){
            if(RespA.isChecked){respPregunta="A"}
            if(RespB.isChecked){respPregunta="B"}
            if(RespC.isChecked){respPregunta="C"}
            validaRespuesta(respPregunta)
            checksNotClick()
            Log.e("pregunta 2***"+ nPregunta.toString(),"probando ******")
            nPregunta += 1
            tviewNumPreg.setText("Pregunta No." + nPregunta)
            if(test.preguntaTres>0 && test.preguntaTres!=null){
                verPregunta(test.preguntaTres)
            }
        }
        else if(nPregunta==3){
            if(RespA.isChecked){respPregunta="A"}
            if(RespB.isChecked){respPregunta="B"}
            if(RespC.isChecked){respPregunta="C"}
            validaRespuesta(respPregunta)
            checksNotClick()
            Log.e("pregunta 3***"+ nPregunta.toString(),"probando ******")
            nPregunta += 1
            tviewNumPreg.setText("Pregunta No." + nPregunta)
            if(test.preguntaCuatro>0 && test.preguntaCuatro!=null){
                verPregunta(test.preguntaCuatro)
            }

        }
        else if(nPregunta==4){
            if(RespA.isChecked){respPregunta="A"}
            if(RespB.isChecked){respPregunta="B"}
            if(RespC.isChecked){respPregunta="C"}
            validaRespuesta(respPregunta)
            checksNotClick()
            Log.e("pregunta 4***"+ nPregunta.toString(),"probando ******")
            nPregunta += 1
            tviewNumPreg.setText("Pregunta No." + nPregunta)
            if(test.PreguntaCinco>0 && test.PreguntaCinco!=null){
                verPregunta(test.PreguntaCinco)
            }

        }
        else if(nPregunta==5){
            if(RespA.isChecked){respPregunta="A"}
            if(RespB.isChecked){respPregunta="B"}
            if(RespC.isChecked){respPregunta="C"}
            validaRespuesta(respPregunta)
            checksNotClick()
            Log.e("pregunta 5***"+ nPregunta.toString(),"probando ******")
            nPregunta += 1
            Log.e("Test Finalizado***"+ nPregunta.toString(),"probando ******")
            edtTest.setText("$listRespTest[0] Nota:$nota")
        }
    }

    suspend fun verTest(idTest: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            val database = applicationContext?.let { PruebasSaberDB.getDataBase(it) }
            test = database?.TestDao()?.getTest(idTest)!!
            //val edtTest = findViewById<EditText>(R.id.edtTest)
            println("***** Probando")
            Log.e("probando ***","probando ******")
        }
    }
    private fun verPregunta(idPregunta: Int){

        pregunta = Pregunta(0, "", "", "", "", "", "")
        CoroutineScope(Dispatchers.IO).launch {

            val database = applicationContext?.let { PruebasSaberDB.getDataBase(it) }
            pregunta = database?.preguntasDao()?.getPregunta(idPregunta)!!
            tviewDesPreg.setText(pregunta.preguntaTexto).toString()
            RespA.setText(pregunta.opcionUno).toString()
            RespB.setText(pregunta.opcionDos).toString()
            RespC.setText(pregunta.opcionTres).toString()
            resPreg.setText(pregunta.respuesta).toString()
        }
    }

    private fun validaRespuesta(respuesta: String){
        Log.e("probando ***","Valida respúesta ******")
        listRespTest.add(respPregunta)
        if(respuesta==("${resPreg.text}")){
             nota += 1
            Log.e("probando ***","Nota Sumando ${nota} ******")
        }
        Log.e("probando ***","Lita Sumando ${listRespTest.size}  ******")
        if(listRespTest.size==5){
            Log.e("probando ***","Val List es igual a 5 ******")
            checksInvisible()
            tviewNumPreg.setText("Test Finalizado. Resultado: ")
            tviewDesPreg.setText("Nota. ${nota}")
            btnSiguiente.visibility = View.INVISIBLE
            btnCerrar.visibility = View.VISIBLE

            Log.e("probando ***","Estado test en el if ${test.estado}  ******")
            /** Se guarda la respuesta del test **/
            CoroutineScope(Dispatchers.IO).launch {
                val database = applicationContext?.let { PruebasSaberDB.getDataBase(it) }
                Log.e("probando ***","Inicio Corutina ${test.estado}  ******")
                if (database != null) {
                    Log.e("probando ***","base de datos no null  ******")
                    if(test.estado==0) {
                        val respuestaTest=RespuestaTest(
                            0,
                            listRespTest[0],
                            listRespTest[1],
                            listRespTest[2],
                            listRespTest[3],
                            listRespTest[4],
                            test.id,
                            nota
                        )
                        database.RespuestaDao().insertRespuestaTest(respuestaTest)
                        test.estado = 1
                        database.TestDao().actualizarTest(test)

                    }
                    else{
                        respuestaTest=database.RespuestaDao().getRespuestaTestId(test.id)
                        val respuestaTest=RespuestaTest(
                            respuestaTest.id,
                            listRespTest[0],
                            listRespTest[1],
                            listRespTest[2],
                            listRespTest[3],
                            listRespTest[4],
                            test.id,
                            nota
                        )
                        Log.e("probando ***","Listadp a Actualizar ******${listRespTest}")
                        Log.e("probando ***","probando ******${respuestaTest.notaFinal}")
                        database.RespuestaDao().actualizarRespuestaTest(respuestaTest)
                    }
                }
                else{
                    Log.e("probando ***","Null Database ******")
                }
            }

        }

    }

    private fun checksNotClick() {
        RespA.isChecked=false
        RespB.isChecked=false
        RespC.isChecked=false
    }

    private fun checksInvisible() {
        RespA.visibility= View.INVISIBLE
        RespB.visibility= View.INVISIBLE
        RespC.visibility= View.INVISIBLE
    }


    private fun checks(){
        RespA.setOnClickListener{
            if(RespB.isChecked || RespC.isChecked){
                RespB.isChecked=false
                RespC.isChecked=false
            }
        }
        RespB.setOnClickListener{
            if(RespA.isChecked || RespC.isChecked){
                RespA.isChecked=false
                RespC.isChecked=false
            }
        }
        RespC.setOnClickListener{
            if(RespA.isChecked || RespB.isChecked){
                RespA.isChecked=false
                RespB.isChecked=false
            }
        }
    }
}