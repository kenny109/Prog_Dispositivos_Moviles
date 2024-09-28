package com.example.p4_ejercicio1

// Descripción: Actividad principal con spinner para seleccionar imágenes y un botón para pasar a la siguiente actividad
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 19-09-2024
// Fecha de última modificación: 21-09-2024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class SecondActivity: AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var backButton: Button
    private var selectedImage= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imageView = findViewById(R.id.selected_image)
        backButton = findViewById(R.id.back_button)

        if (savedInstanceState != null) {
            selectedImage = savedInstanceState.getInt("selectedImage")
        } else {
            selectedImage = intent.getIntExtra("selectedImage", 0)
        }
        when (selectedImage) {
            0 -> imageView.setImageResource(R.drawable.imagen1)
            1 -> imageView.setImageResource(R.drawable.imagen2)
            2 -> imageView.setImageResource(R.drawable.imagen3)
        }


        backButton.setOnClickListener {
            finish()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selectedImage", selectedImage)
    }
}
