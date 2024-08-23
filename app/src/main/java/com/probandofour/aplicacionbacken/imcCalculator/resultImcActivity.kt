package com.probandofour.aplicacionbacken.imcCalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.probandofour.aplicacionbacken.R
import com.probandofour.aplicacionbacken.imcCalculator.imcCalculatorActivity.Companion.IMC_KEY

class resultImcActivity : AppCompatActivity() {

    private lateinit var TvResult: TextView
    private lateinit var TvIMC: TextView
    private lateinit var TvDescription: TextView
    private lateinit var ButtonRecalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imc)


        val result: Double = intent.extras?.getDouble(IMC_KEY)?:-1.0


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initUI(result)
        initListeners()

    }

    private fun initComponents(){
        TvResult = findViewById(R.id.tvResult)
        TvIMC = findViewById(R.id.tvIMC)
        TvDescription = findViewById(R.id.tvDescription)
        ButtonRecalculate = findViewById(R.id.btnRecalculate)


    }

    private fun initListeners(){
        ButtonRecalculate.setOnClickListener{
            onBackPressed()
        }
    }

    private fun initUI(result: Double){
        TvIMC.text = result.toString()
        when(result){
            in 0.00 .. 18.50 ->{//Bajo de peso
                TvResult.text = getString(R.string.Title_bajo_peso)
                TvDescription.text = getString(R.string.Description_bajo_peso)
            }
            in 18.51 .. 24.99 -> {
                TvResult.text = getString(R.string.Title_peso_normal)
                TvDescription.text = getString(R.string.Description_peso_normal)
            }
            in 25.00 .. 29.99 ->{
                TvResult.text = getString(R.string.Title_sobrepeso)
                TvDescription.text = getString(R.string.Description_sobrepeso)
            }
            in 30.00 .. 99.00 ->{
                TvResult.text = getString(R.string.Title_sobrepeso)
                TvDescription.text = getString(R.string.Description_sobrepeso)
            }
            else -> {
                TvIMC.text = "error"
                TvResult.text = "error"
                TvDescription.text = " error"
            }
        }

    }


}