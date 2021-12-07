package com.unicaldas.login.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unicaldas.login.model.Test

@Dao
interface TestDao {
    @Query( "SELECT * FROM test")
    fun getAllTest() : LiveData<List<Test>>

    @Query( "SELECT * FROM test WHERE id = :id")
    fun getTest( id: Int) : Test

    @Insert
    fun insertTest(test: Test)

    @Update
    fun actualizarTest(test: Test)

    @Delete
    fun eliminarTest(test: Test)
}