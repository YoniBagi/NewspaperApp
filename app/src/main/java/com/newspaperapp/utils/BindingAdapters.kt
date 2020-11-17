package com.newspaperapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(view.context)
                .load(it)
                .override(100, 200)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(view)
        }
    }
}