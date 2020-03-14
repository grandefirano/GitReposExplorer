package com.grandefirano.gitreposexplorer.api

import com.grandefirano.gitreposexplorer.api.ApiConstants.GET_REPOS
import com.grandefirano.gitreposexplorer.api.ApiConstants.GET_REPO_CONTRIBUTORS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(GET_REPOS)
    fun getRepositories(@Query("q")q:String): Call<RepoSearchResult>

    @GET(GET_REPO_CONTRIBUTORS)
    fun getContributors(@Path("owner")ownerName: String,
                        @Path("repo")repoName: String):Call<List<Owner>>

}