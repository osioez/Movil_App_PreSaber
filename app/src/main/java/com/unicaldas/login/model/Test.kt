package com.unicaldas.login.model
import  androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "test" )
data class Test(
    @PrimaryKey( autoGenerate = true )
    var id: Int,
    var testTexto: String,
    var preguntaUno: Int,
    var preguntaDos: Int,
    var preguntaTres: Int,
    var preguntaCuatro: Int,
    var PreguntaCinco: Int,
    var estado: Int,
    var usuarioId: Int
)
