package com.unicaldas.login.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unicaldas.login.model.RespuestaTest


@Dao
interface RespuestaDao {
    @Query( "SELECT * FROM respuestatest")
    fun getAllRespuestaTest() : LiveData<List<RespuestaTest>>

    @Query( "SELECT * FROM respuestatest WHERE id = :id")
    fun getRespuestaTest( id: Int) : RespuestaTest

    @Insert
    fun insertRespuestaTest(respuestatest: RespuestaTest)

    @Update
    fun actualizarRespuestaTest(respuestatest: RespuestaTest)

    @Delete
    fun eliminarRespuestaTest(respuestatest: RespuestaTest)
}