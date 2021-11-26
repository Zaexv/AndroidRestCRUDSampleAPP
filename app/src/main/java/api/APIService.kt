package api

import models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/api/User")
    suspend fun getAllUsers(): List<User>

    @GET("/api/User/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @POST("/api/User")
    suspend fun createUser(
        @Body user : User
    ): Response<Unit>

    @PUT("/api/User/{id}")
    suspend fun editUser(
        @Body user: User
    ): Response<Unit>

}