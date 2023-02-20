package com.example.imcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.imcapp.MainActivity.Companion.IMC_KEY

@Suppress("DEPRECATION")
class ResultActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: 1.0
        initComponents()
        initUI(result)
        initListeners()

    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            this.onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> { // bajo peso
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescripcion.text = getString(R.string.descrition_bajo_peso)
            }
            in 18.51..24.99 -> { // Peso normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDescripcion.text = getString(R.string.descrition_peso_normal)
            }
            in 25.00..29.99 -> { //Sobre pespo
                tvResult.text = getString(R.string.title_sobre_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                tvDescripcion.text = getString(R.string.descrition_sobre_peso)
            }
            in 30.00..99.00 -> { //Obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescripcion.text = getString(R.string.descrition_obesidad)
            }
            else -> { // Error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescripcion.text = getString(R.string.error)
            }
        }

    }

    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}