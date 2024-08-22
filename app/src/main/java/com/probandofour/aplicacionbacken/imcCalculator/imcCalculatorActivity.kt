package com.probandofour.aplicacionbacken.imcCalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.probandofour.aplicacionbacken.R

class imcCalculatorActivity : AppCompatActivity() {

    //Definicion de variables
    private lateinit var ViewDots: CardView
    private lateinit var ViewConsulta: CardView

    private var isDotsSelected: Boolean = true
    private var isConsultSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Métodos
        initComponents()
        initListeners()
        initUI()

    }

    //Definicion de funciones
    private fun initComponents(){
        ViewDots = findViewById(R.id.viewDots)
        ViewConsulta = findViewById(R.id.viewConsult)
    }

    private fun initListeners(){
        ViewDots.setOnClickListener{}
        ViewConsulta.setOnClickListener{}
    }
    private fun setGenderColor(){
        ViewDots.setCardBackgroundColor(getCardBackgroundColor(isDotsSelected))
        ViewConsulta.setCardBackgroundColor(getCardBackgroundColor(isConsultSelected))
    }

    

    private fun getCardBackgroundColor(isSelectComponet: Boolean): Int{
        //Otra forma de traer el color
        //ContextCompat.getColor(this, androidx.cardview.R.color.cardview_light_background)

        val colorReference = if(isSelectComponet){
            androidx.cardview.R.color.cardview_light_background
        }else{
            androidx.cardview.R.color.cardview_dark_background
        }

        return ContextCompat.getColor(this, colorReference)

    }


    private fun initUI(){
        setGenderColor()
    }
}