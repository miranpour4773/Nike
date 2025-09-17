package com.example.nike

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.Activty2.Adapter.ColorAdapter
import com.example.nike.Data.ColorShoes
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.Shoe
import com.example.nike.Data.ShoeDao
import com.example.nike.Data.ShopingDao
import com.example.nike.Data.Shopping_Card
import com.example.nike.Fragments.Key
import com.example.nike.databinding.ActivityMain2Binding
import com.google.android.material.chip.Chip
import java.util.Random



class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var data: Shoe
    lateinit var shopingDao: ShopingDao
    lateinit var shoeDao: ShoeDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        shopingDao = MyDataBase.getDataBase(this).shopingDao
        shoeDao = MyDataBase.getDataBase(this).shoeDao


        val first = intent.getParcelableExtra<Bundle>(Key)!!
        data = first.getParcelable("key1")!!
        val color = first.getInt("key2")
        binding.imageView.setColorFilter(color)

        window.statusBarColor = color
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setBackgroundColor(color)
        supportActionBar!!.title = ""
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.frameShop.setOnClickListener {
            val intent1 = Intent(this, MainActivity3::class.java)
            startActivity(intent1)
        }


        var sizeOfShoe: Int? = null


        initUi()

        binding.imgBtnLike.setOnClickListener {
            data.like = !data.like // مقدار را برعکس می‌کنیم

            // حالا بر اساس مقدار جدید، آیکون را تغییر می‌دهیم
            if (data.like) {
                binding.imgBtnLike.setImageResource(R.drawable.ic_with_fill_color)
            } else {
                binding.imgBtnLike.setImageResource(R.drawable.ic_without_fill_color)
            }

            // به‌روزرسانی در دیتابیس
            shoeDao.updateShoe(data)
        }


        data.size.forEach {
            val chip =
                LayoutInflater.from(this).inflate(R.layout.chip, binding.chipGroup, false) as Chip
            chip.id = Random().nextInt()
            chip.text = it.toString()
            chip.setOnClickListener {
                sizeOfShoe = chip.text.toString().toInt()
            }
            binding.chipGroup.addView(chip)
            binding.chipGroup.isSingleSelection = true
        }


        binding.btnBuy.setOnClickListener {
            if (sizeOfShoe == null) {
                Toast.makeText(this, "you don't pick any size", Toast.LENGTH_SHORT).show()
            } else {
                val buy = Shopping_Card(
                    brand_Name = data.brand_Name,
                    shoe_Name = data.shoe_Name,
                    gender = data.gender,
                    like = data.like,
                    price = data.price,
                    img_Shoe = data.img_Shoe,
                    sizee = sizeOfShoe!!,
                    count = 1 ,
                    userid = Id.id,
                    shoeid = data.shoeId!!
                )
                val exist = shopingDao.getAll(Id.id).any {
                    it.shoe_Name == buy.shoe_Name && it.sizee == buy.sizee
                }
                if (exist){
                    Toast.makeText(this, "you have it in bag", Toast.LENGTH_SHORT).show()
                }else{
                    shopingDao.insertAll(buy)
                }
                checkCounter()

            }
        }

    }

    private fun checkCounter() {
        if (shopingDao.getAll(Id.id).size > 0) {
            binding.txtCounter.text = shopingDao.getAll(Id.id).size.toString()
            binding.txtCounter.visibility = View.VISIBLE
            binding.viewCounter.visibility = View.VISIBLE

        } else {
        }
    }


    private fun initUi() {
        binding.txtNameShoes.text = data.shoe_Name
        binding.txtPrice.text = "$" + data.price.toString()
        Glide
            .with(this)
            .load(data.img_Shoe)
            .into(binding.imgShoes)

        if (data.gender) {
            binding.txtGender.text = "Men's Shoes"
        } else {
            binding.txtGender.text = "Women's Shoes"
        }

        if (data.like) {
            binding.imgBtnLike.setImageResource(R.drawable.ic_with_fill_color)
        } else {
            binding.imgBtnLike.setImageResource(R.drawable.ic_without_fill_color)
        }

        val arraylistColor = arrayListOf(
            ColorShoes("#DB4141"),
            ColorShoes("#415DDB"),
        )
        binding.recyclerViewColor.adapter = ColorAdapter(arraylistColor)
        binding.recyclerViewColor.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    override fun onResume() {
        super.onResume()
      checkCounter()
    }
}