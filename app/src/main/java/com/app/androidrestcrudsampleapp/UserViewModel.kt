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
import java.util.*

class UserViewModel(private val repository: Repository): ViewModel() {

    var usersList: MutableLiveData<List<User>> = MutableLiveData()
    var singleUser: MutableLiveData<User> = MutableLiveData()
    var createResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    var editResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    var deleteResponse: MutableLiveData<Response<Unit>> = MutableLiveData()


    fun getAllUsers(){
        viewModelScope.launch{
            val response: List<User> = repository.getAllUsers()
            usersList.value = response
        }
    }

    fun createUser(user: User){
        viewModelScope.launch {
            Log.d("Debug","lanzando creacion de usuario")
            val response : Response<Unit> = repository.createUser(user)
            Log.d("Debug","terminando creacion de usuario")
            createResponse.value = response
        }
    }

    fun editUser(user: User){
        viewModelScope.launch {
            Log.d("EditUser","lanzando edicion de usuario")
            val response : Response<Unit> = repository.editUser(user)
            Log.d("EditUser","terminando edicion de usuario")
            editResponse.value = response
        }
    }


    fun getSingleUser(id: Int){
        viewModelScope.launch {
            val response : Response<User> = repository.getSingleUser(id)
            if(response.code() == 200)
                singleUser.value = response.body()
            else
                singleUser.value = User(0,"Error", Date())
        }
    }
    fun deleteUser(id: Int){
        viewModelScope.launch {
            val response : Response<Unit> = repository.deleteUser(id)
            deleteResponse.value = response
        }
    }

}