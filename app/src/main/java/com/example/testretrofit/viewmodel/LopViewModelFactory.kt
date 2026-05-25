package com.example.testretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testretrofit.repository.LopRepository

class LopViewModelFactory(private val repository: LopRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LopViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LopViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
