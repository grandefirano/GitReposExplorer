package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.LiveData
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.Repository

class MainViewModel(val view:MainContract.MainView,val model: Repository):MainContract.MainViewModel {
    override fun onTypeChange(searchText: String) {
        TODO("Not yet implemented")
    }

    override fun onViewInit() {



        model.getRepositories("android")

        view.updateList(getFilteredList())

    }

    override fun onSortByClicked() {
        TODO("Not yet implemented")
    }

    fun getFilteredList():List<Repo>{
        return model.repos
    }
}