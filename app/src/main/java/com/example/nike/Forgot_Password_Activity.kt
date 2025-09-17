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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.User_Dao
import com.example.nike.databinding.ActivityForgotPasswordBinding

class Forgot_Password_Activity : AppCompatActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    lateinit var userDao: User_Dao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
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
        binding.edtConfirmPass.addTextChangedListener(object : TextWatcher {
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
                    binding.edtConfirmPassLayout.setEndIconTintList(ColorStateList.valueOf(Color.GRAY))
                    binding.edtConfirmPassLayout.setStartIconTintList(ColorStateList.valueOf(Color.GRAY))
                } else {
                    binding.edtConfirmPassLayout.setEndIconTintList(ColorStateList.valueOf(Color.BLACK))
                    binding.edtConfirmPassLayout.setStartIconTintList(ColorStateList.valueOf(Color.BLACK))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.btnChangePass.setOnClickListener {
            val name = binding.edtName.text.toString()
            val pass = binding.edtPass.text.toString()
            val confirm = binding.edtConfirmPass.text.toString()

            val user = userDao.checkExist(name)

            if (user != null) {
                if (pass == confirm) {
                    user.password = pass
                    userDao.changePass(user)
                    val intent = Intent(this, Login_Activity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this, "رمز ها مطابقت ندارند", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "چنین کاربری وجود ندارد", Toast.LENGTH_SHORT).show()
        }
    }
}