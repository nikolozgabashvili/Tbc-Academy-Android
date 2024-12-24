package com.example.tbcacademyhomework.product

import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.category.CategoryType

class ProductsDatabase {

    val products: List<Product> = listOf(
        Product(
            image = R.drawable.product1,
            title = "product 1",
            price = "$100",
            categoryType = CategoryType.CATEGORY1
        ),
        Product(
            image = R.drawable.product2,
            title = "product 2",
            price = "$100",
            categoryType = CategoryType.CATEGORY2
        ),
        Product(
            image = R.drawable.product3,
            title = "product 3",
            price = "$100",
            categoryType = CategoryType.CATEGORY3
        ),
        Product(
            image = R.drawable.product4,
            title = "product 4",
            price = "$100",
            categoryType = CategoryType.PARTY
        ),
        Product(
            image = R.drawable.product3,
            title = "other product",
            price = "$100",
            categoryType = CategoryType.CAMPING
        ),
        Product(
            image = R.drawable.product4,
            title = "produuuct 4",
            price = "$100",
            categoryType = CategoryType.CATEGORY1
        )
    )

    fun getProductsByCategory(category: CategoryType): List<Product> {
        return if (category == CategoryType.ALL) products
        else products.filter { it.categoryType == category }
    }
}