package com.grandefirano.gitreposexplorer.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grandefirano.gitreposexplorer.ExplorerApplication
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
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
        val modelImpl=ExplorerApplication.model
        mainViewModel=MainViewModel(this,modelImpl)
        mainViewModel.onViewInit()

        println("owner mainActiv "+modelImpl.toString())

        val layoutManager=LinearLayoutManager(this)
        repoRecyclerView.layoutManager=layoutManager
        repoRecyclerView.setHasFixedSize(true)
        var totalItemCount:Int
        repoRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                 totalItemCount=layoutManager.itemCount
                var lastVisiblePos=
                    layoutManager.findLastCompletelyVisibleItemPosition()
                    mainViewModel.loadMoreItems(totalItemCount,lastVisiblePos)
                }

        })

        adapter=MainRecyclerViewAdapter(mainViewModel)
        repoRecyclerView.adapter=adapter


        val paths =
            arrayOf("Filter by Name", "Filter by ColorLike")

        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.item_spinner,
            paths
        )
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter2
//        spinner.setOnItemSelectedListener(this)

        mainViewModel.filteredRepositories.observe(this, Observer {

            println("ddd list ${it.isEmpty()}")
            adapter.submitList(it)


        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.menu_main,menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView=searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                println("onqerychangd $newText")
                mainViewModel.onQueryChange(newText)
                return false
            }
        })

        searchButton.setOnClickListener {
            searchItem.expandActionView()
            searchView.requestFocus()
        }
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

    override fun goToDetailsView() {
        intent=Intent(this,DetailsActivity::class.java)
        startActivity(intent)
    }

    override fun showNoResults(searchText: String) {
        noResultsLayout.visibility=View.VISIBLE
    }

    override fun hideNoResult() {
        noResultsLayout.visibility=View.GONE
    }



}
