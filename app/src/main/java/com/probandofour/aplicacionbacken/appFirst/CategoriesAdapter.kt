package com.probandofour.aplicacionbacken.appFirst

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.probandofour.aplicacionbacken.R

class CategoriesAdapter(private val categories: List<TaskCategryOfFirst>, private val onItemSelected:(Int)-> Unit): RecyclerView.Adapter<CategoriesViewHolder>() {


    //Crea una vista visual y pasar esa vista, hasta que se cree la lista, es como un delay visual
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
    }
    //Para obtener la tamaño de cotegoriass
    override fun getItemCount() = categories.size



    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position], onItemSelected)

    }


}