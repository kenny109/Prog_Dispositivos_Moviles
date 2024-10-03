package com.example.p5_ejercicio1
// Descripción: Este es el fragmento que muestra la lista de audios.
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 02-10-2024
// Fecha de última modificación: 03-10-2024
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AudioListFragment : Fragment() {

    private lateinit var audioAdapter: AudioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_audio_list, container, false)

        val audioList = listOf(
            AudioItem("Alan Walker - Faded", R.drawable.audio_image_1, R.raw.alan_walker_faded, "3:32"),
            AudioItem("The Weeknd - Blinding Lights", R.drawable.audio_image_2, R.raw.the_weeknd_blinding_lights, "4:22"),
            AudioItem("The Weeknd - Save Your Tears", R.drawable.audio_image_3, R.raw.the_weeknd_save_your_tears, "4:09"),
            AudioItem("Daddy Yankee - Con Calma", R.drawable.audio_image_4, R.raw.daddyyankee_con_calma, "3:30"),
            AudioItem("Ed Sheeran - Shape of You", R.drawable.audio_image_5, R.raw.ed_sheeran_shape_of_you, "3:55")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.audio_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        audioAdapter = AudioAdapter(audioList) { audioItem ->
            val fragment = AudioPlayerFragment.newInstance(audioItem.audioName, audioItem.audioImage, audioItem.audioFile)
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }
        recyclerView.adapter = audioAdapter

        return view
    }
}
