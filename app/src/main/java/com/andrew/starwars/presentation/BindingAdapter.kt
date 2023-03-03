package com.andrew.starwars.presentation

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.andrew.starwars.R


@BindingAdapter("src")
fun loadImage(imageView: ImageView, drawable: Int) {
    imageView.setImageResource(drawable)
}

@SuppressLint("ResourceAsColor")
@BindingAdapter("backgroundColor")
fun backgroundColor(view: View, color: Int) {
    view.setBackgroundResource(color)
}


@BindingAdapter("load")
fun image(imageView: ImageView, url: String?) {
    if (url != null) {
        imageView.load(url) {
            crossfade(true)
            placeholder(R.drawable.baseline_person_24)
            transformations(CircleCropTransformation())
        }
    }
}
