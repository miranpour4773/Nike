package com.example.nike.AdaptersOfHome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.Data.NewRelease
import com.example.nike.databinding.LayoutNewReleaseBinding

class NewReleaseAdapter(val data : ArrayList<NewRelease>):RecyclerView.Adapter<NewReleaseAdapter.NewReleaseViewHolder>() {
    lateinit var binding: LayoutNewReleaseBinding
    inner class NewReleaseViewHolder(binding: LayoutNewReleaseBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(position: Int){
            binding.txtBrandName.text =data[position].name

            Glide
                .with(binding.root)
                .load(data[position].img)
                .into(binding.imgBrand)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewReleaseViewHolder {
        binding = LayoutNewReleaseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewReleaseViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NewReleaseViewHolder, position: Int) {
        holder.bindData(position)
    }
}