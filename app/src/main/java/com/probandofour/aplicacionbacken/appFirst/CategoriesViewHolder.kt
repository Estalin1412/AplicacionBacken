package com.probandofour.aplicacionbacken.appFirst

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.probandofour.aplicacionbacken.R

class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val tvCategoryNam: TextView = view.findViewById(R.id.tvCategories)
    private val divider: View = view.findViewById(R.id.vDivider)

    private val viewContainer:CardView = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategryOfFirst, onItemSelected: (Int) -> Unit){
        tvCategoryNam.text = "EJEMPLO"

        val color = if(!
            taskCategory.isSelected){
            R.color.ClareBlue
        }else{
            R.color.AzulMarine
        }

        viewContainer.setBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener{
            onItemSelected(layoutPosition)
        }

        when(taskCategory){
            TaskCategryOfFirst.objCategory -> {
                tvCategoryNam.text = "Negocios"
                divider.setBackgroundColor(

                    ContextCompat.getColor(divider.context, R.color.purple)
                )
            }
            TaskCategryOfFirst.objPersonal -> {
                tvCategoryNam.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context , R.color.black)
                )
            }
            TaskCategryOfFirst.objOthers -> {
                tvCategoryNam.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context , R.color.RedWine)

                )
            }

        }

    }

}