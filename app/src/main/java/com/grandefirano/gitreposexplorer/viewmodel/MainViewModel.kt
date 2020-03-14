package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.LiveData
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.ModelImpl

class MainViewModel(val view:MainContract.MainView,val model: ModelImpl):MainContract.MainViewModel {

    var filteredRepositories:LiveData<List<Repo>> = model.repos

    override fun onQueryChange(searchText: String) {
        if(searchText!=null&& searchText!="") {
            model.getRepositories(searchText)
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
        TODO("Not yet implemented")
    }


}