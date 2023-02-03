package com.example.rview.retrofityrecycl2

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("Json.php")
    fun getPosts(): Call <MutableList<PastModel>>
}