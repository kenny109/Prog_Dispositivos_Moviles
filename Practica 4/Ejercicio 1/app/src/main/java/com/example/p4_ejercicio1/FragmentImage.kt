package com.example.p4_ejercicio1

// Descripción: Fragmento para mostrar la imagen seleccionada y un botón para regresar al fragmento anterior
// Autor: Kenny Luis Flores Chacón
// Fecha de creación: 21-09-2024
// Fecha de última modificación: 28-09-2024

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentImage : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var backButton: Button
    private var selectedImage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_image, container, false)

        imageView = view.findViewById(R.id.selected_image)
        backButton = view.findViewById(R.id.back_button)

        // Obtener el argumento que contiene la imagen seleccionada
        arguments?.let {
            selectedImage = it.getInt("selectedImage", 0)
        }

        // Asignar la imagen correspondiente
        when (selectedImage) {
            0 -> imageView.setImageResource(R.drawable.imagen1)
            1 -> imageView.setImageResource(R.drawable.imagen2)
            2 -> imageView.setImageResource(R.drawable.imagen3)
        }

        // Botón para volver al fragmento anterior
        backButton.setOnClickListener {
            // Navegar de vuelta al fragmento anterior (FragmentSpinner)
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    companion object {
        // Método estático para crear una nueva instancia de FragmentImage con argumentos
        fun newInstance(selectedImage: Int): FragmentImage {
            val fragment = FragmentImage()
            val args = Bundle()
            args.putInt("selectedImage", selectedImage)
            fragment.arguments = args
            return fragment
        }
    }
}
