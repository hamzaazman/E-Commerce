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
import com.example.e_commerce.databinding.FragmentMainBinding
import com.example.e_commerce.ui.user.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    val vM : MainViewModel by viewModels()
    lateinit var binding : FragmentMainBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vM.getData()
        observerLiveData()
        //string.take()
    }
    private fun observerLiveData(){
        vM._productsItemList.observe(viewLifecycleOwner){list ->
            Toast.makeText(requireContext(),"Liste Değişti",Toast.LENGTH_SHORT).show()
            val adapter = ProductAdapter(list,requireContext())
            binding.productsRw.adapter = adapter
            binding.productsRw.layoutManager = GridLayoutManager(requireContext(),2)

        }
    }
}