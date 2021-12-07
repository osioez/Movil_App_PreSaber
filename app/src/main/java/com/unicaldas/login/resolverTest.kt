package com.unicaldas.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.unicaldas.login.database.PruebasSaberDB
import com.unicaldas.login.model.Test
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class resolverTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resolver_test)

        val datos = this.intent.extras
        val idTest =  datos!!.getInt("idTest")
        verTest(idTest)
        //var listPregTest: ArrayList<Int> = ArrayList()
        //listPregTest = intent.getSerializableExtra("listPregTest") as ArrayList<Int>
        //val idPregunta =  datos!!.getInt("listPregTest")

        //val edtTest=findViewById<EditText>(R.id.edtTest)
        //edtTest.setText(listPregTest[0].toString())
    }

    private fun verTest(idTest: Int) {
        var test: Test = Test(0, "", 0, 0, 0, 0, 0,0,0)

        CoroutineScope(Dispatchers.IO).launch {
            val database = applicationContext?.let { PruebasSaberDB.getDataBase(it) }
            test = database?.TestDao()?.getTest(idTest)!!
            val edtTest = findViewById<EditText>(R.id.edtTest)

            edtTest.setText(
                test.id.toString() + ","
                        + test.testTexto + ","
                        + test.preguntaUno + ","
                        + test.preguntaDos + ","
                        + test.preguntaTres + ","
                        + test.preguntaCuatro + ","
                        + test.PreguntaCinco + ","
                        + test.estado + ","
                        + test.usuarioId
            )
        }
    }
}