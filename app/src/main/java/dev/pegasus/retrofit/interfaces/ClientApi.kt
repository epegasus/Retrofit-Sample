package dev.pegasus.retrofit.interfaces

import dev.pegasus.retrofit.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface ClientApi {

    @GET("/posts")
    suspend fun getAllPosts(): Response<List<Post>>

}