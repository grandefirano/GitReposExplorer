package com.grandefirano.gitreposexplorer

interface Model {

    fun getRepositories(searchText:String)
    fun getTrending()
    fun getDetails(id:Int)
}