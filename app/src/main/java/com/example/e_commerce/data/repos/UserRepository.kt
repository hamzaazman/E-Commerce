package com.example.e_commerce.data.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.common.Constants
import com.example.e_commerce.common.Constants.NAME
import com.example.e_commerce.common.Constants.PHONE_NUMBER
import com.example.e_commerce.common.Constants.USER
import com.example.e_commerce.common.Constants.USER_EMAIL
import com.example.e_commerce.common.Singleton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class UserRepository @Inject constructor(private val firebaseAuth: FirebaseAuth,private val fireStore: FirebaseFirestore){
    val isSignUp = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()
    val isSaved = MutableLiveData<Boolean>()
    fun signUp(email : String,password : String,nameLastname : String,phoneNumber : String){
        if (email.isEmpty() || password.isEmpty() || nameLastname.isEmpty() || phoneNumber.isEmpty()){
            isEmpty.value = true
        }else{
            isEmpty.value = false
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                isSignUp.value = true
                val userMap = hashMapOf<String,String>(
                    NAME to nameLastname,
                    PHONE_NUMBER to phoneNumber,
                    USER_EMAIL to email
                )
                fireStore.collection(USER).add(userMap).addOnSuccessListener {
                    isSaved.value = true
                }.addOnFailureListener {
                    isSaved.value = false
                }

            }.addOnFailureListener{
                isSignUp.value = false
                Singleton.errorMessage = it.localizedMessage.toString()
            }
        }

    }

}