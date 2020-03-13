package com.grandefirano.gitreposexplorer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.contracts.Model
import com.grandefirano.gitreposexplorer.model.ModelImpl
import com.grandefirano.gitreposexplorer.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    MainContract.MainView {

    private lateinit var adapter:MainRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //TODO: DO ZMIANY
        val modelImpl=ModelImpl()
        val mainViewModel=MainViewModel(this,modelImpl)
        mainViewModel.onViewInit()

        repoRecyclerView.layoutManager=LinearLayoutManager(this)
        repoRecyclerView.setHasFixedSize(true)

        adapter=MainRecyclerViewAdapter(this,mainViewModel)
        repoRecyclerView.adapter=adapter

        mainViewModel.filteredRepositories.observe(this, Observer {
            adapter.repos=it
            adapter.notifyDataSetChanged()
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun initView(repositories: LiveData<List<Repo>>) {

    }

    override fun updateList(repositories: List<Repo>) {
        adapter.notifyDataSetChanged()

    }


    override fun goToDetailsView(id: Int) {
        //TODO("Not yet implemented")
    }


}
