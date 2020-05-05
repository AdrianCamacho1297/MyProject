package com.example.myproject

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class adminBD(context: Context) : SQLiteOpenHelper(context, DataBase, null, 1) {
    companion object {
        val DataBase = "Contactos"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE usuario (correoUser TEXT PRIMARY KEY, nombreUser TEXT, contrasenaUser TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    //Funcion para un Insert, Update, Delete.
    fun Ejecutar(sentencia: String): Int {
        try {
            val db = this.writableDatabase
            db.execSQL(sentencia)
            return 1 //Terminacion Exitosa
        } catch (ex: Exception) {
            return 0 //Terminación no Exitosa
        }
    }

    //Select
    fun Consulta(query: String): Cursor? {
        try {
            val db =
                this.writableDatabase //Lanza la consulta de la BD, por eso se pone en modo lectura.
            return db.rawQuery(query, null)
        } catch (ex: java.lang.Exception) {
            return null
        }
    }
}