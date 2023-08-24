package com.example.e_commerce.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_commerce.MainActivity
import com.example.e_commerce.R
import com.example.e_commerce.databinding.ActivityLoginBinding

import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        setContentView(binding.root)
        userControl()
        binding.loginActivity = this
        setupWithViewPager()
    }
    private fun setupWithViewPager(){
        val titleList : Array<String> =  resources.getStringArray(R.array.titleList)
        binding.viewPager2.adapter = adapter
        TabLayoutMediator(binding.tablayout,binding.viewPager2) { tab,position ->
            tab.text = titleList[position]
        }.attach()
    }
    private fun userControl(){
       Firebase.auth.currentUser?.let {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}