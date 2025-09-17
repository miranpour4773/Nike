package com.example.nike.Activty2.Adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.Data.ColorShoes
import com.example.nike.databinding.ColorLayoutBinding

class ColorAdapter(val data : ArrayList<ColorShoes>):RecyclerView.Adapter<ColorAdapter.ColorViewHolder> (){
    lateinit var binding: ColorLayoutBinding
    inner class ColorViewHolder(binding: ColorLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun binData(position: Int){
            val color = Color.parseColor(data[position].hexColorShoes)
            binding.view.backgroundTintList = ColorStateList.valueOf(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        binding = ColorLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ColorViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.binData(position)
    }
}