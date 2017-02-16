package com.humboshot.todowithsqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.humboshot.todowithsqlite.Database.TodoDbHelper;
import com.humboshot.todowithsqlite.Model.TodoTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected TodoDbHelper db;
    List<TodoTask> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new TodoDbHelper(this);
        list = db.getAllTodos();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        ListView listTodos = (ListView)findViewById(R.id.todoList);
        listTodos.setAdapter(adapter);
        db.addTodo(new TodoTask("This is a test task"));
    }

    public void addTodoToDb(View v){
        EditText textfield = (EditText) findViewById(R.id.textfieldToDo);
        String itemText = textfield.getText().toString();
        if (itemText.equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter something you need to todo first", Toast.LENGTH_LONG);
        } else {
            TodoTask todoTask = new TodoTask(itemText);
            db.addTodo(todoTask);
            Log.d("todo","Data has been added to the database");
            textfield.setText("");
            adapter.add(todoTask);
            adapter.notifyDataSetChanged();
        }
    }
}
