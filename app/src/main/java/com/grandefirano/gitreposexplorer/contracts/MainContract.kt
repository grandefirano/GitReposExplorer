package com.grandefirano.gitreposexplorer.contracts

import androidx.lifecycle.LiveData
import com.grandefirano.gitreposexplorer.api.Repo

interface MainContract {

    interface MainView {

        fun showList()
        fun showWelcomeScreen()
        fun updateList(repositories:List<Repo>)

        fun goToDetailsView()
        fun showNoResults(searchText: String)
        fun hideNoResult()
    }

    interface MainViewModel{
        fun onQueryChange(searchText:String)
        fun onViewInit()
        fun onSortByClicked()
        fun onRepoClicked(repo:Repo)
        fun loadMoreItems()
        fun onNoItemInList(b: Boolean)
    }
}