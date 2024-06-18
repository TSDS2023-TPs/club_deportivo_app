package com.example.appclubdeportivo.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "proyecto_club_deportivo.sqlite"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun insert(tableName: String, values: ContentValues): Long {
        val db = writableDatabase
        return db.insert(tableName, null, values)
    }

    fun update(tableName: String, values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        val db = writableDatabase
        return db.update(tableName, values, whereClause, whereArgs)
    }

    fun delete(tableName: String, whereClause: String, whereArgs: Array<String>): Int {
        val db = writableDatabase
        return db.delete(tableName, whereClause, whereArgs)
    }

    fun <T> getAll(tableName: String, mapper: (Cursor) -> T): List<T> {
        val items = mutableListOf<T>()
        val db = readableDatabase
        val cursor = db.query(tableName, null, null, null, null, null, null)
        cursor.use {
            while (it.moveToNext()) {
                items.add(mapper(it))
            }
        }
        return items
    }




}
