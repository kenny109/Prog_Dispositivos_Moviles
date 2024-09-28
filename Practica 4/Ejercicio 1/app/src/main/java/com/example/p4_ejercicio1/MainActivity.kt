package com.example.p4_ejercicio1

// Descripción: Actividad principal con spinner para seleccionar imágenes
// y un botón para pasar a la siguiente actividad usando fragments
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 19-09-2024
// Fecha de última modificación: 21-09-2024

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.image_spinner)
        nextButton = findViewById(R.id.next_button)

        val imageNames = arrayOf(
            getString(R.string.image_1),
            getString(R.string.image_2),
            getString(R.string.image_3)
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, imageNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        nextButton.setOnClickListener {
            val selectedImage = spinner.selectedItemPosition
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("selectedImage", selectedImage)
            startActivity(intent)
        }

        if (savedInstanceState != null) {
            spinner.setSelection(savedInstanceState.getInt("spinnerPosition"))
        }
    }
    //Función para guardar la instancia
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("spinnerPosition", spinner.selectedItemPosition)
    }
}
