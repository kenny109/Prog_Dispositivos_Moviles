package com.example.p4_ejercicio2
// Descripción: Fragmento para mostrar el reproductor de música con controles de reproducción y una barra de progreso
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 28-09-2024
// Fecha de última modificación: 29-09-2024

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.p4_ejercicio2.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMusicPlayer : Fragment() {

    private lateinit var songImageView: ImageView
    private lateinit var songNameTextView: TextView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var backButton: Button
    private lateinit var seekBar: SeekBar
    private lateinit var mediaPlayer: MediaPlayer
    private var selectedSong = 0
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music_player, container, false)

        songImageView = view.findViewById(R.id.song_image_view)
        songNameTextView = view.findViewById(R.id.audio_name)
        playButton = view.findViewById(R.id.play_button)
        pauseButton = view.findViewById(R.id.pause_button)
        backButton = view.findViewById(R.id.back_button)
        seekBar = view.findViewById(R.id.seekBar)

        arguments?.let {
            selectedSong = it.getInt("selectedSong", 0)
        }

        // Asignar la imagen de la canción
        updateSongImage(selectedSong)

        // Inicializar el MediaPlayer según la canción seleccionada
        mediaPlayer = when (selectedSong) {
            0 -> MediaPlayer.create(context, R.raw.alan_walker_faded)
            1 -> MediaPlayer.create(context, R.raw.the_weeknd_blinding_lights)
            2 -> MediaPlayer.create(context, R.raw.the_weeknd_save_your_tears)
            3 -> MediaPlayer.create(context, R.raw.daddyyankee_con_calma)
            4 -> MediaPlayer.create(context, R.raw.ed_sheeran_shape_of_you)
            else -> MediaPlayer.create(context, R.raw.alan_walker_faded)
        }
        //Botón para reproducir la canción
        playButton.setOnClickListener {
            mediaPlayer.start()
            updateSeekBar()
        }
        //Botón para pausar la canción
        pauseButton.setOnClickListener {
            mediaPlayer.pause()
        }
        //Botón para volver
        backButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.release()
            requireActivity().supportFragmentManager.popBackStack()

        }


        // Configuración de la SeekBar
        seekBar.max = mediaPlayer.duration
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        return view
    }
// Método para actualizar el SeekBar mientras la canción se reproduce

    private fun updateSeekBar() {
        lifecycleScope.launch {
            while (true) {
                if (mediaPlayer?.isPlaying == true) {
                    withContext(Dispatchers.Main) {
                        seekBar.progress = mediaPlayer?.currentPosition ?: 0
                    }
                }
                delay(100) // Update every 100ms
            }
        }
    }
// Método para actualizar el nombre y la imagen de la canción
    private fun updateSongImage(selectedSong: Int) {
        when (selectedSong) {
            0 -> {
                songNameTextView.text = getString(R.string.song_alan_walker)
                songImageView.setImageResource(R.drawable.audio_image_1)
            }
            1 -> {
                songNameTextView.text = getString(R.string.song_blinding_lights)
                songImageView.setImageResource(R.drawable.audio_image_2)
            }
            2 -> {
                songNameTextView.text = getString(R.string.song_save_your_tears)
                songImageView.setImageResource(R.drawable.audio_image_3)
            }
            3 -> {
                songNameTextView.text = getString(R.string.song_con_calma)
                songImageView.setImageResource(R.drawable.audio_image_4)
            }
            4 -> {
                songNameTextView.text = getString(R.string.song_shape_of_you)
                songImageView.setImageResource(R.drawable.audio_image_5)
            }
        }
    }

    companion object {
        // Método para crear una nueva instancia del fragmento y pasar la canción seleccionada
        fun newInstance(selectedSong: Int): FragmentMusicPlayer {
            val fragment = FragmentMusicPlayer()
            val args = Bundle()
            args.putInt("selectedSong", selectedSong)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
