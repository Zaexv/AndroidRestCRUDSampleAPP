package repository

import android.util.Log
import api.RetrofitInstance
import models.User
import retrofit2.Call

class Repository {

    suspend fun getAllUsers(): List<User> {
        return RetrofitInstance.apiService.getAllUsers();
    }

    suspend fun createUser(user: User): Call<Unit> {
        Log.d("Repository-Debug","Lanzando create user en Repository")
        return RetrofitInstance.apiService.createUser(user)
    }
}