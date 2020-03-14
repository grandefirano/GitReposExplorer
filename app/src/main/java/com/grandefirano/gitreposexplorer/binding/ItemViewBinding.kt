package com.grandefirano.gitreposexplorer.binding

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.getJsonDataFromAsset
import com.squareup.picasso.Picasso
import java.lang.reflect.Type
import java.text.SimpleDateFormat


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
@BindingAdapter("shapeColor")
fun bindAdapterShapeColor(imageView: ImageView,language:String){
    if(language!=null){

        val tex=getJsonDataFromAsset(imageView.context,"colors.json")

        val type: Type = object :
            TypeToken<Map<String?, String?>?>() {}.type
        val myMap: Map<String, String> =
            Gson().fromJson(tex, type)

        when(myMap[language]){
            null->imageView.visibility=View.GONE
            else->{

                imageView.visibility=View.VISIBLE
                imageView.setColorFilter(Color.parseColor(myMap[language]),
                    PorterDuff.Mode.SRC_ATOP)
                imageView.setImageResource(R.drawable.shape_circle)
            }
        }
    }
}
