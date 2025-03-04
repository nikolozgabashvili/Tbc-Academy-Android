package com.example.tbcacademyhomework.domain.meal.model

data class MealDetailListDomain(
    val meals: List<MealDetailDomain>
) {
    data class MealDetailDomain(
        val id: String,
        val name: String?,
        val category: String?,
        val area: String?,
        val instructions: String?,
        val image: String?,
        val youtubeVideo: String?,
        val ingredientMap: Map<String?, String?>
    )
}
