package com.andrew.starwars.di

import android.content.Context
import com.andrew.starwars.data.remote.SWApis
import com.andrew.starwars.utils.ConnectivityService
import com.andrew.starwars.utils.Constants.Companion.BASE_URL
import com.andrew.starwars.utils.DispatcherProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request: Request = chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Content-Type", "charset=utf-8")
                    .header("Accept", "application/json").build()
                val response = chain.proceed(request)

                response
            }).build()

        return client
    }

    @Provides
    @Singleton
    fun provideSWApis(client: OkHttpClient): SWApis {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .client(client)
            .build()
            .create()
    }


    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return object : DispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
        }
    }

    @Singleton
    @Provides
    fun provideConnectivityService(@ApplicationContext appContext: Context): ConnectivityService =
        ConnectivityService(context = appContext)


    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext appContext: Context): com.andrew.starwars.data.SharedPreferences {
        return com.andrew.starwars.data.SharedPreferences(context = appContext)
    }

}