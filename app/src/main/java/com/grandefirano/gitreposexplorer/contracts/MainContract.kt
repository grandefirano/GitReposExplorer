package com.grandefirano.gitreposexplorer.contracts

import com.grandefirano.gitreposexplorer.api.Repo

interface MainContract {

    interface MainView {

        fun initView(repositories:List<Repo>)
        fun updateList(repositories:List<Repo>)

        fun goToDetailsView(id: Int)
    }

    interface MainViewModel{
        fun onTypeChange(searchText:String)
        fun onViewInit()
        fun onSortByClicked()
    }
}