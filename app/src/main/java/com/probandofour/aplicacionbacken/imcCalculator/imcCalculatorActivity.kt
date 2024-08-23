package com.probandofour.aplicacionbacken.imcCalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.probandofour.aplicacionbacken.R

class imcCalculatorActivity : AppCompatActivity() {

    //Definicion de variables
    private lateinit var ViewDots: CardView
    private lateinit var ViewConsulta: CardView

    private var isDotsSelected: Boolean = true
    private var isConsultSelected: Boolean = false

    //Definicion de variables para altura
    private lateinit var TvHeight : TextView
    private lateinit var RsSlider: RangeSlider

    private var CurrentHeight: Int = 120
    //Variable para peso
    private lateinit var ButtonPlusWeight: FloatingActionButton
    private lateinit var ButtonSubtractWeight: FloatingActionButton
    private lateinit var TvWeight: TextView

    private var CurrentWeight: Int = 50

    //Variables para Edad
    private lateinit var ButtonPlusAge: FloatingActionButton
    private lateinit var ButtonSubstractAge: FloatingActionButton
    private lateinit var TvAge: TextView

    private var CurrentAge: Int = 20
    //Variables para botón calcular
    private lateinit var ButtonCalculate: Button


    //---------------------------------OBJETOS-------------------------------
    companion object{
        const val IMC_KEY: String = "IMC_RESULT"
    }
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

    //Definicion de funciones para cuadros
    private fun initComponents(){
        ViewDots = findViewById(R.id.viewDots)
        ViewConsulta = findViewById(R.id.viewConsult)
        //Para altura
        TvHeight = findViewById(R.id.tvHeight)
        RsSlider = findViewById(R.id.rsHeight)

        //Para peso
        ButtonPlusWeight = findViewById(R.id.btnPlusWight)
        ButtonSubtractWeight = findViewById(R.id.btnSubtractWight)
        TvWeight = findViewById(R.id.tvWeight)

        //Para Edad
        ButtonPlusAge = findViewById(R.id.btnPlusAge)
        ButtonSubstractAge = findViewById(R.id.btnSubstractAge)
        TvAge = findViewById(R.id.tvAge)
        //Para boton calcular
        ButtonCalculate = findViewById(R.id.btnCalculate)

    }

    private fun changeGender(){
        isDotsSelected = !isDotsSelected
        isConsultSelected = !isDotsSelected
    }

    private fun initListeners(){
        //Para recuadros
        ViewDots.setOnClickListener{
            changeGender()
            setGenderColor()}
        ViewConsulta.setOnClickListener{
            changeGender()
            setGenderColor()}

        //Para la barra de la talla
        RsSlider.addOnChangeListener{_, value, _ ->
            val df = DecimalFormat("#.##")
            CurrentHeight = df.format(value).toInt()
            TvHeight.text = "$CurrentHeight cm"
        }

        // Para botones de suma o resta de peso
        ButtonPlusWeight.setOnClickListener{
            CurrentWeight ++
            setWeight()
        }
        ButtonSubtractWeight.setOnClickListener{
            CurrentWeight --
            setWeight()
        }

        //Para botones de Edad
        ButtonPlusAge.setOnClickListener{
            CurrentAge++
            setAge()
        }
        ButtonSubstractAge.setOnClickListener{
            CurrentAge--
            setAge()
        }
        //Para botón calcular
        ButtonCalculate.setOnClickListener{
            val result: Double = calculateIMC()
            //Devolver resultado en otra pantalla
            navigateToResult(result)

        }
    }

    private fun setGenderColor(){
        ViewDots.setCardBackgroundColor(getCardBackgroundColor(isDotsSelected))
        ViewConsulta.setCardBackgroundColor(getCardBackgroundColor(isConsultSelected))


    }
    private fun initUI(){
        setGenderColor()
        setWeight()
        setAge()
    }

    /*--------------------------------METODOS---------------------------------------------------*/
    //Para recuadros
    private fun getCardBackgroundColor(isSelectComponet: Boolean): Int{
        //Otra forma de traer el color
        //ContextCompat.getColor(this, androidx.cardview.R.color.cardview_light_background)

        val colorReference = if(isSelectComponet){
            R.color.AzulEncendido
        }else{
            R.color.AzulMarine
        }

        return ContextCompat.getColor(this, colorReference)
    }

    //Para cambiar peso
    private fun setWeight(){
        TvWeight.text = CurrentWeight.toString()
    }
    //Para cambiar edad
    private fun setAge(){
        TvAge.text = CurrentAge.toString()
    }
    //Para la función del botón calcular
    private fun calculateIMC(): Double{
        var df = DecimalFormat("#.##")
        var Imc: Double = CurrentWeight.toDouble() / (( CurrentHeight.toDouble() / 100.0) * (CurrentHeight.toDouble()/100.0))
        return df.format(Imc).toDouble()
        /*
        //Valor calculado para que se muestre en pantalla de dispositivo;
        Toast.makeText(this, "El imc es ${df.format(Imc).toDouble()}",Toast.LENGTH_SHORT).show()
        //Varlor calculado en memoria
        Log.i("list", "El imc es $Imc")

         */
    }

    private fun navigateToResult(result: Double){
        val intent = Intent( this, resultImcActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

}