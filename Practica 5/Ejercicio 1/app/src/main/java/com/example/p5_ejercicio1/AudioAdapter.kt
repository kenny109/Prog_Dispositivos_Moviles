package com.example.p5_ejercicio1
// Descripción: Este archivo gestiona el adaptador para el
// RecyclerView que muestra la lista de audios.
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 02-10-2024
// Fecha de última modificación: 03-10-2024
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AudioAdapter(
    private val audioList: List<AudioItem>,
    private val listener: (AudioItem) -> Unit
) : RecyclerView.Adapter<AudioAdapter.AudioViewHolder>() {

    class AudioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.audio_image)
        val nameView: TextView = view.findViewById(R.id.audio_name)
        val durationView: TextView = view.findViewById(R.id.audio_duration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.audio_item, parent, false)
        return AudioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audioItem = audioList[position]
        holder.nameView.text = audioItem.audioName
        holder.imageView.setImageResource(audioItem.audioImage)
        holder.durationView.text = audioItem.duration
        holder.itemView.setOnClickListener { listener(audioItem) }
    }

    override fun getItemCount() = audioList.size
}
