package com.grandefirano.gitreposexplorer.api

import com.grandefirano.gitreposexplorer.Repo
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET
    fun getRepositories(): Call<List<Repo>>
}