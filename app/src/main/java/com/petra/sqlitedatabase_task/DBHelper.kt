package com.petra.sqlitedatabase_task

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object{
        // Student Database
        const  val DATABASE_NAME = "student_data"
        const  val DATABASE_VERSION = 1
        const val TABLE_NAME = "student"
        const val COL_ID = "s_id"
        const val COL_NAME = "s_name"
        const val COL_DEPARTMENT = "s_department"
        const val COL_COURSE = "s_course"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query_table =
            "CREATE TABLE $TABLE_NAME" +
                    "($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_NAME TEXT, $COL_DEPARTMENT TEXT, $COL_COURSE TEXT);"
        db?.execSQL(query_table)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun AddStudent(studentName: String, studentDepart:String, studentCourse:String ){
        val content_values = ContentValues()
        content_values.put(COL_NAME, studentName)
        content_values.put(COL_DEPARTMENT, studentDepart)
        content_values.put(COL_COURSE, studentCourse)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, content_values)
        db.close()
    }

    fun getStudent(): Cursor? {
        val db = this.readableDatabase
        val cursor_dc = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        return cursor_dc
    }

    fun updateStudent(id: Int, depart:String, course:String){
        val values = ContentValues()
        values.put(COL_DEPARTMENT, depart)
        values.put(COL_COURSE, course)

        val db = this.writableDatabase
        db.update(TABLE_NAME, values, COL_ID+"=?", arrayOf(id.toString()))
        db.close()
    }
}