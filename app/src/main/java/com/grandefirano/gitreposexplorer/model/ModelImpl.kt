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

    companion object{
        var modelImpl:ModelImpl?=null
        fun getInstance():ModelImpl{
            if(modelImpl==null){
                modelImpl=ModelImpl()
            }
            return modelImpl as ModelImpl
        }
    }

     var repos= MutableLiveData<List<Repo>>()
    var actualRepo=MutableLiveData<Repo>()


    override fun setActualRepository(repo:Repo){

        println("owner ModelImpl"+repo.name)
        //Null?
        actualRepo.value= repo

    }



    override fun getRepositories(searchText: String,sortBy:String,order:String,page:Int) {
        ExplorerApplication.apiInterface.getRepositories(searchText,page).enqueue(object:
            Callback<RepoSearchResult>{
            override fun onFailure(call: Call<RepoSearchResult>, t: Throwable) {
                //TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<RepoSearchResult>,
                response: Response<RepoSearchResult>
            ) {
                if(response.isSuccessful){
                    if(page==1) {
                        this@ModelImpl.repos.value = response.body()!!.repositories
                        println("ddd inside new")
                    }else{
                        var newArray= response.body()!!.repositories
                        repos.value = repos.value?.plus(newArray)

                    }

                }
            }
        })
    }

    override fun getTrending() {
        TODO("Not yet implemented")
    }

    override fun getDetails(ownerName: String, repoName: String) {
        //details
//        ExplorerApplication.apiInterface.getDetailedRepo(ownerName,repoName).enqueue(object :Callback<DetailedRepo>{
//            override fun onFailure(call: Call<DetailedRepo>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<DetailedRepo>, response: Response<DetailedRepo>) {
//               if(response.isSuccessful){
//                actualRepo.value=response.body()
//               }
//            }
//
//        })

        //contributors
        ExplorerApplication.apiInterface.getContributors(ownerName,repoName).enqueue(object :Callback<List<Owner>>{
            override fun onFailure(call: Call<List<Owner>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Owner>>, response: Response<List<Owner>>) {
                if(response.isSuccessful){
                    actualRepo.value?.contributors= response.body()!!

                }
            }

        })
    }

    override fun getColorOfLanguage(language: String) {
        TODO("Not yet implemented")
    }


    override fun downloadPhoto(url: String) {
        TODO("Not yet implemented")
    }
}