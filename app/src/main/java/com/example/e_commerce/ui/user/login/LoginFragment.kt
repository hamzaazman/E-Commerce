package com.example.e_commerce.ui.user.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.e_commerce.MainActivity
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val  viewModel: LoginViewModel by activityViewModels()
    lateinit var binding : FragmentLoginBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginFragment = this
        observeLiveData()
    }
    fun login(){
        val email = binding.emailEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()
        viewModel.signIn(email, password)
    }
    fun observeLiveData(){
        viewModel.isSignIn.observe(viewLifecycleOwner){isSignIn ->
            if (isSignIn){
                startActivity(Intent(requireActivity().applicationContext,MainActivity::class.java))
                requireActivity().finish()
            }else{
                Toast.makeText(requireContext(),"Giriş Yapılmadı",Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.isEmpty.observe(viewLifecycleOwner){isEmpty ->
            if (isEmpty){
                Toast.makeText(requireContext(),R.string.blanks_error,Toast.LENGTH_SHORT).show()
            }
        }
    }
}