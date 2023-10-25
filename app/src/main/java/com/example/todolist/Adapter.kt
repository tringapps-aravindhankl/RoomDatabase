package com.example.todolist

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Adapter(var list: List<User>,var userDao: UserDao,var context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var username: TextView = view.findViewById(R.id.textview1)
        var password: TextView =view.findViewById(R.id.textview2)
        var button: Button =view.findViewById(R.id.delete);
        var updatebutton: Button=view.findViewById(R.id.update)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.user_details,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.i("MYTAG",list.size.toString()  )
        return list.size;

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note=list[position]
        holder.username.text=note.UserName;
        holder.password.text=note.Password;

        holder.button.setOnClickListener() {
            deleteData(position)
        }

        holder.updatebutton.setOnClickListener{
            updateData(position)
        }
    }

    private fun deleteData(position: Int){
        var user=list[position]
        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteUser(user);
        }
    }

    private fun updateData(position: Int){
        var user=list[position]
        Log.i("ANSWER",user.serialNo.toString())
        var intent = Intent(context,update::class.java)
        intent.putExtra("userName",user.UserName)
        intent.putExtra("passWord",user.Password)
        intent.putExtra("position",user.serialNo.toString())
        context.startActivity(intent);
    }
}