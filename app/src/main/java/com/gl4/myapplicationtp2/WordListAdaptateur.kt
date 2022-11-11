package com.gl4.myapplicationtp2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class studentAdapter(var StudentAfficher : ArrayList<Student>)
    : RecyclerView.Adapter<studentAdapter.ViewHolder>() ,Filterable{



    var dataFilterList = ArrayList<Student>()
    init {
        // associer le tableau des données initiales
        dataFilterList = StudentAfficher
    }
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = StudentAfficher.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = StudentAfficher[position].nom
        if (StudentAfficher[position].genre == "h")
            holder.image.setImageResource(R.drawable.h)
        else {
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

    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = StudentAfficher
                } else {
                    val resultList = ArrayList<Student>()
                    for (student in StudentAfficher) {
                        if (student.nom.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }

        }
    }


}