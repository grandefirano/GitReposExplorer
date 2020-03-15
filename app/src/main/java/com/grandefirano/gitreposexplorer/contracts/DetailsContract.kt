package com.grandefirano.gitreposexplorer.contracts

import com.grandefirano.gitreposexplorer.api.Repo

interface DetailsContract {
    interface DetailsView{

        fun updateDetails()

        fun goToMain()
        fun goToWebsite(website: String)
    }
    interface DetailsViewModel{
        fun onWebsiteClick(website:String)
        fun onInitView(repo:Repo)
    }
}