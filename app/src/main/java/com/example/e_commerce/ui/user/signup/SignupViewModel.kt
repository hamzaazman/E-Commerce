package com.example.e_commerce.ui.user.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.data.repos.UserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
class SignupViewModel  : ViewModel() {

    private val userRepository = UserRepository()
    var _isSignUp = MutableLiveData<Boolean>()
    //val isSignUp : LiveData<Boolean> = _isSignUp
    init {
        _isSignUp = userRepository.isSignUp
    }
    fun signup(email : String,password : String) = userRepository.signUp(email, password)
}