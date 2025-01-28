package com.example.tbcacademyhomework.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tbcacademyhomework.network.ApiService
import com.example.tbcacademyhomework.util.toUser

class HomePagingSource(private val apiService: ApiService) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.fetchUsers(currentPage).body()!!

            LoadResult.Page(
                data = response.data.mapNotNull { it.toUser() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage < response.totalPages) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}