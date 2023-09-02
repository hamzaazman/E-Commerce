package com.example.e_commerce.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.R
import com.example.e_commerce.data.model.retrofit.ProductsItem
import com.example.e_commerce.databinding.FragmentMainBinding
import com.example.e_commerce.ui.detail.DetailActivity
import com.example.e_commerce.ui.user.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel : MainViewModel by viewModels()
    private val productAdapter by lazy { ProductAdapter() }
    lateinit var binding : FragmentMainBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        observerLiveData()
        with(binding){
            swipeRefreshLayout.setOnRefreshListener {
                productsProgress.visibility = View.VISIBLE
                productsRw.visibility = View.INVISIBLE
                viewModel.getData()
                swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    private fun observerLiveData(){
        with(viewModel){
            productsItemList.observe(viewLifecycleOwner){list ->
                with(binding){
                    productsProgress.visibility = View.INVISIBLE
                    productsRw.visibility = View.VISIBLE
                    productsRw.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(requireContext(),2)
                        adapter = productAdapter.also { it.loadData(list) }
                    }
                }
            }
            isLoading.observe(viewLifecycleOwner){
                if (it){
                    with(binding){
                        productsProgress.visibility = View.VISIBLE
                        productsRw.visibility = View.INVISIBLE
                    }
                }

            }
            error.observe(viewLifecycleOwner){
                if (it){
                    with(binding){
                        productsProgress.visibility = View.INVISIBLE
                        productsRw.visibility = View.INVISIBLE
                        Snackbar.make(requireView(),"Error!",Snackbar.LENGTH_SHORT).show()
                    }
                }

            }

        }

    }
}