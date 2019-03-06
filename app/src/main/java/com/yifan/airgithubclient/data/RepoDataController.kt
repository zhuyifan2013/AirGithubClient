package com.yifan.airgithubclient.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RepoDataController {

    private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()

    private val service: GithubService

    init {
        service = retrofit.create(GithubService::class.java)
    }



    fun getGithubService(): GithubService {
        return service
    }

}