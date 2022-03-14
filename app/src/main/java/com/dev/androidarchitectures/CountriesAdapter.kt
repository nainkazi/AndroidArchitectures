package com.dev.androidarchitectures


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater


class CountriesAdapter(list: ArrayList<String>, onclick:OnClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val listitem = list
    var onClick= onclick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.countires_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(holder is MyViewHolder){
           holder.countrieName.text = listitem.get(position)
           holder.view.setOnClickListener{
               onClick.setOnItem(position,listitem)
           }
       }
    }

    public fun clearData(){
        listitem.clear()
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
          return listitem.size
    }

    override fun getItemViewType(position: Int): Int {

        return super.getItemViewType(position)
    }

    internal class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var countrieName: TextView = itemView.findViewById(R.id.country_name)
        var view: View = itemView.findViewById<View?>(R.id.view)
    }

    interface OnClick{
        fun setOnItem(position:Int,arr:ArrayList<String>)
    }
}


