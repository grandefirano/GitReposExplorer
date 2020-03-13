package com.grandefirano.gitreposexplorer.model

import androidx.lifecycle.MutableLiveData
import com.grandefirano.gitreposexplorer.ExplorerApplication
import com.grandefirano.gitreposexplorer.api.DetailedRepo
import com.grandefirano.gitreposexplorer.api.Owner
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.api.RepoSearchResult
import com.grandefirano.gitreposexplorer.contracts.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelImpl:Model {

    //TYMCZASOWE



     var repos= MutableLiveData<List<Repo>>()
    var actualRepo=MutableLiveData<DetailedRepo>()

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

                    this@ModelImpl.repos.value=response.body()!!.repositories

                }
            }
        })
    }

    override fun getTrending() {
        TODO("Not yet implemented")
    }

    override fun getDetails(ownerName: String, repoName: String) {
        //details
        ExplorerApplication.apiInterface.getDetailedRepo(ownerName,repoName).enqueue(object :Callback<DetailedRepo>{
            override fun onFailure(call: Call<DetailedRepo>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<DetailedRepo>, response: Response<DetailedRepo>) {
               if(response.isSuccessful){
                actualRepo.value=response.body()
               }
            }

        })
        //contributors
        ExplorerApplication.apiInterface.getContributors(ownerName,repoName).enqueue(object :Callback<List<Owner>>{
            override fun onFailure(call: Call<List<Owner>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<List<Owner>>, response: Response<List<Owner>>) {
                if(response.isSuccessful){
                    actualRepo.value?.contributors= response.body()!!

                }
            }

        })
    }


    override fun downloadPhoto(url: String) {
        TODO("Not yet implemented")
    }
}