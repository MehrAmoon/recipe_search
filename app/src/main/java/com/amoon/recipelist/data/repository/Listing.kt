package com.amoon.recipelist.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    val pages: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>
)