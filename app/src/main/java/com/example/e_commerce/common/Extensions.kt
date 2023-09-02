package com.example.e_commerce.common

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url : String){
   Picasso.get().load(url).into(this)
}