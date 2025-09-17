package com.example.nike

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.Data.Shoe
import com.example.nike.Data.Shopping_Card
import com.example.nike.databinding.LayoutShoesBinding
import kotlin.random.Random

class LikeAdapter(val data: ArrayList<Shoe>, val itemSelcet: ItemSelcet) : RecyclerView.Adapter<LikeAdapter.ShoeViewHolder>()  {
    lateinit var binding: LayoutShoesBinding

    inner class ShoeViewHolder(binding: LayoutShoesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            binding.txtNameShoes.text = data[position].shoe_Name
            binding.txtPrice.text = "$" + data[position].price.toString()

            if (data[position].gender) {
                binding.txtGender.text = "Men's Shoes"
            } else
                binding.txtGender.text = "Women's Shoes"

            val color = generateRandomColor()
            binding.view.setBackgroundColor(color)

            Glide
                .with(binding.root)
                .load(data[position].img_Shoe)
                .into(binding.imgShoesLayout)

            binding.cardViewShoess.setOnClickListener {
                itemSelcet.onItemSelected(position,color)
            }
            binding.imgFastRequest.setOnClickListener {
                itemSelcet.fastRequest()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        binding = LayoutShoesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.bindData(position)
    }
    private fun generateRandomColor(): Int {
        val random = Random // استفاده از Random داخلی کاتلین
        val red = random.nextInt(80,200)    // 0-255
        val green = random.nextInt(180)  // 0-255
        val blue = random.nextInt(100)   // 0-255

        // ترکیب مولفه‌های RGB برای ساخت یک رنگ
        return Color.rgb(red, green, blue)
    }

    interface ItemSelcet{
        fun fastRequest()
        fun onItemSelected(pos:Int, color: Int)
    }
}