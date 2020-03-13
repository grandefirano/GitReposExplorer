package com.grandefirano.gitreposexplorer.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_main_list.view.*

class MainRecyclerViewAdapter(val context: Context): RecyclerView.Adapter<MainRecyclerViewAdapter.RepoHolder>() {

    var repos= listOf<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.item_main_list, parent, false)


        var itemView=LayoutInflater.from(context).inflate(R.layout.item_main_list,parent,false)
        return RepoHolder(binding)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {

        var repo=repos[position]
        holder.itemView.nameOfRepoTextView.text = repo.name
        holder.itemView.nameOfOwnerTextView.text=repo.owner.login
        holder.itemView.repoPhotoTextView
    }

    class RepoHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(viewModel:MainViewModel,position: Int){

        }


    }



}