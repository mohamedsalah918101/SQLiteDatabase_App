package com.petra.sqlitedatabase_task

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onclick_add_student(view: View){

        val db = DBHelper(this@MainActivity, null)
        val id = findViewById<EditText>(R.id.s_id).text.toString().toInt()
        val name = findViewById<EditText>(R.id.s_name).text.toString()
        val depart = findViewById<EditText>(R.id.s_department).text.toString()
        val course = findViewById<EditText>(R.id.s_course).text.toString()

        db.AddStudent(name, depart, course)
        Toast.makeText(this@MainActivity, "Student Added", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Range")
    fun onclick_get_student(view: View){
        val allstudents = findViewById<TextView>(R.id.all_students)
        val db = DBHelper(this@MainActivity, null)
        val studentcursor = db.getStudent()
        studentcursor!!.moveToFirst()
        allstudents.setText("")
        do {
            val name = studentcursor.getString(studentcursor.getColumnIndex(DBHelper.COL_NAME))
            val depart = studentcursor.getString(studentcursor.getColumnIndex(DBHelper.COL_DEPARTMENT))
            val course = studentcursor.getString(studentcursor.getColumnIndex(DBHelper.COL_COURSE))
            allstudents.append("* " + name + " is in "+ depart + " department"+ " and he's taking "+ course + " course")
            allstudents.append("\n")
        }while (studentcursor.moveToNext())
    }

    fun onclick_update_student(view: View){
        val db = DBHelper(this@MainActivity, null)
        val depart = findViewById<EditText>(R.id.s_department).text.toString()
        val course = findViewById<EditText>(R.id.s_course).text.toString()
        db.updateStudent(1,depart,course)
    }
}