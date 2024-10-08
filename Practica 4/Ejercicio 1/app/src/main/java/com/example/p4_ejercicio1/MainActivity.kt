package com.example.p4_ejercicio1

// Descripción: Actividad principal que maneja los fragmentos de selección de imagen y visualización de imagen
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 28-09-2024
// Fecha de última modificación: 28-09-2024


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), FragmentSpinner.OnImageSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar el FragmentSpinner inicialmente
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentSpinner())
                .commit()
        }
    }

    // Implementación de la interfaz para manejar la imagen seleccionada
    override fun onImageSelected(selectedImage: Int) {
        val fragmentImage = FragmentImage.newInstance(selectedImage)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentImage)
            .addToBackStack(null)
            .commit()
    }
}