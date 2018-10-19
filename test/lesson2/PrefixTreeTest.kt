package lesson2

import lesson2.common.PrefixTree
import org.junit.Test

import org.junit.Assert.*

class PrefixTreeTest {

    @Test
    fun isWordHaveEnding() {
        val tree = PrefixTree()
        setOf("ТРАВА", "КРАН", "АКВА", "НАРТЫ", "РАК").forEach { tree.insert(it) }
        assertEquals(true, tree.isWordHaveEnding("РА"))
        assertEquals(true, tree.isWordHaveEnding("К"))
        assertEquals(true, tree.isWordHaveEnding("АК"))
        assertEquals(false, tree.isWordHaveEnding("П"))
        assertEquals(false, tree.isWordHaveEnding("КТ"))
    }

    @Test
    fun insert() {
        val tree = PrefixTree()
        setOf("ТРАВА", "КРАН", "АКВА", "НАРТЫ", "РАК").forEach { tree.insert(it) }
        assertEquals(true, tree.contains("ТРАВА"))
        assertEquals(true, tree.contains("КРАН"))
        assertEquals(true, tree.contains("АКВА"))
        assertEquals(true, tree.contains("НАРТЫ"))
        assertEquals(true, tree.contains("РАК"))
        assertEquals(false, tree.contains("ЫВА"))
        assertEquals(false, tree.contains("ЫВАЫВА"))


    }
}