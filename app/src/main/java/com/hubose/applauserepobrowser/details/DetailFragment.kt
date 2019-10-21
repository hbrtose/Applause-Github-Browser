package com.hubose.applauserepobrowser.details

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.hubose.applauserepobrowser.R
import com.hubose.applauserepobrowser.common.addTextStyled
import com.hubose.applauserepobrowser.common.toast
import com.hubose.applauserepobrowser.common.url
import com.hubose.domain.entity.RepoEntity
import kotlinx.android.synthetic.main.layout_fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment: Fragment(){

    private val detailViewModel: DetailViewModel by viewModel { parametersOf(args.repoId) }
    private val args: DetailFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailViewModel.getLiveData().observe(this, Observer { handleRepoData(it) })
        detailViewModel.error.observe(this, Observer { context?.toast(it.localizedMessage) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_detail, container, false)
    }

    private fun handleRepoData(repo: RepoEntity){
        activity?.title = repo.name
        tv_detail_fullname.url(repo.url ?: "", repo.fullName ?: "")
        tv_detail_language.addTextStyled(styleText(repo.language ?: ""))
        tv_detail_created.addTextStyled(styleText(repo.createdAt ?: ""))
        tv_detail_last_updated.addTextStyled(styleText(repo.lastUpdated ?: ""))
        switch_detail_private.isChecked = repo.isPrivate
    }

    private fun styleText(string: String): SpannableString {
        val spannableString = SpannableString(string)
        spannableString.setSpan(TextAppearanceSpan(context, android.R.style.TextAppearance_Holo_SearchResult_Subtitle), 0, string.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}