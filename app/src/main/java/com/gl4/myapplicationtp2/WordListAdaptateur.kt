package com.gl4.myapplicationtp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class studentAdapter(val StudentAfficher : Array<Student>,val listener: (studentAdapter) -> Unit)
    : RecyclerView.Adapter<studentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = StudentAfficher.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.textView.text= StudentAfficher[position].nom
        if(StudentAfficher[position].genre=="h")
            holder.image.setImageResource(R.drawable.h)
        else{
            holder.image.setImageResource(R.drawable.f)
        }
    }

    class ViewHolder(val elementDeListe : View) : RecyclerView.ViewHolder(elementDeListe)
    {
        val textView: TextView
        init {
            textView=elementDeListe.findViewById(R.id.textView)}
        var image:ImageView

        init {
            image=elementDeListe.findViewById(R.id.imageView)
        }
        fun bind(Student: Student, listener: (Student) -> Unit) = with(itemView)
        {

        }
    }

}