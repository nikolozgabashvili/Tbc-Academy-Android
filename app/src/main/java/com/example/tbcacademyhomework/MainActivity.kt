package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.category.Category
import com.example.tbcacademyhomework.category.CategoryAdapter
import com.example.tbcacademyhomework.category.CategoryType
import com.example.tbcacademyhomework.databinding.ActivityMainBinding
import com.example.tbcacademyhomework.product.ProductAdapter
import com.example.tbcacademyhomework.product.ProductsDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter
    private val productDatabase by lazy { ProductsDatabase() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()

        setUpCategoriesRecycler()
        setUpProductRecycler()


    }

    private fun setUpProductRecycler() {
        val products = productDatabase.products
        val gridLayoutManager = GridLayoutManager(this, 2)
        productAdapter = ProductAdapter(products)
        binding.rvProduct.layoutManager = gridLayoutManager
        binding.rvProduct.adapter = productAdapter
    }

    private fun setUpCategoriesRecycler() {
        val categoryList = CategoryType.entries.map {
            Category(
                type = it, displayName = getString(it.displayName)
            )
        }
        categoryAdapter = CategoryAdapter(categoryList)
        binding.rvCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.adapter = categoryAdapter

        categoryAdapter.onClickListener {
            val newList = productDatabase.getProductsByCategory(it)
            productAdapter.updateList(newList)
        }

    }

    private fun handleEdgeToEdge() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val bottomPadding = if (imeInsets.bottom == 0) systemBars.bottom else imeInsets.bottom
            view.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = bottomPadding
            )
            insets
        }
    }
}