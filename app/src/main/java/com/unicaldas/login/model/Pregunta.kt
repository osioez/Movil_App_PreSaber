package com.unicaldas.login.model
import  androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "preguntas" )
data class Pregunta(
    @PrimaryKey( autoGenerate = true )
    var id: Int,
    var preguntaTexto: String,
    var opcionUno: String,
    var opcionDos: String,
    var opcionTres: String,
    var respuesta: String,
    var area: String
)
