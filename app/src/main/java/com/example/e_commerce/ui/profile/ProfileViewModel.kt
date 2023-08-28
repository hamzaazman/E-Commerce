package com.example.e_commerce.ui.profile

import androidx.lifecycle.ViewModel
import com.example.e_commerce.data.repos.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository): ViewModel() {
    fun getUser() = repository.getUser()
}