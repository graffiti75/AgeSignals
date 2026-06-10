package com.cericatto.agesignals

import com.cericatto.agesignals.data.repository.DebugAgeSignalsRepository
import com.cericatto.agesignals.domain.repository.AgeSignalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReleaseAgeSignalsModule {

    @Provides
    @Singleton
    fun provideAgeSignalsRepository(
        impl: AgeSignalsRepositoryImpl
    ): AgeSignalsRepository = impl
}