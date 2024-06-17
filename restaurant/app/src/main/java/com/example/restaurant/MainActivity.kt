package com.example.restaurant
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etPastelCantidad: EditText
    private lateinit var etCazuelaCantidad: EditText
    private lateinit var tvPastelTotal: TextView
    private lateinit var tvCazuelaTotal: TextView
    private lateinit var tvComidaTotal: TextView
    private lateinit var switchPropina: Switch
    private lateinit var tvPropina: TextView
    private lateinit var tvTotal: TextView

    private val pastelPrecio = 12000
    private val cazuelaPrecio = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPastelCantidad = findViewById(R.id.etPastelCantidad)
        etCazuelaCantidad = findViewById(R.id.etCazuelaCantidad)
        tvPastelTotal = findViewById(R.id.tvPastelTotal)
        tvCazuelaTotal = findViewById(R.id.tvCazuelaTotal)
        tvComidaTotal = findViewById(R.id.tvComidaTotal)
        switchPropina = findViewById(R.id.switchPropina)
        tvPropina = findViewById(R.id.tvPropina)
        tvTotal = findViewById(R.id.tvTotal)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcularTotales()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        etPastelCantidad.addTextChangedListener(textWatcher)
        etCazuelaCantidad.addTextChangedListener(textWatcher)
        switchPropina.setOnCheckedChangeListener { _, _ ->
            calcularTotales()
        }

        calcularTotales()
    }

    private fun calcularTotales() {
        val pastelCantidad = etPastelCantidad.text.toString().toIntOrNull() ?: 0
        val cazuelaCantidad = etCazuelaCantidad.text.toString().toIntOrNull() ?: 0

        val pastelTotal = pastelCantidad * pastelPrecio
        val cazuelaTotal = cazuelaCantidad * cazuelaPrecio
        val comidaTotal = pastelTotal + cazuelaTotal

        val propina = if (switchPropina.isChecked) (comidaTotal * 0.1).toInt() else 0
        val total = comidaTotal + propina

        val formatter = DecimalFormat("#,###")
        formatter.decimalFormatSymbols = DecimalFormatSymbols.getInstance(Locale("es", "CL"))

        tvPastelTotal.text = "Total: CLP ${formatter.format(pastelTotal)}"
        tvCazuelaTotal.text = "Total: CLP ${formatter.format(cazuelaTotal)}"
        tvComidaTotal.text = "Comida: CLP ${formatter.format(comidaTotal)}"
        tvPropina.text = "Propina: CLP ${formatter.format(propina)}"
        tvTotal.text = "TOTAL: CLP ${formatter.format(total)}"
    }
}
