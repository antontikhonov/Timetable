package com.tikhonov.android.schedule_2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "mydbc"
private const val DB_VERSION = 3

class TimetableDatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE DAY (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ON1 TEXT, "
                    + "UNDER1 TEXT, "
                    + "ON2 TEXT, "
                    + "UNDER2 TEXT, "
                    + "ON3 TEXT, "
                    + "UNDER3 TEXT, "
                    + "ON4 TEXT, "
                    + "UNDER4 TEXT, "
                    + "ON5 TEXT, "
                    + "UNDER5 TEXT, "
                    + "ON6 TEXT, "
                    + "UNDER6 TEXT, "
                    + "ON7 TEXT, "
                    + "UNDER7 TEXT);"
        )
        putDay(sqLiteDatabase, "Понедельник")
        putDay(sqLiteDatabase, "Вторник")
        putDay(sqLiteDatabase, "Среда")
        putDay(sqLiteDatabase, "Четверг")
        putDay(sqLiteDatabase, "Пятница")
        putDay(sqLiteDatabase, "Суббота")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    private fun putDay(sqLiteDatabase: SQLiteDatabase, name: String) {
        ContentValues().also {
            it.put("NAME", name)
            it.put("ON1", "")
            it.put("UNDER1", "")
            it.put("ON2", "")
            it.put("UNDER2", "")
            it.put("ON3", "")
            it.put("UNDER3", "")
            it.put("ON4", "")
            it.put("UNDER4", "")
            it.put("ON5", "")
            it.put("UNDER5", "")
            it.put("ON6", "")
            it.put("UNDER6", "")
            it.put("ON7", "")
            it.put("UNDER7", "")
            sqLiteDatabase.insert("DAY", null, it)
        }
    }
}