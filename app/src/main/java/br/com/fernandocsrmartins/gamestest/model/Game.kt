package br.com.fernandocsrmartins.gamestest.model

import com.google.gson.annotations.SerializedName

data class Game(
    val name: String? = null,
    val box: UrlImg? = null,
    val logo: UrlImg? = null,
    @SerializedName("_id")
        val id: String? = null,
    @SerializedName("giantbomb_id")
        val giantBombId: String? = null)