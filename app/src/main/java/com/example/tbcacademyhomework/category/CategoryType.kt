package com.example.tbcacademyhomework.category

import androidx.annotation.StringRes
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.product.Product

enum class CategoryType(@StringRes val displayName: Int){
    ALL(R.string.category_all),
    PARTY(R.string.category_party),
    CAMPING(R.string.category_camping),
    CATEGORY1(R.string.category_category1),
    CATEGORY2(R.string.category_category2),
    CATEGORY3(R.string.category_category3),

}
