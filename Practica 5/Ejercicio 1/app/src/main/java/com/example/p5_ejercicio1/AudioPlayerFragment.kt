package com.example.p5_ejercicio1


import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

class AudioPlayerFragment : Fragment() {

    private lateinit var songImageView: ImageView
    private lateinit var audioNameTextView: TextView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var seekBar: SeekBar
    private var mediaPlayer: MediaPlayer? = null
    private var audioFile: Int = 0
    private var updateSeekBar: Runnable? = null
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val view = inflater.inflate(R.layout.fragment_audio_player, container, false)

        // Inicializar las vistas
        songImageView = view.findViewById(R.id.song_image_view)
        audioNameTextView = view.findViewById(R.id.audio_name)
        playButton = view.findViewById(R.id.play_button)
        pauseButton = view.findViewById(R.id.pause_button)
        stopButton = view.findViewById(R.id.stop_button)
        seekBar = view.findViewById(R.id.seekBar)

        // Obtener el audio y los datos de la imagen del bundle
        val audioName = arguments?.getString(ARG_AUDIO_NAME) ?: ""
        val audioImage = arguments?.getInt(ARG_AUDIO_IMAGE) ?: 0
        audioFile = arguments?.getInt(ARG_AUDIO_FILE) ?: 0

        // Establecer la información en las vistas
        audioNameTextView.text = audioName
        songImageView.setImageResource(audioImage)

        // Crear el media player
        mediaPlayer = MediaPlayer.create(requireContext(), audioFile)

        // Configurar la barra de progreso
        handler = Handler(Looper.getMainLooper())
        seekBar.max = mediaPlayer?.duration ?: 0
        updateSeekBar = object : Runnable {
            override fun run() {
                mediaPlayer?.let {
                    seekBar.progress = it.currentPosition
                    handler.postDelayed(this, 1000)
                }
            }
        }

        // Listener para actualizar la SeekBar mientras el usuario la manipula
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Detener la actualización de la SeekBar mientras el usuario la ajusta
                handler.removeCallbacks(updateSeekBar!!)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Reanudar la actualización de la SeekBar cuando el usuario termina de ajustar
                mediaPlayer?.let {
                    seekBar?.progress = it.currentPosition
                    handler.postDelayed(updateSeekBar!!, 1000)
                }
            }
        })

        // Configurar los botones de reproducción
        playButton.setOnClickListener {
            mediaPlayer?.start()
            handler.postDelayed(updateSeekBar!!, 0)
        }

        pauseButton.setOnClickListener {
            mediaPlayer?.pause()
        }

        stopButton.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.prepare()
            seekBar.progress = 0
            handler.removeCallbacks(updateSeekBar!!)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Liberar el media player cuando el fragmento se destruya
        mediaPlayer?.release()
        mediaPlayer = null
        handler.removeCallbacks(updateSeekBar!!)
    }

    // Manejo del botón de "back" para que regrese al fragmento anterior sin cerrar la app
    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        private const val ARG_AUDIO_NAME = "audio_name"
        private const val ARG_AUDIO_IMAGE = "audio_image"
        private const val ARG_AUDIO_FILE = "audio_file"

        fun newInstance(audioName: String, audioImage: Int, audioFile: Int): AudioPlayerFragment {
            val fragment = AudioPlayerFragment()
            val args = Bundle()
            args.putString(ARG_AUDIO_NAME, audioName)
            args.putInt(ARG_AUDIO_IMAGE, audioImage)
            args.putInt(ARG_AUDIO_FILE, audioFile)
            fragment.arguments = args
            return fragment
        }
    }
}

