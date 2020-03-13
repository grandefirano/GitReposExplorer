package com.grandefirano.gitreposexplorer.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.grandefirano.gitreposexplorer.R
import com.squareup.picasso.Picasso


@BindingAdapter("imageUrl")
fun bindAdapterPhoto(imageView: ImageView, imageUrl: String) {
    if (imageUrl != null) {
        // If we don't do this, you'll see the old image appear briefly
        // before it's replaced with the current image
        Picasso.with(imageView.context).load(imageUrl).into(imageView)
    }
}
