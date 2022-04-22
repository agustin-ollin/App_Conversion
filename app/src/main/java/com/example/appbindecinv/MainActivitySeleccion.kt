package com.example.appbindecinv

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

/**
 * Activity para seleccionar el número que será convertido a las diferentes bases
 */
class MainActivitySeleccion : AppCompatActivity() {
    // Variables a utilizar para referenciar a los botones de la interfaz
    lateinit var opcionDecimal: Button
    lateinit var opcionBinario: Button
    lateinit var opcionOctal: Button
    lateinit var opcionHexadecimal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_seleccion)

        eventButton()
    }

    /**
     * Función para generar los eventos de acuerdo con el botón que se presione
     */
    private fun eventButton() {
        opcionDecimal = findViewById(R.id.btnDecimal)
        opcionBinario = findViewById(R.id.btnBinario)
        opcionOctal = findViewById(R.id.btnOctal)
        opcionHexadecimal = findViewById(R.id.btnHexadecimal)

        opcionDecimal.setOnClickListener {
            lanzarVentanaDeOperaciones(0, "Decimal")
        }

        opcionBinario.setOnClickListener {
            lanzarVentanaDeOperaciones(1, "Binario")
        }

        opcionOctal.setOnClickListener {
            lanzarVentanaDeOperaciones(2, "Octal")
        }

        opcionHexadecimal.setOnClickListener {
            lanzarVentanaDeOperaciones(3, "Hexadecimal")
        }
    }

    /**
     * Función para lanzar activity de operaciones
     */
    fun lanzarVentanaDeOperaciones(opcion: Int, base: String){
        var intent: Intent = Intent(this, MainActivity::class.java)
        intent.putExtra("base", opcion)
        intent.putExtra("titulo", base)
        startActivity(intent)
        overridePendingTransition(R.anim.left_in, R.anim.left_out)
    }

    /**
     * Función sobreescrita para cerrar la aplicación en caso de presionar la tecla BACK del telefono en el principal Activity
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("Desea salir de la aplicación?").setPositiveButton("Si", DialogInterface.OnClickListener { dialogInterface, i ->
                var intento: Intent = Intent(Intent.ACTION_MAIN)
                intento.addCategory(Intent.CATEGORY_HOME)
                intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intento)
            }).setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })

            builder.show()
        }
        return super.onKeyDown(keyCode, event)
    }
}