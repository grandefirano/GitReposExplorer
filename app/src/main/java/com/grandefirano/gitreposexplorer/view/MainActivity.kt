package com.grandefirano.gitreposexplorer.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
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
import kotlinx.android.synthetic.main.fragment_error.*
import kotlinx.android.synthetic.main.fragment_no_result.*


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


        val titlesOfSpinner = resources.getStringArray(R.array.sort_by_list_title)
        val valuesOfSpinner = resources.getStringArray(R.array.sort_by_list_value)

        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.item_checked_spinner,
            titlesOfSpinner
        )
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter2
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {

                mainViewModel.sortListBy=valuesOfSpinner[position]
                mainViewModel.onQueryChange()
            }

        }

        mainViewModel.isServerLimitExceeded.value=false


        mainViewModel.isServerLimitExceeded.observe(this, Observer {

            println("dddd main acti ERRPR$it")

            it?.let {
                 showServerError(it)
                showNoResults(false)
                showList(false)
            }

        })

        mainViewModel.filteredRepositories.observe(this, Observer {

            println("ddd list ${it.isEmpty()}")
            adapter.submitList(it)
            showServerError(false)
            if(it.isEmpty()){
                showNoResults(true)
                showList(false)
            }else {
                showList(true)
               showNoResults(false)
            }


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
                mainViewModel.actualSearchText=newText
                mainViewModel.onQueryChange()
                return false
            }
        })

        searchButton.setOnClickListener {
            searchItem.expandActionView()
            searchView.requestFocus()
        }
        return true
    }

    //TODO
    override fun showList(ifShow: Boolean) {
        println("View show list: $ifShow")
        if(ifShow) {
            repoRecyclerView.visibility = View.VISIBLE

        }else{
            repoRecyclerView.visibility = View.GONE

        }
        //welcomeLayout.visibility=View.GONE
    }

    override fun showWelcomeScreen(ifShow: Boolean) {
        //toolbarLayout.visibility=View.GONE
        //repoRecyclerView.visibility=View.GONE
        println("View show welcome: $ifShow")
        if(ifShow) {
            welcomeLayout.visibility = View.VISIBLE
            toolbarLayout.visibility = View.GONE
        }else{
            welcomeLayout.visibility=View.GONE
            toolbarLayout.visibility = View.VISIBLE
        }
    }


    override fun updateList(repositories: List<Repo>) {
        //adapter.notifyDataSetChanged()

    }

    override fun goToDetailsView() {
        intent=Intent(this,DetailsActivity::class.java)
        startActivity(intent)
    }

    override fun showNoResults(ifShow: Boolean) {
        if(ifShow)
        noResultLayoutLayout.visibility=View.VISIBLE
        else
            noResultLayoutLayout.visibility=View.GONE

    }



    override fun showServerError(b:Boolean) {
        println("View show error: $b")
        if(b){
            errorLayout.visibility=View.VISIBLE
//            repoRecyclerView.visibility=View.GONE
        }
        else{
            errorLayout.visibility=View.GONE
//            repoRecyclerView.visibility=View.VISIBLE
        }

    }


}
