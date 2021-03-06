package lesson3

import org.junit.jupiter.api.Tag
import kotlin.test.*

class TrieTest {

    @Test
    @Tag("Example")
    fun generalTest() {
        val trie = Trie()
        trie.add("abcdefg")
        assertTrue("abcdefg" in trie)
        assertFalse("abcdef" in trie)
        assertFalse("a" in trie)
        assertFalse("g" in trie)

        trie.add("zyx")
        trie.add("zwv")
        trie.add("zyt")
        trie.add("abcde")
        assertEquals(5, trie.size)
        assertTrue("abcdefg" in trie)
        assertFalse("abcdef" in trie)
        assertTrue("abcde" in trie)
        assertTrue("zyx" in trie)
        assertTrue("zyt" in trie)
        assertTrue("zwv" in trie)
        assertFalse("zy" in trie)
        assertFalse("zv" in trie)

        trie.remove("zwv")
        trie.remove("zy")
        assertEquals(4, trie.size)
        assertTrue("zyt" in trie)
        assertFalse("zwv" in trie)

        trie.clear()
        assertEquals(0, trie.size)
        assertFalse("zyx" in trie)
    }

    @Test
    @Tag("Hard")
    fun rudeIteratorTest() {
        val trie = Trie()
        trie.add("балда")
        trie.add("банка")
        trie.add("бар")
        trie.add("манка")
        trie.add("барсук")
        trie.add("свалка")
        trie.add("манта")
        trie.add("холодильник")
        assertEquals(setOf("балда", "банка", "бар", "манка", "барсук", "свалка", "манта", "холодильник"), trie)
        val iter = trie.iterator()
        while (iter.hasNext()) {
            val next = iter.next()
            println("next is: $next")
            if (next == "бар") iter.remove()
        }
        assertEquals(setOf("балда", "банка", "манка", "барсук", "свалка", "манта", "холодильник"), trie)
    }
}