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
import javax.inject.Inject

class ModelImpl @Inject constructor() : Model {


    /**
     * SINGLETON
     */
    companion object {
        private var modelImpl: ModelImpl? = null
        fun getInstance(): ModelImpl {
            if (modelImpl == null) {
                modelImpl = ModelImpl()
            }
            return modelImpl as ModelImpl
        }
    }

    override var repos = MutableLiveData<List<Repo>>()
    var actualRepo = MutableLiveData<Repo>()
    override var isServerLimitExceeded = MutableLiveData(false)

    override fun setActualRepository(repo: Repo) {

        println("Model actual Repo=" + repo.name)
        actualRepo.value = repo
    }

    override fun getRepositories(searchText: String, sortBy: String, page: Int) {
        println("Model getRepo $searchText $page")
        ExplorerApplication.apiInterface.getRepositories(searchText, sortBy, page, SIZE_OF_PAGE)
            .enqueue(object :
                Callback<RepoSearchResult> {
                override fun onFailure(call: Call<RepoSearchResult>, t: Throwable) {
                    println("Model get repositories failed")
                }

                override fun onResponse(
                    call: Call<RepoSearchResult>,
                    response: Response<RepoSearchResult>
                ) {
                    println("Model get repositories success: ${response.isSuccessful}")
                    if (response.isSuccessful) {
                        isServerLimitExceeded.value = false
                        if (response.body()?.repositories != null) {

                            val newArray = response.body()!!.repositories
                            if (page == 1) {
                                this@ModelImpl.repos.value = newArray
                                println("Model get repositories new list")
                            } else {
                                repos.value = repos.value?.plus(newArray)
                                println("Model get repositories append list")
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

                    println("Model download Contributors is successful?=${response.isSuccessful}")

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