package com.otp.demoprojecttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otp.demoprojecttest.databinding.ActivityMainBinding
import com.otp.demoprojecttest.model.ItemRepository
import com.otp.demoprojecttest.network.ApiService
import com.otp.demoprojecttest.ui.ItemAdapter
import com.otp.demoprojecttest.ui.ItemViewModel
import com.otp.demoprojecttest.ui.ItemViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var viewModel: ItemViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
        setUpRecyclerView()
        setUpViewModel()
        observeViewModel()
        fetchData()
    }

    private fun initializeViews() {
        recyclerView = binding.recyclerView
        progressBar = binding.progressBar
    }

    private fun setUpRecyclerView() {
        itemAdapter = ItemAdapter(mutableListOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = itemAdapter
    }

    private fun setUpViewModel() {
        val apiService = ApiService.create()
        val itemRepository = ItemRepository(apiService)
        viewModel =
            ViewModelProvider(
                this,
                ItemViewModelFactory(itemRepository)
            ).get(ItemViewModel::class.java)
    }

    private fun observeViewModel() {
        viewModel.items.observe(this, Observer { items ->
            items?.let {
                itemAdapter.updateItems(it)
                showRecyclerView()
            }
        })
        viewModel.error.observe(this, Observer { error ->
            error?.let {
                // Handle the error, for example, display a toast or log it
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                showRecyclerView() // Ensure RecyclerView is visible even if there's an error
            }
        })
    }

    private fun fetchData() {
        showProgressBar()
        viewModel.fetchData()
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun showRecyclerView() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}
