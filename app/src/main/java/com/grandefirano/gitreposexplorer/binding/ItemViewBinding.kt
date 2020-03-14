package com.grandefirano.gitreposexplorer.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("imageUrl")
fun bindAdapterPhoto(imageView: ImageView, imageUrl: String) {
    if (imageUrl != null) {
        // If we don't do this, you'll see the old image appear briefly
        // before it's replaced with the current image
        Picasso.with(imageView.context).load(imageUrl).into(imageView)
    }
}
@BindingAdapter("textDateFormat")
fun bindAdapterDateText(textView: TextView,date:String){
    if(date!=null){

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val output: String = formatter.format(parser.parse("2018-12-14T09:55:00Z"))
        textView.text=output
    }

}
