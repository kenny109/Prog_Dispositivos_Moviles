package com.example.p5_ejercicio1
// Descripción: Esta es la actividad principal que carga los fragments.
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 02-10-2024
// Fecha de última modificación: 03-10-2024
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AudioListFragment())
                .commit()
        }
    }
}