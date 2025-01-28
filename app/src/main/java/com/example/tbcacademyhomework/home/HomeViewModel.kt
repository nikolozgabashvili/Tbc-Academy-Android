package com.example.tbcacademyhomework.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.network.RetrofitImpl
import kotlinx.coroutines.flow.Flow

class HomeViewModel : BaseViewModel() {

    val userFlow: Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 6, prefetchDistance = 2),
        pagingSourceFactory = { HomePagingSource(RetrofitImpl.apiService) }
    ).flow.cachedIn(viewModelScope)


}