package com.grandefirano.gitreposexplorer.shared.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grandefirano.gitreposexplorer.api.Repo

interface Model {

    val repos: MutableLiveData<List<Repo>>
    val isServerLimitExceeded: MutableLiveData<Boolean>

    fun getRepositories(searchText: String, sortBy: String, page: Int)
    fun getContributors(repo: Repo)
    fun setActualRepository(repo: Repo)
}