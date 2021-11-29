package com.unicaldas.login.model
import  androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "usuarios" )
data class Usuario(
    @PrimaryKey( autoGenerate = true )
    var id: Int,
    var nombres: String,
    var apellidos: String,
    var correo: String,
    var contrasena: String,
    var rol: Int //Tipo de usuario checkbox al registrar.
)
