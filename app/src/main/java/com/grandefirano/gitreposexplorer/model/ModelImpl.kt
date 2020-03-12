package com.grandefirano.gitreposexplorer.model

import com.grandefirano.gitreposexplorer.ExplorerApplication
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.api.RepoSearchResult
import com.grandefirano.gitreposexplorer.contracts.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelImpl:Model {


     var repos= listOf<Repo>()

    override fun getRepositories(searchText: String) {
        ExplorerApplication.apiInterface.getRepositories(searchText).enqueue(object:
            Callback<RepoSearchResult>{
            override fun onFailure(call: Call<RepoSearchResult>, t: Throwable) {
                //TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<RepoSearchResult>,
                response: Response<RepoSearchResult>
            ) {
                if(response.isSuccessful){

                    this@ModelImpl.repos = response.body()!!.repositories

                    println("ADDD"+response.body()!!.repositories)

                }
            }
        })
    }

    override fun getTrending() {
        TODO("Not yet implemented")
    }

    override fun getDetails(id: Int) {
        TODO("Not yet implemented")
    }
}