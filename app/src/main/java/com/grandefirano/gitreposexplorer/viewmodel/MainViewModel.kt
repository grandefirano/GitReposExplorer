package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.LiveData
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.ModelImpl

class MainViewModel(val view:MainContract.MainView,val model: ModelImpl):MainContract.MainViewModel {

    var filteredRepositories:LiveData<List<Repo>> = model.repos

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

}