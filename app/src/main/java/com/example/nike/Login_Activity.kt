package com.example.nike

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.User_Dao
import com.example.nike.databinding.ActivityLoginBinding

object Id {
    var id = 0
}

class Login_Activity : AppCompatActivity() {
    lateinit var userDao: User_Dao
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        userDao = MyDataBase.getDataBase(this).userDao

        binding.edtName.addTextChangedListener(object : TextWatcher {
            private var edtLength = 0
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    edtLength = p0.length
                } else
                    edtLength = 0
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var edtLengthNow = 0
                if (p0 != null) {
                    edtLengthNow = p0.length
                } else {
                    edtLengthNow = 0
                }
                if (edtLengthNow == 0) {
                    binding.edtNameLayout.setStartIconTintList(ColorStateList.valueOf(Color.GRAY))
                } else {

                    binding.edtNameLayout.setStartIconTintList(ColorStateList.valueOf(Color.BLACK))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.edtPass.addTextChangedListener(object : TextWatcher {
            private var edtLength = 0
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    edtLength = p0.length
                } else
                    edtLength = 0
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var edtLengthNow = 0
                if (p0 != null) {
                    edtLengthNow = p0.length
                } else {
                    edtLengthNow = 0
                }
                if (edtLengthNow == 0) {
                    binding.edtPassLayout.setEndIconTintList(ColorStateList.valueOf(Color.GRAY))
                    binding.edtPassLayout.setStartIconTintList(ColorStateList.valueOf(Color.GRAY))
                } else {
                    binding.edtPassLayout.setEndIconTintList(ColorStateList.valueOf(Color.BLACK))
                    binding.edtPassLayout.setStartIconTintList(ColorStateList.valueOf(Color.BLACK))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })


        binding.btnSingIn.setOnClickListener {
            val name = binding.edtName.text.toString()
            val pass = binding.edtPass.text.toString()

            if (name.length > 0) {
                val exist = userDao.exist(name)


                if (exist) {
                    val contact = userDao.login(name, pass)
                    if (contact != null) {
                        Id.id = contact.userId!!
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else
                        Toast.makeText(this, "رمز اشتباه است", Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(this, "چنین کاربری وجود ندارد", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "مشخصات را وارد کنید", Toast.LENGTH_SHORT).show()
            }

        }
        binding.frameSingIn.setOnClickListener {
            val intent =Intent(this , Sing_UpActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPass.setOnClickListener {
            val intent = Intent(this, Forgot_Password_Activity::class.java)
            startActivity(intent)
        }
    }
}
