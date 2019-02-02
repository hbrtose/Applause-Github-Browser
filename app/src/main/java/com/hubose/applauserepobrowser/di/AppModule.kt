package com.hubose.applauserepobrowser.di

import com.hubose.applauserepobrowser.BuildConfig
import com.hubose.applauserepobrowser.common.AsyncTransformer
import com.hubose.applauserepobrowser.details.DetailViewModel
import com.hubose.applauserepobrowser.entity.RepoListItem
import com.hubose.applauserepobrowser.list.ListViewModel
import com.hubose.applauserepobrowser.mapper.RepoEntityToListItemMapper
import com.hubose.data.repository.GithubRepositoryImpl
import com.hubose.data.repository.LocalGithubCache
import com.hubose.data.repository.RemoteGithubDataStore
import com.hubose.domain.GithubCache
import com.hubose.domain.GithubDataStore
import com.hubose.domain.GithubRepository
import com.hubose.domain.common.Mapper
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetAllRepos
import com.hubose.domain.usecase.GetRepoById
import com.hubose.domain.usecase.SearchRepos
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModels = module {
    viewModel { ListViewModel(get(), get(), get(), BuildConfig.REPO_OWNER_NAME, BuildConfig.NUMBER_OF_REPOS) }
    viewModel { DetailViewModel(get()) }
}

val dataModule = module(createOnStart = true) {
    single<GithubRepository> { GithubRepositoryImpl(get(), get()) }
    single<GithubDataStore> { RemoteGithubDataStore(get()) }
    single<GithubCache> { LocalGithubCache() }
}

val useCases = module {
    factory { GetAllRepos(AsyncTransformer(), get()) }
    factory { GetRepoById(AsyncTransformer(), get()) }
    factory { SearchRepos(AsyncTransformer(), get()) }
}

val mappers = module {
    factory<Mapper<RepoEntity, RepoListItem>> { RepoEntityToListItemMapper() }
}