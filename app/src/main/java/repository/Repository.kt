package repository

import android.util.Log
import api.RetrofitInstance
import models.User
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getAllUsers(): List<User> {
        return RetrofitInstance.apiService.getAllUsers();
    }

    suspend fun createUser(user: User): Response<Unit> {
        Log.d("Repository-Debug","Lanzando create user en Repository")
        return RetrofitInstance.apiService.createUser(user)
    }

    suspend fun editUser(user: User): Response<Unit> {
        Log.d("Repository-Debug","Lanzando edit user en Repository")
        return RetrofitInstance.apiService.editUser(user)
    }

    suspend fun deleteUser(id:Int): Response<Unit>{
        Log.d("Repository-Debug", "Lanzando delete User en Repository")
        return RetrofitInstance.apiService.deleteUser(id)
    }

    suspend fun getSingleUser(id: Int): Response<User>{
        return RetrofitInstance.apiService.getUser(id)
    }
}