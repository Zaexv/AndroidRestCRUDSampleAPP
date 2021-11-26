package com.app.androidrestcrudsampleapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import models.User
import repository.Repository
import retrofit2.Call
import retrofit2.Response

class UserViewModel(private val repository: Repository): ViewModel() {

    var usersList: MutableLiveData<List<User>> = MutableLiveData()
    var createResponse: MutableLiveData<Call<Unit>> = MutableLiveData()

    fun getAllUsers(){
        viewModelScope.launch{
            val response: List<User> = repository.getAllUsers()
            usersList.value = response
        }
    }

    fun createUser(user: User){
        viewModelScope.launch {
            Log.d("Debug","lanzando creacion de usuario")
            val response : Call<Unit> = repository.createUser(user)
            Log.d("Debug","terminando creacion de usuario")
            createResponse.value = response
        }
    }

}