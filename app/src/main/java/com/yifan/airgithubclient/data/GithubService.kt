package com.yifan.airgithubclient.data

import com.yifan.airgithubclient.data.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{user}/repos")
    fun getRepoList(@Path("user") userName: String): Call<List<Repo>>
}
