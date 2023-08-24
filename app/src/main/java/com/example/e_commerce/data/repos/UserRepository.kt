package com.example.e_commerce.data.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class UserRepository {
    val firebaseAuth = FirebaseAuth.getInstance()
    val isSignUp = MutableLiveData<Boolean>()
    fun signUp(email : String,password : String){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            isSignUp.value = true
        }.addOnFailureListener{
            isSignUp.value = false
            Log.e("onFailure",it.localizedMessage)
        }
    }

}