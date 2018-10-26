package lesson3

import java.lang.StringBuilder
import java.util.*

class Trie : AbstractMutableSet<String>(), MutableSet<String> {
    override var size: Int = 0
        private set

    private class Node {
        val children: MutableMap<Char, Node> = linkedMapOf()
    }

    private var root = Node()

    override fun clear() {
        root.children.clear()
        size = 0
    }

    private fun String.withZero() = this + 0.toChar()

    private fun findNode(element: String): Node? {
        var current = root
        for (char in element) {
            current = current.children[char] ?: return null
        }
        return current
    }

    override fun contains(element: String): Boolean =
            findNode(element.withZero()) != null

    override fun add(element: String): Boolean {
        var current = root
        var modified = false
        for (char in element.withZero()) {
            val child = current.children[char]
            if (child != null) {
                current = child
            } else {
                modified = true
                val newChild = Node()
                current.children[char] = newChild
                current = newChild
            }
        }
        if (modified) {
            size++
        }
        return modified
    }

    override fun remove(element: String): Boolean {
        val current = findNode(element) ?: return false
        if (current.children.remove(0.toChar()) != null) {
            size--
            return true
        }
        return false
    }

    inner class TrieIterator : MutableIterator<String> {
        private val visited = mutableSetOf(root)
        private var wordsFound = 0
        private var lastNode = root
        private val word = StringBuilder("")
        private var finalWord = ""


        private fun getNextWord(): String {
            val min = lastNode.children.filter {
                it.value !in visited
            }
            visited.add(lastNode)
            min.forEach {
                if (contains(finalWord)) {
                    val tmp = finalWord
                    finalWord = ""
                    return tmp
                }
                word.append(it.key)
                if (contains(word.toString())) {
                    wordsFound++
                    println(word)
                    lastNode = it.value
                    finalWord = word.toString()
                    return finalWord
                }
                lastNode = it.value
                getNextWord()
                if (word.isNotEmpty()) word.setLength(word.length - 1)
            }
            visited.remove(lastNode)
            if (word.isNotEmpty()) word.setLength(word.length - 1)
            return finalWord
        }



        override fun hasNext(): Boolean {
            return wordsFound != size
        }

        override fun next(): String {
            return getNextWord()
        }

        override fun remove() {

        }

    }

    /**
     * Итератор для префиксного дерева
     * Сложная
     */
    override fun iterator(): MutableIterator<String> = TrieIterator()
}