package com.unicaldas.login.model
import  androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "respuestatest" )
data class RespuestaTest(
    @PrimaryKey( autoGenerate = true )
    var id: Int,
    var respuestaUno: String,
    var respuestaDos: String,
    var respuestaTres: String,
    var respuestaCuatro: String,
    var respuestaCinco: String,
    var testId: Int,
    var notaFinal: Int
)
