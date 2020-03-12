package com.grandefirano.gitreposexplorer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract

class MainActivity : AppCompatActivity(),
    MainContract.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun updateList(repositories: List<Repo>) {

    }


    override fun goToDetailsView(id: Int) {
        //TODO("Not yet implemented")
    }


    private fun initRecyclerView(){

    }
}
