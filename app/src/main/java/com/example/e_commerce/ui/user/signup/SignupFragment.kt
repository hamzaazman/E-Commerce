package com.example.e_commerce.ui.user.signup

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerce.MainActivity
import com.example.e_commerce.R
import com.example.e_commerce.common.Singleton
import com.example.e_commerce.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private val viewModel : SignupViewModel by activityViewModels()
    lateinit var binding : FragmentSignupBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupFragment = this
        observeLiveData()
    }
    fun signup(){
        val email = binding.emailEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()
        val nameLastname = binding.nameEdittext.text.toString()
        val phoneNumber = binding.phoneNumberEdittext.text.toString()
        viewModel.signup(email,password,nameLastname, phoneNumber)
    }
    private fun observeLiveData(){
        viewModel._isSignUp.observe(viewLifecycleOwner){isSignUp ->
            if(isSignUp){
                Toast.makeText(requireContext(),R.string.success_message1,Toast.LENGTH_SHORT).show()
            }
            else{
               Toast.makeText(requireContext(),R.string.error_message1,Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.isSaved.observe(viewLifecycleOwner){isSaved ->
            if (isSaved){
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }
        viewModel.isEmpty.observe(viewLifecycleOwner){isEmpty ->
            if (isEmpty){
                Toast.makeText(requireContext(),R.string.blanks_error,Toast.LENGTH_SHORT).show()
            }
        }
    }
}