package com.humboshot.todowithsqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.humboshot.todowithsqlite.Model.TodoTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 16-02-2017.
 */

public class TodoDbHelper extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "todoList";
    // tasks table name
    private static final String TABLE_TODO = "todo";
    // tasks Table Columns names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TODONAME = "todoName";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    private static final String TODO_TABLE_CREATE =
            "CREATE TABLE " + TABLE_TODO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TODONAME + " TEXT,"
                    + COLUMN_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP" + ");";

    public TodoDbHelper(Context context){
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db){
        db.execSQL(TODO_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        // Create tables again
        onCreate(db);
    }

    public void addTodo(TodoTask todo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TODONAME, todo.getTodoName()); //Gets this entered string of the task
        db.insertWithOnConflict(TABLE_TODO, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }
    
    public ArrayList<TodoTask> getAllTodos(){
        ArrayList<TodoTask> todoTaskList = new ArrayList();
        //Query for selecting all todos
        String selectQuery = "SELECT " + COLUMN_ID + ", " + COLUMN_TODONAME +" FROM " + TABLE_TODO;
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()){
            do{
                TodoTask todoTask = new TodoTask();
                todoTask.setId(cursor.getInt(0));
                todoTask.setTodoName(cursor.getString(1));
                todoTaskList.add(todoTask);
            } while (cursor.moveToNext());
        }
        return todoTaskList;
    }

}
