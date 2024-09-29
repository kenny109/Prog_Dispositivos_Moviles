package com.example.p4_ejercicio2
// Descripción: Fragmento para mostrar el reproductor de música con controles de reproducción y una barra de progreso
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 28-09-2024
// Fecha de última modificación: 28-09-2024

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.p4_ejercicio2.R

class FragmentMusicPlayer : Fragment() {

    private lateinit var songImageView: ImageView
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

        playButton.setOnClickListener {
            mediaPlayer.start()
            updateSeekBar()
        }

        pauseButton.setOnClickListener {
            mediaPlayer.pause()
        }

        backButton.setOnClickListener {
            mediaPlayer.stop()
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

    private fun updateSeekBar() {
        seekBar.progress = mediaPlayer.currentPosition
        if (mediaPlayer.isPlaying) {
            handler.postDelayed({ updateSeekBar() }, 1000)
        }
    }

    private fun updateSongImage(selectedSong: Int) {
        when (selectedSong) {
            0 -> songImageView.setImageResource(R.drawable.audio_image_1)
            1 -> songImageView.setImageResource(R.drawable.audio_image_2)
            2 -> songImageView.setImageResource(R.drawable.audio_image_3)
            3 -> songImageView.setImageResource(R.drawable.audio_image_4)
            4 -> songImageView.setImageResource(R.drawable.audio_image_5)
        }
    }

    companion object {
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
