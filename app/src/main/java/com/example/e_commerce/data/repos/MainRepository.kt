package com.example.e_commerce.data.repos

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.data.model.retrofit.Products
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.retrofit.RetrofitApi
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response


class MainRepository @Inject constructor(private val api : RetrofitApi){
    val productsItemList = MutableLiveData<ArrayList<ProductsItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    fun getData(){
        isLoading.value = true
        api.getProducts().enqueue(object : Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful){
                    isLoading.value = false
                    error.value = false
                    productsItemList.value = response.body()
                }
            }
            override fun onFailure(call: Call<Products>, t: Throwable) {
                isLoading.value = false
                error.value = true
                Log.e("MAIN_ERROR",t.message.toString())
            }
        })
    }
}