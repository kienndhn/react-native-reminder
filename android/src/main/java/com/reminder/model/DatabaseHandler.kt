package com.reminder.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context:Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
  companion object{
    private val DATABASE_VERSION = 1;
    private val DATABASE_NAME = "ReminderDatabase"
    private val TABLE_NAME = "ReminderTable"
    private val KEY_ID = "id"
    private val KEY_DATA = "data"
    private val KEY_ACTIVE = "active"
  }

  override fun onCreate(db: SQLiteDatabase?) {
    val CREATE_TABLE = ("CREATE TABLE "+ TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"+ KEY_DATA + " TEXT," + KEY_ACTIVE + " INTEGER)")
    db?.execSQL(CREATE_TABLE)
  }

  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
    onCreate(db)
  }
}
