package com.example.todolist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update)

        var name:EditText=findViewById(R.id.username)
        var word:EditText=findViewById(R.id.password)
        var button: Button =findViewById(R.id.update1);


        var username=intent.getStringExtra("userName");
        var password=intent.getStringExtra("passWord");
        var position=intent.getStringExtra("position")


        Log.i("ANSWER",username.toString());
        Log.i("ANSWER",password.toString());
        Log.i("ANSWER",position.toString());



        name.setText(username.toString())
        word.setText(password.toString())

        button.setOnClickListener{
            try{
            Log.i("ANSWER",name.text.toString());
            Log.i("ANSWER",position.toString())
            var name=name.text.toString();
            var database=MyDatabase.getInstance(this)
                var userDao = database?.userDao();
                CoroutineScope(Dispatchers.IO).launch {
                    userDao?.updateData(position.toString(), name)
                }
            }catch (e:Exception){
                Log.e("ANSWER", "Update failed: ${e.message}")
            }


        }



    }
}