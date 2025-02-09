package com.example.tbcacademyhomework.presentation.screens.users.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tbcacademyhomework.data.mappers.toDomain
import com.example.tbcacademyhomework.data.network.service.UsersApiService
import com.example.tbcacademyhomework.data.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.users.models.User


class UsersPagingSource(
    private val usersApiService: UsersApiService
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val response = usersApiService.fetchUsers(page)

            if (response.isSuccessful) {
                val userdata = response.body()?.toDomain()
                val users = userdata?.data ?: emptyList()

                val nextKey =
                    if (userdata != null && userdata.page < userdata.totalPages) page + 1 else null
                val prevKey = if (page == 1) null else page - 1

                LoadResult.Page(
                    data = users,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                val error = HttpRequestHelper.parseError(response)
                LoadResult.Error(Exception(error))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}