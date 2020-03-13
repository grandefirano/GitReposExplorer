package com.grandefirano.gitreposexplorer.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.ModelImpl
import com.grandefirano.gitreposexplorer.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    MainContract.MainView {

    private lateinit var adapter:MainRecyclerViewAdapter

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //TODO: DO ZMIANY
        val modelImpl=ModelImpl()
        mainViewModel=MainViewModel(this,modelImpl)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.menu_main,menu)

        val searchItem = menu.findItem(R.id.app_bar_search)

        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mainViewModel.onQueryChange(newText)
                return false
            }
        })


        return true
    }

    override fun showList() {
        repoRecyclerView.visibility= View.VISIBLE
        welcomeLayout.visibility=View.GONE
    }

    override fun showWelcomeScreen() {
        repoRecyclerView.visibility=View.GONE
        welcomeLayout.visibility=View.VISIBLE
    }


    override fun updateList(repositories: List<Repo>) {
        adapter.notifyDataSetChanged()

    }


    override fun goToDetailsView(id: Int) {
        //TODO("Not yet implemented")
    }


}
