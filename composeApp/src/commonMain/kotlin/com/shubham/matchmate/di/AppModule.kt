package com.shubham.matchmate.di

import com.shubham.matchmate.data.repository.*
import com.shubham.matchmate.viewmodel.*
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::MockMatchRepository).bind<MatchRepository>()
    singleOf(::MockChatRepository).bind<ChatRepository>()
    singleOf(::MockPollRepository).bind<PollRepository>()
    singleOf(::MockUserRepository).bind<UserRepository>()
    singleOf(::MockThreadRepository).bind<ThreadRepository>()

    viewModelOf(::HomeViewModel)
    viewModelOf(::MatchDetailViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ThreadsViewModel)
}
