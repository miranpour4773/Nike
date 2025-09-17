package com.example.nike

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nike.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val scaleX = ObjectAnimator.ofFloat(binding.imgBrand, "scaleX", 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.imgBrand, "scaleY", 0f, 1f)
        scaleX.duration = 2000
        scaleY.duration = 2000

        val animSet = AnimatorSet()
        animSet.playTogether(scaleY,scaleX)
        animSet.start()

        binding.btnStart.setOnClickListener {
           val intent = Intent(this , Login_Activity ::class.java)
            startActivity(intent)

        }
    }
}