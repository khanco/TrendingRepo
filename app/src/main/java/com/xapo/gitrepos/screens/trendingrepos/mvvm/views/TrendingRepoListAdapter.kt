package com.xapo.gitrepos.screens.trendingrepos.mvvm.views

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xapo.gitrepos.R
import com.xapo.gitrepos.commonmodels.RepositoryDetailsModel
import com.xapo.gitrepos.databinding.ListItemRepoBinding

class TrendingRepoListAdapter(var data: List<RepositoryDetailsModel>): RecyclerView.Adapter<TrendingRepoListAdapter.Item>() {

  private var layoutInflater: LayoutInflater? = null
  private lateinit var iRepoSelection: IRepoSelection

  override fun getItemCount() = data.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.context)
    }
    val binding: ListItemRepoBinding = DataBindingUtil.inflate(this.layoutInflater!!, R.layout.list_item_repo, parent, false)
    return Item(binding)
  }

  override fun onBindViewHolder(holder: Item, position: Int) {
    holder.bindingItem.model = data[position]
    holder.bindingItem.itemContainer.setOnClickListener {
      iRepoSelection.onItemClick(position)
    }
  }

  inner class Item(binding: ListItemRepoBinding): RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding
  }

  fun initInterface(iRepoSelection: IRepoSelection) {
    this.iRepoSelection = iRepoSelection
  }

  interface IRepoSelection {
    fun onItemClick(position: Int)
  }
}