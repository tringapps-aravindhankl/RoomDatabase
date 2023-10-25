package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var edit1: EditText = findViewById(R.id.editTextText);
        var edit2: EditText = findViewById(R.id.editTextText2);
        var button: Button = findViewById(R.id.button);
        var database = MyDatabase?.getInstance(this)
        var userDao = database?.userDao();
        Log.i("MYTAG", "ENTER")

        button.setOnClickListener {
            var username = edit1.text.toString()
            var password = edit2.text.toString()
            var user = User(username, password, serialNo = 115)



            GlobalScope.launch {
                try {
                    userDao?.insert(user);
                    Log.i("MYTAG", "USER IS ADDED")
                    edit1.text.clear();
                    edit2.text.clear();
                } catch (e: Exception) {
                    Log.e("MYTAG", e.message.toString());
                }
            }
        }

            Log.i("MYTAG", "BUTTON IS ENTER")
            var recyclerview: RecyclerView = findViewById(R.id.recycleview);

//
            recyclerview.setHasFixedSize(true);
            recyclerview.layoutManager = LinearLayoutManager(this)

            CoroutineScope(Dispatchers.IO).launch {

                var datalist = userDao?.getAllData();
                Log.i("MYTAG", datalist.toString());
                Log.i("MYTAG", "ISENTER")
                var adapter = datalist?.let {
                    userDao?.let { it1 ->
                        Adapter(it, it1,this@MainActivity)
                    }
                }
//                val adapter=Adapter(datalist,userDao,this)
                Log.i("MYTAG", adapter.toString())
                Log.i("MYTAG", "ADAPTER")
                recyclerview.adapter = adapter
                adapter?.notifyDataSetChanged()
        }

    }

}

