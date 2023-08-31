package com.example.e_commerce.data.repos

import android.widget.Toast
import com.example.e_commerce.data.model.retrofit.Products
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.data.retrofit.RetrofitApi
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response


class MainRepository @Inject constructor(private val api : RetrofitApi){
    suspend fun getData(){
        api.getProducts().enqueue(object : Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                println("Data İs Successful")
            }
            override fun onFailure(call: Call<Products>, t: Throwable) {
                println("Error : ${t.message}")
            }
        })
    }
}