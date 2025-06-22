package com.example.haetalhae3.viewmodel.temple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haetalhae3.repository.TempleRepository

class TempleViewModelFactory(
    private val repository: TempleRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TempleViewModel::class.java)) {
            return TempleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
