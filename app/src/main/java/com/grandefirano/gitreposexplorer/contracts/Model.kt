package com.grandefirano.gitreposexplorer.contracts

import com.grandefirano.gitreposexplorer.api.Repo

interface Model {

    fun getRepositories(searchText:String)
    fun getTrending()
    fun getDetails(ownerName:String,repoName:String)
    fun downloadPhoto(url:String)
    fun setActualRepository(repo:Repo)
    fun getColorOfLanguage(language:String)
}