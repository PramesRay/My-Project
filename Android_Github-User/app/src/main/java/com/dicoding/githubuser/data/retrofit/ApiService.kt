package com.dicoding.githubuser.data.retrofit

import com.dicoding.githubuser.data.response.DetailUserResponse
import com.dicoding.githubuser.data.response.ItemsItem
import com.dicoding.githubuser.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    companion object{
        private const val USERNAME = "prames"
    }

    @GET("search/users")
    fun getUser(
        @Query("q") q: String = USERNAME
    ): Call<UserResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String = USERNAME
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}