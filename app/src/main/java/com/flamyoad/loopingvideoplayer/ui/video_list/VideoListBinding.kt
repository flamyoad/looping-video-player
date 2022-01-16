package com.flamyoad.loopingvideoplayer.ui.video_list

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flamyoad.loopingvideoplayer.model.Video

@BindingAdapter("videoList")
fun bindRecyclerView(recyclerView: RecyclerView, list: List<Video>?) {
    val adapter = recyclerView.adapter as VideoListAdapter
    adapter.submitList(list)
}

@RequiresApi(Build.VERSION_CODES.Q)
@BindingAdapter("imageUri")
fun bindImage(imageView: ImageView, uri: Uri) {
    Glide.with(imageView)
        .load(uri)
        .dontTransform()
        .into(imageView)
}