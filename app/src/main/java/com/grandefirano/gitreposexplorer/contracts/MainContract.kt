package com.grandefirano.gitreposexplorer.contracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grandefirano.gitreposexplorer.api.Repo

interface MainContract {

    interface MainView {

        fun showList(ifShow:Boolean)
        fun showWelcomeScreen(ifShow: Boolean)
        fun updateList(repositories:List<Repo>)

        fun goToDetailsView()
        fun showNoResults(ifShow: Boolean)
        fun showServerError(b:Boolean)
    }

    interface MainViewModel{
        var isServerLimitExceeded: MutableLiveData<Boolean>
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