package com.unicaldas.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.unicaldas.login.database.PruebasSaberDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Conectar a la base de datos
        val database= PruebasSaberDB.getDataBase(this)


        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            validarCredenciales()
        }

        val btnAdd = findViewById<Button>(R.id.btnAdicionar)
        btnAdd.setOnClickListener {
            val intentRegister = Intent(this, ActivityRegister::class.java)
            startActivity(intentRegister)
        }

    }

    private fun validarCredenciales() {
        //val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        //val edtPass = findViewById<EditText>(R.id.edtPass)

        CoroutineScope(Dispatchers.IO).launch {
            val database = applicationContext?.let { PruebasSaberDB.getDataBase(it) }
            val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
            val edtPass = findViewById<EditText>(R.id.edtPass)
            //Toast toast1

            if (database != null) {

                val usuario = database.loginDao().getUsuarioXCorreo(edtUsuario.text.toString())
                if(usuario != null){
                    if (edtUsuario.text.toString() == usuario.correo) {
                        if (edtPass.text.toString() == usuario.contrasena) {
                            abrir(usuario.id,usuario.rol)
                        } else {
                            //Toast.makeText(applicationContext?, "Valide su contraseña", Toast.LENGTH_LONG).show()
                        }
                    }else {
                        //Toast.makeText(applicationContext, "Valide su usuario o registrese", Toast.LENGTH_LONG).show()
                            //valideUsuario()
                    }
                }
                else{
                    //Toast.makeText(applicationContext, "Valide su usuario o registrese", Toast.LENGTH_LONG).show()
                    //valideUsuario()
                }
            }
        }
    }
    private fun abrir(usuarioId: Int,rol: Int) {

        val intentPruebaSaber = Intent(this, ActivityPruebaSaber::class.java)
        intentPruebaSaber.putExtra("idRol",rol)
        intentPruebaSaber.putExtra("idUser",usuarioId)
        startActivity(intentPruebaSaber)
    }
/**
    private fun showBasicDialog(view: View){
        val dialog = AlertDialog.Builder(this).setTitle("ERROR!")
            .setMessage("Usuario o Contaseña Invalida").create().show()
    }**/
}