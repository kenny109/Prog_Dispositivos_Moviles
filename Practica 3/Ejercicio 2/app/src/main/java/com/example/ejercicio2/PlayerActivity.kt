// Descripción: Actividad para reproducir el audio seleccionado con controles de reproducción
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 21-09-2024
// Fecha de última modificación: 21-09-2024

package com.example.ejercicio2

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlayerActivity : AppCompatActivity() {

    private lateinit var albumImageView: ImageView
    private lateinit var audioNameTextView: TextView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        albumImageView = findViewById(R.id.album_image)
        audioNameTextView = findViewById(R.id.audio_name)
        playButton = findViewById(R.id.play_button)
        pauseButton = findViewById(R.id.pause_button)
        stopButton = findViewById(R.id.stop_button)

        val selectedAudio = intent.getIntExtra("selectedAudio", 0)
        val selectedImage = intent.getIntExtra("selectedImage", R.drawable.default_image)
        val audioName = intent.getStringExtra("audioName") ?: "Audio"

        // Asignar imagen y nombre del audio
        albumImageView.setImageResource(selectedImage)
        audioNameTextView.text = audioName

        // Array de archivos de audio en res/raw
        val audioFiles = arrayOf(
            R.raw.alan_walker_faded,
            R.raw.the_weeknd_blinding_lights,
            R.raw.the_weeknd_save_your_tears,
            R.raw.daddyyankee_con_calma,
            R.raw.ed_sheeran_shape_of_you
        )

        // Inicializar MediaPlayer con el archivo de audio seleccionado
        mediaPlayer = MediaPlayer.create(this, audioFiles[selectedAudio])

        // Botón de reproducción
        playButton.setOnClickListener {
            mediaPlayer?.start()
        }

        // Botón de pausa
        pauseButton.setOnClickListener {
            mediaPlayer?.pause()
        }

        // Botón de detener
        stopButton.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer = MediaPlayer.create(this, audioFiles[selectedAudio])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
