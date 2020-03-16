package com.grandefirano.gitreposexplorer.contracts

import com.grandefirano.gitreposexplorer.api.Repo

interface Model {

    fun getRepositories(searchText: String,sortBy:String,page:Int)
    fun getContributors(repo:Repo)
    fun setActualRepository(repo:Repo)
}