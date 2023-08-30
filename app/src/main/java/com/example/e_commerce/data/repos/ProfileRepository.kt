package com.example.e_commerce.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.common.Constants
import com.example.e_commerce.common.Constants.NAME
import com.example.e_commerce.common.Constants.PHONE_NUMBER
import com.example.e_commerce.common.Constants.USER
import com.example.e_commerce.common.Constants.USER_EMAIL
import com.example.e_commerce.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val firestore: FirebaseFirestore){
    val userLiveData = MutableLiveData<User>()
    var email = ""
    var name = ""
    var phoneNumber = ""
    fun getUser() : LiveData<User>{
        firestore.collection(USER).document("yusuforhan@gmail.com").addSnapshotListener { value, error ->
            if (error != null){
                println("Error : ${error.message}")
            }else{
                val userMap = value?.data
                email = userMap?.get(USER_EMAIL) as String
                name = userMap.get(NAME) as String
                phoneNumber = userMap.get(PHONE_NUMBER) as String
                val user = User(name,phoneNumber, email)
                println(user.name)
                userLiveData.postValue(user)

            }
        }
        return userLiveData

    }
}