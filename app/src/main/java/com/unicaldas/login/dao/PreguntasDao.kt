package com.unicaldas.login.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unicaldas.login.model.Pregunta

@Dao
interface PreguntasDao {
    @Query( "SELECT * FROM preguntas")
    fun getAllPreguntas() : LiveData<List<Pregunta>>

    @Query( "SELECT * FROM preguntas WHERE id = :id")
    fun getPregunta( id: Int) : Pregunta

    @Insert
    fun insertPregunta(pregunta: Pregunta)

    @Update
    fun actualizarPregunta(pregunta: Pregunta)

    @Delete
    fun eliminarPregunta(pregunta: Pregunta)
}