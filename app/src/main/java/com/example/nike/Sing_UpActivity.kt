package com.example.nike

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nike.Data.MyDataBase
import com.example.nike.Data.User
import com.example.nike.Data.User_Dao
import com.example.nike.databinding.ActivitySingUpBinding

class Sing_UpActivity : AppCompatActivity() {
    lateinit var userDao: User_Dao

    lateinit var binding: ActivitySingUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
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

        binding.edtEmail.addTextChangedListener(object : TextWatcher {
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
                    binding.edtEmailLayout.setStartIconTintList(ColorStateList.valueOf(Color.GRAY))
                } else {

                    binding.edtEmailLayout.setStartIconTintList(ColorStateList.valueOf(Color.BLACK))
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
                if (edtLength > 8) {
                    binding.edtPassLayout.isCounterEnabled = false
                    binding.edtPassLayout.isHelperTextEnabled = false
                } else {

                }
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
                    binding.edtPassLayout.setHelperTextColor(
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                this@Sing_UpActivity,
                                R.color.gray
                            )
                        )
                    )

                } else if (edtLengthNow <= 8) {
                    binding.edtPassLayout.error = "رمز باید بیشتر از 8 کارکتر باشد"
                    binding.edtPassLayout.setEndIconTintList(ColorStateList.valueOf(Color.BLACK))
                    binding.edtPassLayout.setStartIconTintList(ColorStateList.valueOf(Color.BLACK))
                    binding.edtPassLayout.setHelperTextColor(
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                this@Sing_UpActivity,
                                R.color.red
                            )
                        )
                    )

                } else {
                    binding.edtPassLayout.error = ""

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
                    binding.edtPassLayout.error = "رمز باید بیشتر از 8 کارکتر باشد"
                    binding.edtConfirmPassLayout.setEndIconTintList(ColorStateList.valueOf(Color.GRAY))
                    binding.edtConfirmPassLayout.setStartIconTintList(ColorStateList.valueOf(Color.GRAY))
                } else {
                    binding.edtPassLayout.error = ""
                    binding.edtConfirmPassLayout.setEndIconTintList(ColorStateList.valueOf(Color.BLACK))
                    binding.edtConfirmPassLayout.setStartIconTintList(ColorStateList.valueOf(Color.BLACK))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })



        binding.btnSingIn.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPass.text.toString()
            val confirm_Pass = binding.edtConfirmPass.text.toString()
            if (name.length > 0) {

                if (userDao.exist(name)){
                    Toast.makeText(this, "چنین نام کاربری وجود دارد ", Toast.LENGTH_SHORT).show()
                }else if (userDao.exist(email)){
                    Toast.makeText(this, "چنین کاربری وجود دارد ", Toast.LENGTH_SHORT).show()

                }else
                if (pass.length > 8) {
                    if (name.length > 0 &&
                        email.length > 0 &&
                        pass.length > 0
                    ) {
                        val user_info = User(name = name, email = email, password = pass)
                        if (pass == confirm_Pass) {
                            userDao.insertUser(user_info)
                            val intent = Intent(this, Login_Activity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "دوتا رمز مثه هم نیستن", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "تمامی مقادیر را پرکنید ", Toast.LENGTH_SHORT).show()
                    }
                }else
                    Toast.makeText(this, "رمز باید بیش از 8 کارکتر باشد ", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "نام کاربری را وارد کنید", Toast.LENGTH_SHORT).show()
        }
    }

}