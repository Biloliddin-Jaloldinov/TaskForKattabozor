package com.example.kattabozortask.presentation.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kattabozortask.databinding.ScreenProductsCategoryBinding
import com.example.kattabozortask.network.data.Product
import com.example.kattabozortask.presentation.ui.adapter.ProductAdapter
import com.example.kattabozortask.presentation.viewmodel.CategoryProductsViewModel

class CategoryProductsScreen : Fragment() {
    private lateinit var binding: ScreenProductsCategoryBinding
    private lateinit var adapter: ProductAdapter
    private val viewModel: CategoryProductsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCategoryProducts()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = ScreenProductsCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startShimmerAnimation()
        initButtons()
        setObservers()
    }

    private fun setObservers() {
        viewModel.categoryProductsLiveData.observe(this, productsObserver)
        viewModel.error.observe(this, errorObserver)

    }

    private fun initButtons() {
        adapter = ProductAdapter()
        binding.rv.adapter = adapter

        binding.btnRefresh.setOnClickListener {

            startShimmerAnimation()
            viewModel.getCategoryProducts()
        }
    }


    private val errorObserver = Observer<String> { errorMessage ->
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private val productsObserver = Observer<List<Product>> { products ->
        stopShimmerAnimation()
        updateUi(products)
    }


    private fun updateUi(products: List<Product>) {
        adapter.submitList(products)
    }

    private fun stopShimmerAnimation() {
        binding.shimmerProducts.stopShimmer()
        binding.shimmerProducts.visibility = View.GONE
        binding.rv.visibility = View.VISIBLE
    }

    private fun startShimmerAnimation() {
        binding.shimmerProducts.visibility = View.VISIBLE
        binding.shimmerProducts.startShimmer()
        binding.rv.visibility = View.GONE
    }


    companion object {
        @JvmStatic
        fun newInstance() = CategoryProductsScreen()
    }
}