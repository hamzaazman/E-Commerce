package com.example.e_commerce.data.retrofit



import com.example.e_commerce.data.model.retrofit.Products
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("/products")
    fun getProducts() : Call<Products>
}