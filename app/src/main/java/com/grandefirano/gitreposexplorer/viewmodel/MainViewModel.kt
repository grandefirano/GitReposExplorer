package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.LiveData
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.ModelImpl

class MainViewModel(val view:MainContract.MainView,val model: ModelImpl):MainContract.MainViewModel {

    var filteredRepositories:LiveData<List<Repo>> = model.repos
    var actualSearchText:String=""
    var actualPage:Int=1

    override fun onQueryChange(searchText: String) {
        actualPage=1
        actualSearchText=searchText
        if(searchText!=null&& searchText!="") {
            //TODO ZMIENIC
            model.getRepositories(searchText,"s","s",1)
            view.showList()
        }
        else onViewInit()
    }

    override fun onViewInit() {
        view.showWelcomeScreen()
    }

    override fun onSortByClicked() {
        TODO("Not yet implemented")
    }

    override fun onRepoClicked(repo:Repo) {


       // val repo=model.repos.value!![position]
        model.setActualRepository(repo)
//        var owner= model.repos.value!![position].owner.login
//        var repoName= model.repos.value!![position].name
//        println("owner mainVM"+model.repos.value!![position].name)

        view.goToDetailsView()
    }

    override fun loadMoreItems() {
        println("dddd na dole")
        actualPage++
        println("dd $actualPage")
        model.getRepositories(actualSearchText,"s","s",actualPage)

    }


}