package sanchez.kevin.mainapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import sanchez.kevin.mainapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var currentExpression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.displayResultField)

        val buttons = listOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8", R.id.btn9 to "9",
            R.id.btnPlus to "+", R.id.btnSubstract to "-",
            R.id.btnMultiply to "*", R.id.btnDivide to "/"
        )

        buttons.forEach { (id, symbol) ->
            findViewById<Button>(id).setOnClickListener {
                currentExpression += symbol
                display.text = currentExpression
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            currentExpression = ""
            display.text = ""
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            try {
                val expression = ExpressionBuilder(currentExpression).build()
                val result = expression.evaluate()
                display.text = result.toInt().toString()
                currentExpression = result.toString()
            } catch (e: Exception) {
                display.text = "Error"
                currentExpression = ""
            }
        }
    }
}

