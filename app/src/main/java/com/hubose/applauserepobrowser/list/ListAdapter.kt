package com.hubose.applauserepobrowser.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.hubose.applauserepobrowser.R
import com.hubose.applauserepobrowser.common.setVisible
import com.hubose.applauserepobrowser.entity.RepoListItem
import kotlinx.android.synthetic.main.layout_item_rv.view.*

class ListAdapter(liveData: LiveData<ListViewState>,
                  owner: LifecycleOwner,
                  private val onItemClicked: (RepoListItem) -> Unit): RecyclerView.Adapter<ListAdapter.ItemHolder>() {

    private val repos = mutableListOf<RepoListItem>()

    init {
        liveData.observe(owner, Observer {
            repos.clear()
            repos.addAll(it.items)
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(repos[position], onItemClicked)
    }

    class ItemHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(item: RepoListItem, listener: (RepoListItem) -> Unit) = with(view) {
            view.tv_item_title.text = item.name
            view.iv_item_private.setVisible(item.private)
            setOnClickListener { listener(item) }
        }
    }
}