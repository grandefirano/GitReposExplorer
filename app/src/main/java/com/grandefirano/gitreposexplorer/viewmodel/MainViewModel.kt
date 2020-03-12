package com.grandefirano.gitreposexplorer.viewmodel

import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.ModelImpl

class MainViewModel(val view:MainContract.MainView,val model: ModelImpl):MainContract.MainViewModel {



    override fun onTypeChange(searchText: String) {
        TODO("Not yet implemented")
    }

    override fun onViewInit() {



        model.getRepositories("android")

        view.initView(model.repos)


    }

    override fun onSortByClicked() {
        TODO("Not yet implemented")
    }

    //TODO do model?
    fun getFilteredList():List<Repo>{
        return model.repos
    }
}