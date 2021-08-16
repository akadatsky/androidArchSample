package com.akadatsky.gif4work.presentation

import com.akadatsky.gif4work.data.remote.Data

interface Repository {

    suspend fun performSearch(query: String): List<Data>

}