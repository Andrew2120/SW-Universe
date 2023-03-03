package com.andrew.starwars.di

import com.andrew.starwars.data.data_sources.*
import com.andrew.starwars.data.remote.SWApis
import com.andrew.starwars.utils.ConnectivityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providingDataSource(
        api: SWApis,
        connectivityService: ConnectivityService

    ): BaseDataSource =
        DataSourceImpl(
            api,
            connectivityService
        )


    @Provides
    @Singleton
    fun providingPlanetsDataSourcePaging(
        baseDataSource: BaseDataSource


    ) = PlanetsDataSourcePaging(baseDataSource)

    @Provides
    @Singleton
    fun providePeopleDataSourcePaging(
        baseDataSource: BaseDataSource


    ) = PeopleDataSourcePaging(baseDataSource)

    @Provides
    @Singleton
    fun provideMoviesDataSourcePaging(
        baseDataSource: BaseDataSource


    ) = MoviesDataSourcePaging(baseDataSource)


    @Provides
    @Singleton
    fun provideSpeciesDataSourcePaging(
        baseDataSource: BaseDataSource


    ) = SpeciesDataSourcePaging(baseDataSource)

}