package com.example.course_registration

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView


class View : AppCompatActivity() {

    internal var titles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        //Create the database
        val db = openOrCreateDatabase("slitDb", Context.MODE_PRIVATE,null)

        val list1 = findViewById(R.id.list1) as ListView

        val c =db.rawQuery("select * from records", null)

        //auto increment part
        val id = c.getColumnIndex("id")
        val name = c.getColumnIndex("name")
        val course = c.getColumnIndex("course")
        val fee = c.getColumnIndex("fee")
        titles.clear()

        var arrayAdapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles)
        list1.setAdapter(arrayAdapter)

        val stud = ArrayList<Student>()

        if (c.moveToFirst()){
            do{

                val stu = Student()
                stu.id = c.getString(id)
                stu.name = c.getString(name)
                stu.course = c.getString(course)
                stu.fee = c.getString(fee)
                stud.add(stu)

                titles.add(c.getString(id) + "\t" + c.getString(name) + "\t" + c.getString(course) + "\t" + c.getString(fee))



            }while (c.moveToNext())
            arrayAdapter.notifyDataSetChanged()
            list1.invalidateViews()
        }

    }
}