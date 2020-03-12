package com.grandefirano.gitreposexplorer.api

import com.grandefirano.gitreposexplorer.api.ApiConstants.GET_REPOS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(GET_REPOS)
    fun getRepositories(@Query("q")q:String): Call<RepoSearchResult>
}