package com.example.p4_ejercicio2

// Descripción: Fragmento que muestra un spinner para seleccionar una canción y un botón para continuar
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 28-09-2024
// Fecha de última modificación: 28-09-2024

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment

class FragmentSongSelection : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var nextButton: Button
    private lateinit var songImageView: ImageView
    private var listener: OnSongSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_song_selection, container, false)

        spinner = view.findViewById(R.id.song_spinner)
        nextButton = view.findViewById(R.id.next_button)
        songImageView = view.findViewById(R.id.song_image_view)

        val songNames = arrayOf(
            getString(R.string.song_alan_walker),
            getString(R.string.song_blinding_lights),
            getString(R.string.song_save_your_tears),
            getString(R.string.song_con_calma),
            getString(R.string.song_shape_of_you)
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, songNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Mostrar la imagen según la canción seleccionada
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                updateSongImage(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        nextButton.setOnClickListener {
            val selectedSong = spinner.selectedItemPosition
            listener?.onSongSelected(selectedSong)
        }

        return view
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSongSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSongSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnSongSelectedListener {
        fun onSongSelected(selectedSong: Int)
    }
}
