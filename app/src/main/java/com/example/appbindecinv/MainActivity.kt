package com.example.appbindecinv

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat

/**
 * Activity para realizar las conversiones y mostrar resultados
 */
class MainActivity : AppCompatActivity() {
    // Variables que referencian a los elementos de la interfaz
    //lateinit var listaOpciones: Spinner
    lateinit var button: Button
    lateinit var labelTitulo: TextView

    var opcion: Int = 0
    var titulo: String = ""

    /**
     * Función OnCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var extra: Bundle? = intent.extras
        opcion = extra!!.getInt("base")
        titulo = extra!!.getString("titulo").toString()

        changeTitleAndBackground(titulo, opcion)
        eventButton(opcion, titulo)
    }

    /**
     * Función para cambiar el title y background de la aplicación
     */
    fun changeTitleAndBackground(titulo: String, option: Int){
        labelTitulo = findViewById(R.id.textTitulo)
        labelTitulo.text = titulo

        var fondo: Drawable? = null
        val label: ConstraintLayout = findViewById(R.id.constrainLayout)

        when(option) {
            0 -> {
                fondo = resources.getDrawable(R.drawable.decbackground)
            }
            1 -> {
                fondo = resources.getDrawable(R.drawable.binbackground)
            }
            2 -> {
                fondo = resources.getDrawable(R.drawable.octbackground)
            }
            3 -> {
                fondo = resources.getDrawable(R.drawable.hexbackground)
            }
        }

        label.background = fondo
    }

    /**
     * Función para agregar evento al botón de la interfaz
     */
    fun eventButton(opcion: Int, operation: String) {
        button = findViewById(R.id.button)
        button.setOnClickListener {
            var editTNum: EditText = findViewById(R.id.numTransf)
            var numero = editTNum.text.toString()
            if(comprobarValores(opcion, numero)){

                realizarOperacion(numero, opcion, operation)
            } else {
                Toast.makeText(this, "El número a convertir debe ser de tipo: $titulo", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Método para comprobar el tipo de número que se ha ingresado en el TextView
     */
    fun comprobarValores(opcion: Int, edit: String): Boolean{
        var band = true
        val abc = "A".."Z"
        val numRange = "2".."9"
        val octRange = "8".."9"

        when{
            0 == opcion -> {
                edit.forEach {
                    if (it.toString() in abc){
                        band = false
                    }
                }
            }
            1 == opcion -> {
                edit.forEach {
                    if ((it.toString() in abc) || (it.toString() in numRange)){
                        band = false
                    }
                }
            }
            2  == opcion-> {
                edit.forEach {
                    if ((it.toString() in abc) || (it.toString() in octRange)){
                        band = false
                    }
                }
            }
            else -> {println("Sin Errores")}
        }

        return band
    }

    /**
     * Función para mostrar el resultado en pantalla
     */
    fun mostrarResultado(dec: String, bin: String, oct: String, hex: String) {
        val decimal: TextView = findViewById(R.id.resDecimal)
        val binario: TextView = findViewById(R.id.resBinario)
        val octal: TextView = findViewById(R.id.resOctal)
        val hexadecimal: TextView = findViewById(R.id.resHexadecimal)

        decimal.setText(dec)
        binario.setText(bin)
        octal.setText(oct)
        hexadecimal.setText(hex)
    }

    /**
     * Realiza las operaciones y aplica restricciones de acuerdo con las operaciones que se quieren realizar
     */
    fun realizarOperacion(num: String, option: Int, operation: String) {
        var decimal = ""
        var binario = ""
        var octal = ""
        var hexadecimal = ""

        when(option) {
            0 -> {
                decimal = num
                binario = convertirDeDecimalABinario(decimal.toInt())
                octal = convertirDeDecimalAOctal(decimal.toInt())
                hexadecimal = convertirDeDecimalAHexadecimal(decimal.toInt())
                sendToast(operation)
            }
            1 -> {
                binario = num
                decimal = convertirDeBinarioADecimal(binario.toInt()).toString()
                octal = convertirDeDecimalAOctal(decimal.toInt())
                hexadecimal = convertirDeDecimalAHexadecimal(decimal.toInt())
                sendToast(operation)
            }
            2 -> {
                octal = num
                decimal = convertirDeOctalADecimal(octal).toString()
                binario = convertirDeDecimalABinario(decimal.toInt())
                hexadecimal = convertirDeDecimalAHexadecimal(decimal.toInt())
                sendToast(operation)
            }
            3 -> {
                hexadecimal = num
                decimal = convertirDeHexadecimalADecimal(hexadecimal).toString()
                binario = convertirDeDecimalABinario(decimal.toInt())
                octal = convertirDeDecimalAOctal(decimal.toInt())
                sendToast(operation)
            }
            else -> {
                Toast.makeText(this, "Ninguna Opción", Toast.LENGTH_LONG).show()
            }
        }

        mostrarResultado(decimal, binario, octal, hexadecimal)
    }

    /**
     * Envia mensaje del tipo de conversión que se realizará
     */
    fun sendToast(operation: String){
        Toast.makeText(this, "Transformación de $operation", Toast.LENGTH_LONG).show()
    }

}