package com.example.prueba

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        //insert movie
        btn_insert.setOnClickListener{
            var user = User(etvName.text.toString())
            var db = Database(context)
            db.insertData(user)
        }
        //delete movie
        btn_delete.setOnClickListener{
            var user = User(etvName.text.toString())
            var db = Database(context)
            db.delete(user)
        }
        //display movies
        btn_read.setOnClickListener{
            var db = Database(context)
            db.display()
        }
        //update movies
        btn_update.setOnClickListener{
            var db = Database(context)
            db.update()
        }

    }


}