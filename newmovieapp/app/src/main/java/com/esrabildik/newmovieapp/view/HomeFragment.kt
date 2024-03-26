package com.esrabildik.newmovieapp.view

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.esrabildik.newmovieapp.adapter.BestMovieAdapter
import com.esrabildik.newmovieapp.adapter.UpCommingMovieAdapter
import com.esrabildik.newmovieapp.base.BaseFragment
import com.esrabildik.newmovieapp.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    lateinit var bestMovieAdapter: BestMovieAdapter
    lateinit var upCommingAdapter : UpCommingMovieAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bestMovieAdapter = BestMovieAdapter()
        upCommingAdapter = UpCommingMovieAdapter()
        bestMovieInit()
        upCommingMovieInit()


    }

    fun bestMovieInit() {
        binding.bestMovieRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.bestMovieRv.adapter = bestMovieAdapter
        loadData(2) {
            bestMovieAdapter.submitList(it.data)
        }
    }

    fun upCommingMovieInit() {
        binding.upCommingRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.upCommingRv.adapter = upCommingAdapter
        loadData(1) {
            upCommingAdapter.submitList(it.data)
        }

    }
}

