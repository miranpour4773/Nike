package com.example.nike.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.AdaptersOfHome.ShoeAdapter
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.Shoe
import com.example.nike.Data.ShoeDao
import com.example.nike.Data.ShopingDao
import com.example.nike.Data.Shopping_Card
import com.example.nike.LikeAdapter
import com.example.nike.Shopping_Adapter
import com.example.nike.databinding.FragmentLikeBinding

class Like_Fragment : Fragment(),LikeAdapter.ItemSelcet{
    lateinit var shoeDao: ShoeDao
    lateinit var binding: FragmentLikeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         shoeDao= MyDataBase.getDataBase(container!!.context).shoeDao
        binding = FragmentLikeBinding.inflate(layoutInflater)



        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayListLike: ArrayList<Shoe> = arrayListOf()
        shoeDao.getAllShoes().forEach {
            if (it.like)
                arrayListLike.add(it)
        }
        val myAdapter = LikeAdapter(arrayListLike,this)
        binding.recyclerLike.adapter = myAdapter
        binding.recyclerLike.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
    }

    override fun fastRequest() {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(pos: Int, color: Int) {
        TODO("Not yet implemented")
    }


}