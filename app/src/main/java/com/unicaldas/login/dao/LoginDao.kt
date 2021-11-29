package com.unicaldas.login.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unicaldas.login.model.Usuario

@Dao
interface LoginDao {
    @Query( "SELECT * FROM usuarios")
    fun getAll() : LiveData<List<Usuario>>

    @Query( "SELECT * FROM usuarios where correo = :correo")
    fun getItemsXCorreo(correo: String) : List<Usuario>

    @Query( "SELECT * FROM usuarios where correo = :correo")
    fun getUsuarioXCorreo(correo: String) : Usuario

    @Query( "SELECT * FROM usuarios WHERE id = :id")
    fun getUsuario( id: Int) : Usuario

    @Insert
    fun insert(usuario: Usuario)

    @Update
    fun actualizar(usuario: Usuario)

    @Delete
    fun eliminar(usuario: Usuario)

    // Preguntas
    /**

    */
}