package com.faisal.dc.apprise.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faisal.dc.apprise.R
import com.faisal.dc.apprise.databinding.RowLayoutMovieBinding
import com.faisal.dc.apprise.model.Search

class HomeMovieAdapter (private var list : List<Search>): RecyclerView.Adapter<HomeMovieAdapter.ViewHolder>() {

    class ViewHolder(binding: RowLayoutMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val moviePoster : ImageView = binding.ivMovie
        val movieTitle : TextView = binding.tvMovieTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val  binding: RowLayoutMovieBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.row_layout_movie,parent,false)
      return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.moviePoster).load(list.get(position).Poster).into(holder.moviePoster)
        holder.movieTitle.text=list.get(position).Title.toString()
    }

    fun updateData(list: List<Search>) {
        this.list = list
        notifyDataSetChanged()
    }
}