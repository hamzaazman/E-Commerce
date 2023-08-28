package com.example.e_commerce.ui.user.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.data.repos.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    var isSignIn = MutableLiveData<Boolean>()
    var isEmpty = MutableLiveData<Boolean>()
    init {
        isSignIn = userRepository.isSignIn
        isEmpty = userRepository.isEmptySignIn
    }
    fun signIn(email : String,password : String) =  userRepository.signIn(email, password)
}