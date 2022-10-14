package com.faisal.dc.apprise.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faisal.dc.apprise.BannerDataModel
import com.faisal.dc.apprise.R
import com.faisal.dc.apprise.databinding.RowLayoutBannerBinding

import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView

class CarouselAdapter (private var list : List<BannerDataModel>): RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    class ViewHolder(binding: RowLayoutBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        val image : ReflectionImageView = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val  binding: RowLayoutBannerBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.row_layout_banner,parent,false)
      return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.image).load(list.get(position).urlLink).into(holder.image)
    }

    fun updateData(list: List<BannerDataModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}