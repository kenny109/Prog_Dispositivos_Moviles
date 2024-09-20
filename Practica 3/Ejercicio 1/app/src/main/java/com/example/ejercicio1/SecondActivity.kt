package com.example.ejercicio1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class SecondActivity(contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {

    private lateinit var imageView: ImageView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imageView = findViewById(R.id.selected_image)
        backButton = findViewById(R.id.back_button)

        val selectedImage = intent.getIntExtra("selectedImage", 0)

        when (selectedImage) {
            0 -> imageView.setImageResource(R.drawable.imagen1)
            1 -> imageView.setImageResource(R.drawable.imagen2)
            2 -> imageView.setImageResource(R.drawable.imagen3)
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}