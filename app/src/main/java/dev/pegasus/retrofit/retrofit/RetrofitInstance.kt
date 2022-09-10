package dev.pegasus.retrofit.retrofit

import dev.pegasus.retrofit.interfaces.ClientApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: ClientApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClientApi::class.java)
    }

}