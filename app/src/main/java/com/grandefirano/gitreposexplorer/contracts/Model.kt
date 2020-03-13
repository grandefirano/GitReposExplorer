package com.grandefirano.gitreposexplorer.contracts

interface Model {

    fun getRepositories(searchText:String)
    fun getTrending()
    fun getDetails(ownerName:String,repoName:String)
    fun downloadPhoto(url:String)
}