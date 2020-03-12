package com.grandefirano.gitreposexplorer

interface MainContract {

    interface MainView {

        fun updateList()

        fun goToDetailsView(id: Int)
    }

    interface MainViewModel{
        fun onTypeChange(searchText:String)
        fun onViewInit()
        fun onSortByClicked()
    }
}