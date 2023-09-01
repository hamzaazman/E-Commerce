package com.example.e_commerce.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.common.loadImage
import com.example.e_commerce.data.model.retrofit.Products
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.databinding.ProductListItemBinding

class ProductAdapter(val list : ArrayList<ProductsItem>,val context: Context) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

     class ProductViewHolder(val binding : ProductListItemBinding) : RecyclerView.ViewHolder(binding.root){
         fun bind(position: Int,url : String,context : Context,price : Double,title : String){
             binding.productItemImage.loadImage(url = url, context = context)
             binding.priceText = "$price$"
             binding.titleText = title
             binding.oldPriceText = price + 3
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = DataBindingUtil.inflate<ProductListItemBinding>(LayoutInflater.from(parent.context),R.layout.product_list_item,parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(position = position,list.get(position).image, context = context,list.get(position).price,list.get(position).title)
    }
}