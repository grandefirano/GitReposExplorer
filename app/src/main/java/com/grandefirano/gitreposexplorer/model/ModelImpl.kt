package com.grandefirano.gitreposexplorer.model

import androidx.lifecycle.MutableLiveData
import com.grandefirano.gitreposexplorer.ExplorerApplication
import com.grandefirano.gitreposexplorer.api.ApiConstants.SIZE_OF_PAGE
import com.grandefirano.gitreposexplorer.api.Owner
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.api.RepoSearchResult
import com.grandefirano.gitreposexplorer.contracts.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelImpl : Model {

    //TYMCZASOWE

    companion object {
        var modelImpl: ModelImpl? = null
        fun getInstance(): ModelImpl {
            if (modelImpl == null) {
                modelImpl = ModelImpl()
            }
            return modelImpl as ModelImpl
        }
    }

    var repos = MutableLiveData<List<Repo>>()
    var actualRepo = MutableLiveData<Repo>()
    var isServerLimitExceeded = MutableLiveData(false)

    override fun setActualRepository(repo: Repo) {

        println("owner ModelImpl" + repo.name)
        //Null?
        actualRepo.value = repo

    }


    override fun getRepositories(searchText: String, sortBy: String, page: Int) {
        println("ddddd Model getRepo $searchText $page")
        ExplorerApplication.apiInterface.getRepositories(searchText, sortBy, page, SIZE_OF_PAGE)
            .enqueue(object :
                Callback<RepoSearchResult> {
                override fun onFailure(call: Call<RepoSearchResult>, t: Throwable) {

                }


                override fun onResponse(
                    call: Call<RepoSearchResult>,
                    response: Response<RepoSearchResult>
                ) {
                    //println("ddd model if succes: ${response.isSuccessful} ${response.errorBody()?.string()} GGG ${response.body()} on response")
                    //println("answeeer ${response.errorBody()?.string()}")

                    if (response.isSuccessful) {
                        isServerLimitExceeded.value = false
                        if (response.body()?.repositories != null) {

                            println("ddd model success on response")
                            val newArray = response.body()!!.repositories
                            if (page == 1) {
                                this@ModelImpl.repos.value = newArray
                                println("ddd inside new")
                            } else {
                                repos.value = repos.value?.plus(newArray)

                            }

                        } else {
                            this@ModelImpl.repos.value = listOf()
                            println("ddd model else on response")
                        }
                    } else {

                        isServerLimitExceeded.postValue(
                            response.errorBody()?.string()
                                ?.startsWith("{\"message\":\"API rate limit exceeded for")
                        )
                        println(
                            "Modeeel czy ta" + response.errorBody()?.string()
                                ?.startsWith("{\"message\":\"API rate limit exceeded for")
                        )
                    }
                }
            })
    }

    override fun getContributors(repo: Repo) {

        val repoName = repo.name
        val ownerName = repo.owner.login

        ExplorerApplication.apiInterface
            .getContributors(ownerName, repoName).enqueue(object : Callback<List<Owner>> {
                override fun onFailure(call: Call<List<Owner>>, t: Throwable) {
                    println("Model download Contributors failed")
                }

                override fun onResponse(call: Call<List<Owner>>, response: Response<List<Owner>>) {

                    println("Model download Contributors is succesful?=${response.isSuccessful}")

                    if (response.isSuccessful) {
                        isServerLimitExceeded.value = false
                        val listOfContributors = response.body()
                        if (listOfContributors != null) {
                            repo.contributors = listOfContributors
                            repo.contributorsCount = listOfContributors.size
                            actualRepo.value = repo
                            println("Model actual Repo Contributors ${actualRepo.value?.contributors}")
                            println("Model actual Repo Contributors count ${actualRepo.value?.contributorsCount}")
                        }
                    } else {

                        isServerLimitExceeded.postValue(
                            response.errorBody()?.string()
                                ?.startsWith("{\"message\":\"API rate limit exceeded for")
                        )
                    }
                }
            })
    }
}