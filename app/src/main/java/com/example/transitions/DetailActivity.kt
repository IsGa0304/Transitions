package com.example.transitions

import android.os.Bundle
import android.transition.TransitionInflater
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.sharedElementEnterTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.imagen_transition)
        window.sharedElementReturnTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.imagen_transition)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView: ImageView = findViewById(R.id.fullScreenImageView)
        val imageResId = intent.getIntExtra("IMAGE_ID", 0)

        if (imageResId != 0) {
            imageView.setImageResource(imageResId)
        }
    }
}