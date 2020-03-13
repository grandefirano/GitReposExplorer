package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.LiveData
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.ModelImpl

class MainViewModel(val view:MainContract.MainView,val model: ModelImpl):MainContract.MainViewModel {

    var filteredRepositories:LiveData<List<Repo>> = model.repos

    override fun onQueryChange(searchText: String) {
        if(searchText!=null&& searchText!="")
        model.getRepositories(searchText)
        else onViewInit()
    }

    override fun onViewInit() {


        //TODO DO ZMIANY
        model.getRepositories("android")

        view.initView(model.repos)


    }

    override fun onSortByClicked() {
        TODO("Not yet implemented")
    }

    //TODO do model?

}