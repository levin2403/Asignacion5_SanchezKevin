package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import sanchez.kevin.mainapp.R
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var expression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.displayResultField)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btnPlus, R.id.btnSubstract,
            R.id.btnMultiply, R.id.btnDivide
        )

        val operators = mapOf(
            R.id.btnPlus to "+",
            R.id.btnSubstract to "-",
            R.id.btnMultiply to "*",
            R.id.btnDivide to "/"
        )

        // Manejo de números y operadores
        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                val value = if (id in operators) {
                    operators[id]
                } else {
                    findViewById<Button>(id).text.toString()
                }
                expression += value
                display.text = expression
            }
        }

        // Botón igual (=)
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            try {
                val engine = ScriptEngineManager().getEngineByName("rhino")
                val result = engine.eval(expression).toString()
                display.text = result
                expression = result
            } catch (e: Exception) {
                display.text = "Error"
                expression = ""
            }
        }

        // Logica de la limpieza de la calculadora
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            expression = ""
            display.text = ""
        }
    }
}
