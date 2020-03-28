package com.grandefirano.gitreposexplorer.shared

import android.app.Application
import com.grandefirano.gitreposexplorer.api.ApiConstants
import com.grandefirano.gitreposexplorer.api.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExplorerApplication : Application() {

    companion object {

        /**
         * SINGLETON FOR APPLICATION CONTEXT
         */
        lateinit var apiInterface: ApiInterface

    }

    override fun onCreate() {
        super.onCreate()

        /**
         * RETROFIT INIT
         */
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)


    }
}