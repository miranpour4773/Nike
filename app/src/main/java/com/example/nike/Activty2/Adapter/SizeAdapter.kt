package com.example.nike.Activty2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Size
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.R
import com.example.nike.databinding.ShoesSizeLayoutBinding

class SizeAdapter(val data: ArrayList<Int>) : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {
    lateinit var binding: ShoesSizeLayoutBinding
    private var selectedPosition = -1

    inner class SizeViewHolder(binding: ShoesSizeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(pos: Int) {
            binding.chipSize.text = data[pos].toString()

            if(binding.chipSize.isChecked){
                binding.chipSize.isChecked = false
            }else{
                for (i in 0..data.size-1){
                    if (i == pos){
                        binding.chipSize.isChecked=true
                        break
                    }
                    binding.chipSize.isChecked =false
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        binding = ShoesSizeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.bindData(position)
    }

}