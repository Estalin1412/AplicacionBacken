package com.probandofour.aplicacionbacken

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.probandofour.aplicacionbacken.imcCalculator.imcCalculatorActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        //Variables
        lateinit var btnSaludAppMenu: Button
        lateinit var btnIMCapp: Button

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Asociacion de variable
        btnSaludAppMenu = findViewById(R.id.btnSaludAppMenu)
        btnIMCapp= findViewById(R.id.btnIMCapp)
        //Usar métodos
        btnSaludAppMenu.setOnClickListener{ navigateUpToSaludApp() }
        btnIMCapp.setOnClickListener{
            navigateToIMCapp()
        }


    }
    //Crear Funciones
    private fun navigateToIMCapp(){
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateUpToSaludApp(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}