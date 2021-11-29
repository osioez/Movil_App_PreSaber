package com.unicaldas.login.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.unicaldas.login.model.Usuario
import com.unicaldas.login.model.Pregunta
import com.unicaldas.login.dao.LoginDao
import com.unicaldas.login.dao.PreguntasDao

@Database ( entities = [Usuario::class,Pregunta::class],version = 3 ) //Aqui se adicionan las otras entitie Pregunta::class
abstract class PruebasSaberDB : RoomDatabase() {
    //Operaciones de la BD
    abstract fun loginDao() : LoginDao
    abstract fun preguntasDao() : PreguntasDao


    //Instancia BD
    companion object{
        @Volatile
        private var INSTANCE : PruebasSaberDB? = null

        fun getDataBase(context: Context) : PruebasSaberDB {
            val tempInstance = INSTANCE

            if ( tempInstance != null){
                return tempInstance
            }
            synchronized( this ){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PruebasSaberDB::class.java,
                    "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}