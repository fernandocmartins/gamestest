package br.com.fernandocsrmartins.gamestest

import br.com.fernandocsrmartins.gamestest.helper.HelperGames
import org.junit.Assert.assertEquals
import org.junit.Test

class HelperGamesTest {
    @Test
    @Throws(Exception::class)
    fun testGetLinkBox() {
        assertEquals(HelperGames.getLinkBox("{width}", 2f), "304")
        assertEquals(HelperGames.getLinkBox("{height}", 2f), "436")
    }
    @Test
    @Throws(Exception::class)
    fun testGetLinkLogo() {
        assertEquals(HelperGames.getLinkLogo("{width}", 2f), "128")
        assertEquals(HelperGames.getLinkLogo("{height}", 2f), "76")
    }
}