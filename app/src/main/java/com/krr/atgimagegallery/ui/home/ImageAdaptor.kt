package com.krr.atgimagegallery.ui.home

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.krr.atgimagegallery.R
import com.krr.atgimagegallery.resource.network.Photo

class ImageAdaptor(photo: List<Photo>) : RecyclerView.Adapter<ImageAdaptor.ViewHolder>() {

    var photos : List<Photo> = photo
    lateinit var context : Context

    val options = RequestOptions()
            .centerCrop()
            .priority(Priority.HIGH)

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image_view_remote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_image, parent, false))
    }

    override  fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
                .load(Uri.parse(photos[position].urls))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.image)
    }

    override fun getItemCount() : Int{
      Log.d("Response", photos.size.toString())
      return photos.size
    }

    fun updateAdaptor(photo: List<Photo>){
        this.photos = photo
        notifyDataSetChanged()
    }
}