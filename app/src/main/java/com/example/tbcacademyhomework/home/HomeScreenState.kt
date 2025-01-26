package com.example.tbcacademyhomework.home

import com.example.tbcacademyhomework.network.model.UsersListDto
import com.example.tbcacademyhomework.util.Resource
import com.example.tbcacademyhomework.util.isLoading

data class HomeScreenState(
    val usersResource: Resource<UsersListDto>? = null,
    val usersData: List<User?>? = emptyList()
) {
    val canGetNextPage: Boolean
        get() {
            usersResource?.data?.let { data ->
                return (data.page < data.totalPages) && !usersResource.isLoading()
            }
            return false
        }

}
