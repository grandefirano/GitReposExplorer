package com.grandefirano.gitreposexplorer.contracts

import com.grandefirano.gitreposexplorer.api.Repo

interface Model {

    fun getRepositories(searchText: String,sortBy:String,order:String,page:Int)
    fun getTrending()
    fun getContributors(repo:Repo)
    fun downloadPhoto(url:String)
    fun setActualRepository(repo:Repo)
    fun getColorOfLanguage(language:String)
}