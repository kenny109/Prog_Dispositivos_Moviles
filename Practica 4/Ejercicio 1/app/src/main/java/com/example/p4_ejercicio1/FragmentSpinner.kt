package com.example.p4_ejercicio1
// Descripción: Fragmento que muestra un spinner para seleccionar una imagen y un botón para continuar
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 28-09-2024
// Fecha de última modificación: 28-09-2024

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment

class FragmentSpinner : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var nextButton: Button
    private var listener: OnImageSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_spinner, container, false)

        spinner = view.findViewById(R.id.image_spinner)
        nextButton = view.findViewById(R.id.next_button)

        val imageNames = arrayOf(
            getString(R.string.image_1),
            getString(R.string.image_2),
            getString(R.string.image_3)
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, imageNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        nextButton.setOnClickListener {
            val selectedImage = spinner.selectedItemPosition
            listener?.onImageSelected(selectedImage)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnImageSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnImageSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnImageSelectedListener {
        fun onImageSelected(selectedImage: Int)
    }
}
