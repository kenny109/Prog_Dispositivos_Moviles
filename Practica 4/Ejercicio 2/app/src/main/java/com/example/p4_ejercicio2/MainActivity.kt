package com.example.p4_ejercicio2

// Descripción: Actividad principal que maneja los fragmentos de selección de canción y reproducción de música
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 28-09-2024
// Fecha de última modificación: 28-09-2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentSongSelection.OnSongSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar el FragmentSongSelection inicialmente
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentSongSelection())
                .commit()
        }
    }

    // Implementación de la interfaz para manejar la canción seleccionada
    override fun onSongSelected(selectedSong: Int) {
        val fragmentMusicPlayer = FragmentMusicPlayer.newInstance(selectedSong)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentMusicPlayer)
            .addToBackStack(null)  // Permitir volver atrás
            .commit()
    }
}
