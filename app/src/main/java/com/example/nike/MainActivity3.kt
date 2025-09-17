package com.example.nike

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.ShopingDao
import com.example.nike.databinding.ActivityMain3Binding

object total{
    var totalPrice = 0
}
class MainActivity3 : AppCompatActivity(),Shopping_Adapter.ChangePrice {
    lateinit var binding :ActivityMain3Binding
    lateinit var shopingDao: ShopingDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shopingDao = MyDataBase.getDataBase(this).shopingDao

        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val myAdapter = Shopping_Adapter(ArrayList(shopingDao.getAll(Id.id)),this)
        binding.reCyclerShopping.adapter = myAdapter
        binding.reCyclerShopping.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    }

    override fun increase(price: Int) {
        total.totalPrice += price
        binding.txtTotalPrice.text = "$" +total.totalPrice.toString()
    }

    override fun decrease(price: Int) {
        total.totalPrice-=price
        binding.txtTotalPrice.text ="$" + total.totalPrice.toString()
    }

}
