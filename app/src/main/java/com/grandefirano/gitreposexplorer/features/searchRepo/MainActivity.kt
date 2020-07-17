package com.grandefirano.gitreposexplorer.features.searchRepo

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
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.shared.model.Model
import com.grandefirano.gitreposexplorer.shared.model.ModelImpl
import com.grandefirano.gitreposexplorer.features.showDetails.DetailsActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_error.*
import kotlinx.android.synthetic.main.fragment_no_result.*


class MainActivity : AppCompatActivity(),
    MainContract.MainView {

    private val adapter: MainRecyclerViewAdapter by lazy{
        MainRecyclerViewAdapter(mainViewModel)
    }
    val mainViewModel: MainViewModel by lazy {
        MainViewModel(this, model)
    }
    private val model: Model by lazy {
        ModelImpl
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        println("Main Ativity Model Id= $model")


        mainViewModel.onViewInit()

        /**
         * INIT FUNCTIONS
         */
        initRecyclerView()
        initSpinner()
        initLiveData()

    }

    override fun onResume() {
        super.onResume()
        mainViewModel.onQueryChange()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                println("Main Activity search Text Change =$newText")
                mainViewModel.onQueryTextChanged(newText)
                return false
            }
        })
        searchButton.setOnClickListener {
            searchItem.expandActionView()
            searchView.requestFocus()
        }
        return true
    }

    /**
     * LIVE DATA OBSERVERS
     */
    private fun initLiveData() {
        mainViewModel.isServerLimitExceeded.observe(this, Observer {

            println("Main Activity observe ServerLimit= $it")

            it?.let {
                showServerError(it)
                showNoResults(false)
                showList(false)
            }
        })

        mainViewModel.filteredRepositories.observe(this, Observer {

            println("Main Activity observe list is empty?= ${it.isEmpty()}")
            adapter.submitList(it)
            showServerError(false)
            if (it.isEmpty()) {
                showNoResults(true)
                showList(false)
            } else {
                showList(true)
                showNoResults(false)
            }
        })
    }

    /**
     * SPINNER
     */

    private fun initSpinner() {
        val titlesOfSpinner = resources.getStringArray(R.array.sort_by_list_title)
        val valuesOfSpinner = resources.getStringArray(R.array.sort_by_list_value)

        val adapterSpinner: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.item_checked_spinner,
            titlesOfSpinner
        )
        adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapterSpinner
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Main Activity spinner nothing selected")
            }

            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val sortByState = valuesOfSpinner[position]
                mainViewModel.onSortByStateChanged(sortByState)
                println("Main Activity spinner $sortByState")
            }
        }
    }

    /**
     * REPO RECYCLERVIEW
     */
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        repoRecyclerView.layoutManager = layoutManager
        repoRecyclerView.setHasFixedSize(true)
        repoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastVisiblePos =
                    layoutManager.findLastCompletelyVisibleItemPosition()
                mainViewModel.loadMoreItems(totalItemCount, lastVisiblePos)
            }
        })

        repoRecyclerView.adapter = adapter

    }

    /**
     * NAVIGATION
     */
    override fun goToDetailsView() {
        println("View open Details Activity")
        intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }

    /**
     * SHOW LAYOUTS
     */
    override fun showList(ifShow: Boolean) {
        println("View show list: $ifShow")
        if (ifShow)
            repoRecyclerView.visibility = View.VISIBLE
        else
            repoRecyclerView.visibility = View.GONE
    }

    override fun showWelcomeScreen(ifShow: Boolean) {
        println("View show welcome: $ifShow")

        if (ifShow) {
            welcomeLayout.visibility = View.VISIBLE
            toolbarLayout.visibility = View.GONE
        } else {
            welcomeLayout.visibility = View.GONE
            toolbarLayout.visibility = View.VISIBLE
        }
    }

    override fun showNoResults(ifShow: Boolean) {
        println("View show noResult prompt: $ifShow")
        if (ifShow)
            noResultLayoutLayout.visibility = View.VISIBLE
        else
            noResultLayoutLayout.visibility = View.GONE

    }

    override fun showServerError(ifShow: Boolean) {
        println("View show error: $ifShow")
        if (ifShow)
            errorLayout.visibility = View.VISIBLE
        else
            errorLayout.visibility = View.GONE
    }

}
