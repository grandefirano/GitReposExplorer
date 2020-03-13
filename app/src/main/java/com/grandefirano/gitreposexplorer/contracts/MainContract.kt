package com.grandefirano.gitreposexplorer.contracts

import androidx.lifecycle.LiveData
import com.grandefirano.gitreposexplorer.api.Repo

interface MainContract {

    interface MainView {

        fun initView(repositories:LiveData<List<Repo>>)
        fun updateList(repositories:List<Repo>)

        fun goToDetailsView(id: Int)
    }

    interface MainViewModel{
        fun onQueryChange(searchText:String)
        fun onViewInit()
        fun onSortByClicked()
    }
}