package lesson2

import lesson2.common.Graph
import lesson2.common.PrefixTree
import org.junit.Test

class GraphTest {

    @Test
    fun wordsSearch() {
        val g = Graph()
        val tree = PrefixTree()
        g.addVertex("A", "a")
        g.addVertex("B", "b")
        g.addVertex("C", "c")
        g.addVertex("D", "d")
        g.addVertex("E", "e")
        g.addVertex("F", "f")
        g.addVertex("G", "g")
        g.connect("A", "B")
        g.connect("A", "C")
        g.connect("B", "D")
        g.connect("B", "E")
        g.connect("B", "C")
        g.connect("C", "F")
        g.connect("C", "G")
        //print(g.wordsSearch("A"))
    }
}