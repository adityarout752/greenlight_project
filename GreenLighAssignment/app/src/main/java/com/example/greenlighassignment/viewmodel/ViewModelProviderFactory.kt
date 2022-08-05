package com.example.greenlighassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.greenlighassignment.repository.Repository

class ViewModelProviderFactory(private val repository: Repository) : ViewModelProvider.AndroidViewModelFactory() {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MainViewModel(repository) as T
//    }
}