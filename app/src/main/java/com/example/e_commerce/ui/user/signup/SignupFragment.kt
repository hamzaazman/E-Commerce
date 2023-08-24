package com.example.e_commerce.ui.user.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.e_commerce.MainActivity
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    val viewModel : SignupViewModel by viewModels()
    lateinit var binding : FragmentSignupBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupFragment = this
        observeLiveData()
    }
    fun login(){
        val email = binding.emailEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()
        viewModel.signup(email,password)
    }
    fun observeLiveData(){
        viewModel._isSignUp.observe(viewLifecycleOwner){isSignUp ->
            if(isSignUp){
                Toast.makeText(requireContext(),"Giriş Başarılı",Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(requireContext(),"Bir Hata Oluştu",Toast.LENGTH_SHORT).show()
            }
        }
    }
}