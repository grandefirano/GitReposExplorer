package com.grandefirano.gitreposexplorer.model

import androidx.lifecycle.MutableLiveData
import com.grandefirano.gitreposexplorer.ExplorerApplication
import com.grandefirano.gitreposexplorer.api.ApiConstants.SIZE_OF_PAGE
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
    var isServerLimitExceeded=MutableLiveData<Boolean>()


    override fun setActualRepository(repo:Repo){

        println("owner ModelImpl"+repo.name)
        //Null?
        actualRepo.value= repo

    }



    override fun getRepositories(searchText: String,sortBy:String,order:String,page:Int) {
        println("ddddd Model getRepo $searchText $page")
        ExplorerApplication.apiInterface.getRepositories(searchText,sortBy,page,SIZE_OF_PAGE).enqueue(object:
            Callback<RepoSearchResult>{
            override fun onFailure(call: Call<RepoSearchResult>, t: Throwable) {

            }


            override fun onResponse(
                call: Call<RepoSearchResult>,
                response: Response<RepoSearchResult>
            ) {
                println("ddd model if succes: ${response.isSuccessful} ${response.errorBody()?.string()} GGG ${response.body()} on response")
                if( response.body()?.repositories!=null){
                    println("ddd model success on response")
                    var newArray= response.body()!!.repositories
                    if(page==1) {
                        this@ModelImpl.repos.value = newArray
                        println("ddd inside new")
                    }else{
                        repos.value = repos.value?.plus(newArray)

                    }

                }else{
                    this@ModelImpl.repos.value= listOf()
                    println("ddd model else on response")
                }
                isServerLimitExceeded.postValue(response.errorBody()?.string()?.startsWith("{\"message\":\"API rate limit exceeded for"))

            }
        })
    }

    override fun getTrending() {
        TODO("Not yet implemented")
    }

    override fun getContributors(repo:Repo) {

        var repoName=repo.name
        var ownerName=repo.owner.login
        //contributors
        ExplorerApplication.apiInterface.getContributors(ownerName,repoName).enqueue(object :Callback<List<Owner>>{
            override fun onFailure(call: Call<List<Owner>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Owner>>, response: Response<List<Owner>>) {
                println("ddd model if succes: ${response.isSuccessful} ${response.errorBody()?.string()} GGG ${response.body()} on response")
                println("ddd contrib}")
                    
                    //println("ddd contrib ${response.body()}")
                if(response.isSuccessful){

                    var listOfContributors=response.body()
                    if(listOfContributors!=null) {
                        repo.contributors=listOfContributors
                        repo.contributorsCount=listOfContributors.size
                        actualRepo.value=repo
                        println("dddd ${actualRepo.value?.contributors}")
                        println("dddd ${actualRepo.value?.contributorsCount}")
                    }
                }
                isServerLimitExceeded.postValue(response.errorBody()?.string()?.startsWith("{\"message\":\"API rate limit exceeded for"))

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