// Descripción: Actividad principal con spinner para seleccionar un audio y un botón para pasar a la siguiente actividad
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 21-09-2024
// Fecha de última modificación: 21-09-2024

package com.example.ejercicio2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var selectButton: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.audio_spinner)
        selectButton = findViewById(R.id.select_button)
        imageView = findViewById(R.id.audio_image)

        // Array de nombres de audio
        val audioNames = arrayOf(
            getString(R.string.audio_1),
            getString(R.string.audio_2),
            getString(R.string.audio_3),
            getString(R.string.audio_4),
            getString(R.string.audio_5)
        )

        // Array de IDs de imágenes correspondientes a cada audio
        val audioImages = arrayOf(
            R.drawable.audio_image_1,
            R.drawable.audio_image_2,
            R.drawable.audio_image_3,
            R.drawable.audio_image_4,
            R.drawable.audio_image_5
        )

        // Adaptador para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, audioNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Cambiar la imagen cada vez que se seleccione un audio diferente
        spinner.setOnItemSelectedListener {
            val selectedPosition = spinner.selectedItemPosition
            imageView.setImageResource(audioImages[selectedPosition])
        }

        // Botón de selección para ir a PlayerActivity
        selectButton.setOnClickListener {
            val selectedAudio = spinner.selectedItemPosition
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("selectedAudio", selectedAudio)
            intent.putExtra("selectedImage", audioImages[selectedAudio])
            intent.putExtra("audioName", audioNames[selectedAudio])
            startActivity(intent)
        }
    }
}
