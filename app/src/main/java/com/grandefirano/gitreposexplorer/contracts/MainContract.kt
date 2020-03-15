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
        var sortListBy:String
        var actualSearchText:String

        fun onQueryChange()
        fun onViewInit()
        fun onSortByClicked()
        fun onRepoClicked(repo:Repo)
        fun loadMoreItems(totalItemCount:Int,lastVisiblePos:Int)
        fun onNoItemInList(b: Boolean)

    }
}