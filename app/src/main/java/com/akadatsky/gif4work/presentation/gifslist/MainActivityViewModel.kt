package com.akadatsky.gif4work.presentation.gifslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akadatsky.gif4work.data.remote.Data
import com.akadatsky.gif4work.presentation.Repository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivityViewModel : ViewModel(), KoinComponent {

    private val repository: Repository by inject()

    val searchResult = MutableLiveData<List<Data>>()

    fun performSearch(query: String) {
        viewModelScope.launch {
            searchResult.value = repository.performSearch(query)
        }
    }

}