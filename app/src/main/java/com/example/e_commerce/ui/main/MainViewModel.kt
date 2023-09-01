package com.example.e_commerce.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.retrofit.Products
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.repos.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo : MainRepository): ViewModel() {
    var _productsItemList = MutableLiveData<ArrayList<ProductsItem>>()
    init {
        _productsItemList = repo.productsItemList
    }
    fun getData() {
        repo.getData()
    }
}