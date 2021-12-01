package com.example.prueba

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf

val DATABASE_NAME = "MYDB"
val TABLE_NAME = "Users"
val COL_NAME = "name"
val COL_ID = "id"

class Database(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME+" VARCHAR(256)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    //function for insert movies
    fun insertData(user: User){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,user.name)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
    }

    //function for display database
    fun display(): Cursor {
        val db = this.readableDatabase
        val projection = arrayOf(BaseColumns._ID, FeedEntry.COLUMN_NAME_TITLE, FeedEntry.COLUMN_NAME_SUBTITLE)
        val selection = "${FeedEntry.COLUMN_NAME_TITLE} = ?"
        val selectionArgs = arrayOf("My Title")
        val sortOrder = "${FeedEntry.COLUMN_NAME_SUBTITLE} DESC"
        val cursor = db.query(
            FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
    }

    //function for delete movie
    fun delete(user: User){
        val selection = "${FeedEntry.COLUMN_NAME_TITLE} LIKE ?"
        val selectionArgs = arrayOf("MyTitle")
        val deletedRows = this.delete(FeedEntry.TABLE_NAME, selection, selectionArgs)
    }

    //function for update database
    fun update(){
        val db = this.writableDatabase
        val title = "MyNewTitle"
        val values = ContentValues().apply {
            put(FeedEntry.COLUMN_NAME_TITLE, title)
        }
        val selection = "${FeedEntry.COLUMN_NAME_TITLE} LIKE ?"
        val selectionArgs = arrayOf("MyOldTitle")
        val count = db.update(
            FeedEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs)
    }
}