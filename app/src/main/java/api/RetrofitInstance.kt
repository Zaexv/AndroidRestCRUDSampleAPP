package api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.Constants.Companion.BASE_URL


object RetrofitInstance {

    private val retrofit by lazy {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()


        Retrofit.Builder().
                baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }

}