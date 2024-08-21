package com.probandofour.aplicacionbacken

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    public lateinit var boton: Button
    public lateinit var vText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        vText = findViewById(R.id.IdUsuario)
        boton = findViewById(R.id.button)

        boton.setOnClickListener{
            val variableTexto =vText.text.toString()
            Toast.makeText(this, "Bienvenido ${variableTexto}!!", Toast.LENGTH_SHORT).show()

            if(variableTexto.isNotEmpty()){
                val intent = Intent(this, ResultActivity2::class.java)
                intent.putExtra("EXTRA_NAME",variableTexto)
                startActivity(intent)
            }
        }
    }
}