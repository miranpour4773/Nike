package com.example.nike.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.AdaptersOfHome.NewReleaseAdapter
import com.example.nike.AdaptersOfHome.ShoeAdapter
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.NewRelease
import com.example.nike.Data.Shoe
import com.example.nike.Data.ShoeDao
import com.example.nike.MainActivity2
import com.example.nike.R
import com.example.nike.databinding.HomeFragmentBinding

class Home_Fragment : Fragment(), ShoeAdapter.ItemSelcet {
    lateinit var binding: HomeFragmentBinding
    lateinit var dataNewrelease: ArrayList<NewRelease>
    lateinit var shoeDao: ShoeDao
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)

        shoeDao = MyDataBase.getDataBase(requireContext()).shoeDao
        firstRun()

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewShoes)

        return binding.root
    }

    private fun firstRun() {
        dataNewrelease = arrayListOf(
            NewRelease(R.drawable.img_shoes, "Nike"),
            NewRelease(R.drawable.img_shoes, "Adidas"),
            NewRelease(R.drawable.img_shoes, "Puma"),
        )


        newreleaseAdapter(dataNewrelease)


        shoeAdapter(ArrayList(shoeDao.getAllShoes()))

    }

    private fun newreleaseAdapter(dataNewrelease: java.util.ArrayList<NewRelease>) {
        val adapterNewRelease = NewReleaseAdapter(dataNewrelease)
        binding.recyclerViewNewRelease.adapter = adapterNewRelease
        binding.recyclerViewNewRelease.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    private fun shoeAdapter(data: ArrayList<Shoe>) {
        val adapterShoe = ShoeAdapter(data, this)
        binding.recyclerViewShoes.adapter = adapterShoe
        binding.recyclerViewShoes.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val edtText = requireActivity().findViewById<EditText>(R.id.btnSearch)
//        edtText.addTextChangedListener {
//            if (it!!.length > 0) {
//
//
//                val filterlist =
//                    (ArrayList(shoeDao.getAllShoes()).clone() as ArrayList<Shoe>).filter { shoeItem ->
//                        (shoeItem.shoe_Name.contains(it))
//
//                    }
//                shoeAdapter(ArrayList(filterlist))
//
//
//            }else{
//                shoeAdapter(ArrayList(shoeDao.getAllShoes()))
//            }
//        }
        selectAnyChip()
    }

    private fun selectAnyChip() {
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            // زمانی که هیچ کدام انتخاب نشده اند
            if (group.checkedChipId == -1) {
                shoeAdapter(ArrayList(shoeDao.getAllShoes()))
            }
            // زمانی که هرکدام انتخاب شوند
            when (group.checkedChipId) {
                R.id.chipPopular -> {
                    Toast.makeText(requireContext(), "asfsdgagasg", Toast.LENGTH_SHORT).show()

                }
                // جمسیت مرد
                R.id.menChip -> {
                   val dataMen = ArrayList(shoeDao.getAllShoes()).filter { it.gender == true }
                    shoeAdapter(ArrayList(dataMen))
                }
                // جنسیت زن
                R.id.womenChip -> {
                    val dataWomen = ArrayList(shoeDao.getAllShoes()).filter { it.gender == false }
                    shoeAdapter(ArrayList(dataWomen))
                }

            }
        }
    }

    override fun fastRequest() {

    }

    override fun onItemSelected(pos: Int, color: Int) {
        val intent = Intent(requireActivity(), MainActivity2::class.java)
        val bundle = Bundle()
        Log.v("qrrrwr", color.toString())
        bundle.putParcelable("key1" , shoeDao.getAllShoes()[pos])
        bundle.putInt("key2" , color)
        intent.putExtra(Key, bundle)
        startActivity(intent)
    }


}

const val Key = " Send Data"