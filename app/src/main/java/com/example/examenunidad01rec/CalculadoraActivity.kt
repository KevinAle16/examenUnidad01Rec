package com.example.examenunidad01rec


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var calculadora: calculadora

    private lateinit var lblCalculadora: TextView
    private lateinit var lblUsuario: TextView
    private lateinit var lblResultado: TextView

    private lateinit var txtNum1: EditText
    private lateinit var txtNum2: EditText

    private lateinit var btnSuma: Button
    private lateinit var btnResta: Button
    private lateinit var btnMultiplicacion: Button
    private lateinit var btnDivision: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @SuppressLint("StringFormatInvalid")
    fun iniciarComponentes() {
        calculadora = calculadora()

        lblCalculadora = findViewById(R.id.txtTitulo)
        lblUsuario = findViewById(R.id.txtUsuario)
        lblResultado = findViewById(R.id.txtResultado)

        txtNum1 = findViewById(R.id.num1)
        txtNum2 = findViewById(R.id.num2)

        btnSuma = findViewById(R.id.btnSuma)
        btnResta = findViewById(R.id.btnResta)
        btnMultiplicacion = findViewById(R.id.btnMultiplicacion)
        btnDivision = findViewById(R.id.btnDivision)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)

        val usuario = intent.getStringExtra("usuario")
        lblUsuario.text = getString(R.string.user, usuario)
    }
    fun eventosClic() {
        btnSuma.setOnClickListener {
            if (obtenerDatos()) {
                val resultado = calculadora.suma()
                lblResultado.text =  getString(R.string.resultado_mostrar, resultado)
            }
        }

        btnResta.setOnClickListener {
            if (obtenerDatos()) {
                val resultado = calculadora.resta()
                lblResultado.text = getString(R.string.resultado_mostrar, resultado)
            }
        }

        btnMultiplicacion.setOnClickListener {
            if (obtenerDatos()) {
                val resultado = calculadora.multiplicacion()
                lblResultado.text =  getString(R.string.resultado_mostrar, resultado)
            }
        }

        btnDivision.setOnClickListener {
            if (obtenerDatos()) {
                val resultado = calculadora.division()
                lblResultado.text = if (resultado.isNaN()) {
                    getString(R.string.division_cero)
                } else {
                    getString(R.string.resultado_mostrar, resultado)
                }
            }
        }

        btnLimpiar.setOnClickListener {
            txtNum1.text.clear()
            txtNum2.text.clear()
            lblResultado.text = getString(R.string.resultado)
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun obtenerDatos(): Boolean {
        val num1Text = txtNum1.text.toString()
        val num2Text = txtNum2.text.toString()

        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_ingresa_numeros), Toast.LENGTH_SHORT).show()
            return false
        }

        calculadora.num1 = num1Text.toFloat()
        calculadora.num2 = num2Text.toFloat()
        return true
    }
}