package com.flamyoad.loopingvideoplayer.ui.folder_list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flamyoad.loopingvideoplayer.model.Folder

@BindingAdapter("setAdapter")
fun bindRecyclerview(recyclerView: RecyclerView, rvAdapter: RecyclerView.Adapter<*>) {
    with(recyclerView) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(recyclerView.context)
        adapter = rvAdapter
    }
}

@BindingAdapter("folderList")
fun bindRecyclerView(recyclerView: RecyclerView, list: List<Folder>?) {
    val adapter = recyclerView.adapter as FolderListAdapter
    adapter.submitList(list)
}