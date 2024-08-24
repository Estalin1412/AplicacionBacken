package com.probandofour.aplicacionbacken.appFirst

import android.app.Dialog
import android.os.Bundle
import android.security.identity.PersonalizationData
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.probandofour.aplicacionbacken.R

class appFirstActivity : AppCompatActivity() {

    private val tasks = mutableListOf(
        Task("PruebaBussines" , TaskCategryOfFirst.objCategory),
        Task("PruebaPersonal", TaskCategryOfFirst.objPersonal),
        Task("PruebaOthers", TaskCategryOfFirst.objOthers)

    )

    private val categories = listOf(

        TaskCategryOfFirst.objCategory,
        TaskCategryOfFirst.objPersonal,
        TaskCategryOfFirst.objOthers


    )
    //Para categorias
    private lateinit var RecViewCategories : RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    //Para tareas
    private lateinit var RecViewTask: RecyclerView

    private lateinit var taskAdapter: TasksAdapter
    //Para boton de agregar tares
    private lateinit var fabAddTask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_first)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        InitComponents()
        InitListenerComponets()
        InitUI()

    }

    private fun InitComponents(){
        RecViewCategories = findViewById(R.id.rvCategories)
        RecViewTask = findViewById(R.id.rvTask)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun InitListenerComponets(){
        fabAddTask.setOnClickListener{
            showDialog()
        }

    }

    private fun InitUI(){


        //Adapter para categorias
        categoriesAdapter = CategoriesAdapter(categories){position -> updateCategories(position)}
        RecViewCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        RecViewCategories.adapter = categoriesAdapter

        //Adapter para tareas
        taskAdapter = TasksAdapter(tasks) {position -> onItemSelection(position)}
        RecViewTask.layoutManager = LinearLayoutManager(this)
        RecViewTask.adapter = taskAdapter

        //Para botón de agregar tares

    }
/* ------------------------------PARA MÉTODOS DE OBJETOS------------------------------- */
    private fun showDialog(){
        val dialog = Dialog(this)
    dialog.setContentView(R.layout.dialog_task)

    val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
    val etTask: EditText = dialog.findViewById(R.id.edTask)
    val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

     btnAddTask.setOnClickListener{
         val currentTask = etTask.text.toString()

         if(currentTask.isNotEmpty()) {
             val selectedId = rgCategories.checkedRadioButtonId
             val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
             val currentCategory: TaskCategryOfFirst = when (selectedRadioButton.text) {
                 "Negocios" -> TaskCategryOfFirst.objCategory
                 "Personal" -> TaskCategryOfFirst.objPersonal
                 else -> TaskCategryOfFirst.objOthers

             }

             tasks.add(Task(currentTask, currentCategory))

             updateTasks()
             dialog.hide()
         }
     }

    dialog.show()



    }
    //Para avisar al adaptador que hay nuevos items
    private fun updateTasks(){
        val selectedCategories: List<TaskCategryOfFirst> = categories.filter{
            it.isSelected
        }

        val newTasks = tasks.filter{selectedCategories.contains((it.category))}

        taskAdapter.tasks = newTasks
        taskAdapter.notifyDataSetChanged()
    }
    //Funciones lamda
    private fun onItemSelection(position: Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
    }
}