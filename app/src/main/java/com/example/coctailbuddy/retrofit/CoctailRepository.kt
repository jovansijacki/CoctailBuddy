package com.example.coctailbuddy.retrofit

import android.util.Log
import com.example.coctailbuddy.data.CoctailItemList
import com.example.coctailbuddy.data.SmallCoctailItemList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class CoctailRepository() {

    private var retrofitService: CoctailItemsRepo? = null

    init {
        if (retrofitService == null) {
            retrofitService = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/").build()
                .create(CoctailItemsRepo::class.java)
        }
    }

    private fun getCoctails() = retrofitService!!.getCoctailRepos()
    private fun getDetails(id: String) = retrofitService!!.getDetails(id)

    fun getAllCoctails(fetchCoctailsCallback: (SmallCoctailItemList) -> Unit) {
        val call = getCoctails()
        call.enqueue(object : Callback<SmallCoctailItemList> {
            override fun onResponse(
                call: Call<SmallCoctailItemList>,
                response: Response<SmallCoctailItemList>
            ) {
                fetchCoctailsCallback.invoke(response.body()!!)
            }

            override fun onFailure(call: Call<SmallCoctailItemList>, t: Throwable) {
                Log.d("CoctailRepository", "Failure" + t.message)
            }
        })
    }

    fun getCoctailDetails(id: String, fetchCoctailDetailsCallback: (CoctailItemList) -> Unit) {
        val call = getDetails(id)
        call.enqueue(object : Callback<CoctailItemList> {
            override fun onResponse(
                call: Call<CoctailItemList>,
                response: Response<CoctailItemList>
            ) {
                fetchCoctailDetailsCallback.invoke(response.body()!!)
            }

            override fun onFailure(call: Call<CoctailItemList>, t: Throwable) {
                Log.d("CoctailRepository", "Failure" + t.message)
            }
        })
    }

    interface CoctailItemsRepo {
        @GET("filter.php?i=Vodka")
        fun getCoctailRepos(): Call<SmallCoctailItemList>

        @GET("lookup.php")
        fun getDetails(@Query("i") id: String): Call<CoctailItemList>
    }
}