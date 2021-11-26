package com.app.androidrestcrudsampleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repository.Repository

class UserViewModelFactory(private val repository: Repository):  ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}