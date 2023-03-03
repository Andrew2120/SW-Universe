package com.andrew.starwars.di

import com.andrew.starwars.data.data_sources.*
import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.repository.RepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        dataSource: BaseDataSource,
        planetsPagingDataSource: PlanetsDataSourcePaging,
        peopleDataSourcePaging: PeopleDataSourcePaging,
        moviesSearchPaging: MoviesDataSourcePaging,
         speciesDataSourcePaging: SpeciesDataSourcePaging,
        firebaseAuth: FirebaseAuth,


        ): BaseRepository =
        RepositoryImpl(
            dataSource,
            planetsPagingDataSource,
            peopleDataSourcePaging,
            moviesSearchPaging,
            speciesDataSourcePaging,
            firebaseAuth,
        )
}