package com.xapo.gitrepos.screens.trendingrepos.mvp.views

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xapo.gitrepos.R
import com.xapo.gitrepos.screens.repodetails.RepoDetailsActivity
import com.xapo.gitrepos.screens.trendingrepos.mvp.model.RepositoryDetails
import kotlinx.android.synthetic.main.list_item_repo.view.tvDesc
import kotlinx.android.synthetic.main.list_item_repo.view.tvTitle

class TrendingRepoListAdapter(var data: List<RepositoryDetails>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private lateinit var iRepoSelection: IRepoSelection

  override fun getItemCount() = data.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return Item(LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    (holder as Item).bindDate(position)
  }

  inner class Item(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindDate(position: Int) {
      val repo = data[position]
      itemView.tvTitle.text = repo.name
      itemView.tvDesc.text = repo.description

      itemView.setOnClickListener {
        iRepoSelection.onRepoSelected(position)
      }
    }
  }

  fun initInterface(iRepoSelection: IRepoSelection) {
    this.iRepoSelection = iRepoSelection
  }

  interface IRepoSelection {
    fun onRepoSelected(position: Int)
  }
}