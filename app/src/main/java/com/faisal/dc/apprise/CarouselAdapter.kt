package com.faisal.dc.apprise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView

class CarouselAdapter (private var list : List<DataModel>): RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ReflectionImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.row_layout_banner, parent,false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.image).load(list.get(position).urlLink).into(holder.image)
    }

    fun updateData(list: List<DataModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}