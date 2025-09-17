package com.example.nike

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.Shoe
import com.example.nike.Data.ShoeDao
import com.example.nike.Data.ShopingDao
import com.example.nike.Fragments.Home_Fragment
import com.example.nike.Fragments.Like_Fragment
import com.example.nike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var shoeDao: ShoeDao
    lateinit var shopingDao: ShopingDao
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //create database
        //toolbar
        setSupportActionBar(binding.moduleToolbar.toolbar)

        shopingDao = MyDataBase.getDataBase(this).shopingDao
        shoeDao = MyDataBase.getDataBase(this).shoeDao
        val sharedPreferences = getSharedPreferences("duniFood", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("first_run", true)) {
            firstRun()
            sharedPreferences.edit().putBoolean("first_run", false).apply()
        }

        //first fragment
        firstFragment()





        // log out

        binding.moduleToolbar.btnLogOut.setOnClickListener {
            val intent = Intent(this , Login_Activity::class.java)
            startActivity(intent)
        }
        // bottom navigation view
        binding.btmNavhigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nothing -> {
                    return@setOnItemSelectedListener false
                }
                R.id.Like_bottom_navigation ->{
                    replaceFragment(Like_Fragment())
                }
                R.id.Home_bottom_navigation->{
                    replaceFragment(Home_Fragment())
                }
            }
            true
        }

        binding.shoppingCard.setOnClickListener {
            val intent = Intent(this , MainActivity3::class.java)
            startActivity(intent)
        }
        binding.shoppingCard2.setOnClickListener {
            val intent = Intent(this , MainActivity3::class.java)
            startActivity(intent)
        }
    }

    fun firstRun(){
        // سایز های کفش ها
        val size = arrayListOf("37", "38", "39", "40", "45", "43")
        val dataShoes = arrayListOf<Shoe>(
            Shoe(
                img_Shoe = R.drawable.img_shoes,
                shoe_Name = "NIKE AIR MAX 270",
                gender = true,
                price = 190,
                like = false,
                brand_Name = "Puma",
                size = size
            ), Shoe(
                img_Shoe = R.drawable.img_5,
                shoe_Name = "PUMA Unisex Inhale Essentials",
                gender = false,
                price = 96,
                brand_Name = "Puma",
                like = false,
                size = size
            ),
            Shoe(
                img_Shoe = R.drawable.img_shoes5,
                shoe_Name = "MBT-2000 II LACE UP ",
                gender = false,
                price = 90,
                brand_Name = "Nike",
                like = false,
                size = size
            ),
            Shoe(
                img_Shoe = R.drawable.img_97,
                shoe_Name = "MBT-2000 II LACE UP ",
                gender = false,
                price = 90,
                brand_Name = "Nike",
                like = false,
                size = size
            ),



        )
        shoeDao.insertAll(dataShoes.clone() as ArrayList<Shoe>)

    }

    private fun firstFragment(){
        replaceFragment(Home_Fragment())

    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentsLayout,fragment)
        transaction.commit()
    }

    override fun onResume() {
        super.onResume()

        if ((shopingDao.getAll(Id.id)).size > 0){
            binding.viewCounter.visibility = View.VISIBLE
            binding.txtCounter.text = shopingDao.getAll(Id.id).size.toString()
            binding.txtCounter.visibility = View.VISIBLE
        }
        if ((shopingDao.getAll(Id.id)).size < 1){
            binding.viewCounter.visibility = View.INVISIBLE
            binding.txtCounter.visibility = View.INVISIBLE
        }
    }
}