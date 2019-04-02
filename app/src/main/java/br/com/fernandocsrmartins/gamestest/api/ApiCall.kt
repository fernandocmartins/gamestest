package br.com.fernandocsrmartins.gamestest.api

import br.com.fernandocsrmartins.gamestest.model.TwitchRankingGames
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiCall{

    @Headers("Client-ID: wlnog3p05c2pkn6eb7mvq9twmo4dby")
    @GET("/kraken/games/top")
    fun getTopGames(@Query("limit") limit: Int?,
                    @Query("offset") offset: Int?): Call<TwitchRankingGames>
}