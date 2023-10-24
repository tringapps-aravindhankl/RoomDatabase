package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var edit1: EditText=findViewById(R.id.editTextText);
        var edit2: EditText=findViewById(R.id.editTextText2);
        var button: Button=findViewById(R.id.button);

        button.setOnClickListener{
            var username=edit1.text.toString()
            var password=edit2.text.toString()
            var database=MyDatabase.getInstance(this)
            var userDao=database?.userDao();
            var user=User(15,username,password)
            GlobalScope.launch {
                try {
                    userDao?.insert(user);
                    Log.i("MYTAG", "USER IS ADDED")
                    edit1.text.clear();
                    edit2.text.clear();
                }
                catch(e: Exception){
                    Log.e("MYTAG",e.message.toString());
                }

            }
            Log.i("MYTAG","Second THIS ONE");
        }
    }
}