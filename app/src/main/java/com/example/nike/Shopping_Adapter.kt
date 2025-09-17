package com.example.nike

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.ShopingDao
import com.example.nike.Data.Shopping_Card
import com.example.nike.databinding.CardOfShoppingCartBinding

class Shopping_Adapter(val data: ArrayList<Shopping_Card>, val changePrice: ChangePrice) :
    RecyclerView.Adapter<Shopping_Adapter.ShoppingViewHolder>() {
    lateinit var binding: CardOfShoppingCartBinding
    lateinit var shopingDao: ShopingDao

    inner class ShoppingViewHolder(val binding: CardOfShoppingCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            shopingDao = MyDataBase.getDataBase(itemView.context).shopingDao

            val item = data[position]
            binding.shoeName.text = item.shoe_Name
            binding.txtSize.text = item.sizee.toString()
            binding.txtCounter.text = item.count.toString()
            val price = item.price * item.count
            binding.txtPrice.text = "$" + price.toString()
            changePrice.increase(item.price)

            Glide.with(binding.root)
                .load(item.img_Shoe)
                .into(binding.imageView2)


            binding.increase.setOnClickListener {
                item.count += 1
                shopingDao.updateShoe(item)
                val updatedPrice = item.price * item.count
                binding.txtCounter.text = item.count.toString()
                binding.txtPrice.text = "$" + updatedPrice.toString()
                changePrice.increase(item.price)
            }

            binding.decrease.setOnClickListener {
                if (item.count >= 1) {
                    item.count -= 1
                    binding.txtCounter.text = item.count.toString()
                    val updatedPrice = item.price * item.count
                    binding.txtCounter.text = item.count.toString()
                    binding.txtPrice.text = "$" + updatedPrice.toString()
                    shopingDao.updateShoe(item)
                }
                if (item.count == 0) {
                    shopingDao.deleteShoe(item)
                    data.remove(item)
                    notifyItemRemoved(position)
                }
                if (data.size == 0) {
                    Toast.makeText(itemView.context, "checkout is clear", Toast.LENGTH_SHORT).show()
                }
                binding.txtCounter.text = item.count.toString()
                changePrice.decrease(item.price)

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        binding =
            CardOfShoppingCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        holder.bindData(position)
    }

    interface ChangePrice {
        fun increase(price: Int)
        fun decrease(price: Int)
    }
}