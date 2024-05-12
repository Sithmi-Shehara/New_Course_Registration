package com.example.course_registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sname = findViewById(R.id.txtname) as EditText
        val scourse = findViewById(R.id.txtcourse) as EditText
        val sfee = findViewById(R.id.txtfee) as EditText

        val b1 = findViewById(R.id.button4) as Button
        val b2 = findViewById(R.id.button5) as Button

        b2.setOnClickListener{

            val i = Intent(applicationContext,View::class.java)
            startActivity(i)
        }

        b1.setOnClickListener{
            try {
                val name = sname.getText().toString()
                val course = scourse.getText().toString()
                val fee = sfee.getText().toString()

                //Create the database
                val db = openOrCreateDatabase("slitDb", Context.MODE_PRIVATE,null)
                db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,course VARCHAR,fee VARCHAR )")

                //this is insert records by using prepared statement(?,?,?)
                val sql = "insert into records(name,course,fee)values(?,?,?)"
                val statement = db.compileStatement(sql)
                statement.bindString(1,name)
                statement.bindString(2,course)
                statement.bindString(3,fee)
                statement.execute()
                Toast.makeText(this,"Record Added", Toast.LENGTH_LONG).show()

                sname.setText("")
                scourse.setText("")
                sfee.setText("")
                sname.requestFocus()


            }catch (ex: Exception){
                Toast.makeText(this,"Record Failed", Toast.LENGTH_LONG).show()

            }
        }




    }
}