package com.hubose.applauserepobrowser.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hubose.applauserepobrowser.R
import com.hubose.applauserepobrowser.common.onTextChanged
import kotlinx.android.synthetic.main.layout_fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment: Fragment(){

    private val listViewModel: ListViewModel by viewModel()
    private lateinit var adapter: ListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel.error.observe(this, Observer {
            context?.toast(it.localizedMessage)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListAdapter(listViewModel.getViewState(), this) {
            findNavController().navigate(ListFragmentDirections.actionListToDetail(it.id))
        }
        et_list_search.onTextChanged {
            listViewModel.filter(it)
        }
        rv_list_items.layoutManager = LinearLayoutManager(context)
        rv_list_items.adapter = adapter
        activity?.title = getString(R.string.app_name)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.layout_fragment_list, container, false)
    }
}