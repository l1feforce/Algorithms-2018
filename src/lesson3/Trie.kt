package lesson3

import java.lang.StringBuilder
import java.util.*
import kotlin.NoSuchElementException

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

    private fun StringBuilder.removeLast() = if (this.isNotEmpty()) this.setLength(this.length - 1) else this.setLength(this.length)

    /**
     * Итератор для префиксного дерева
     * Сложная
     */
    override fun iterator(): MutableIterator<String> = object : MutableIterator<String> {
        var foundWordsAmount = 0
        val queue = ArrayDeque<Pair<Char?, Node>>()
        val visited = mutableSetOf<Node>()
        val depthQueue = ArrayDeque<Int>()
        val word = StringBuilder("")

        init {
            queue.add(null to root)
        }

        override fun hasNext(): Boolean {
            return size != foundWordsAmount
        }

        override fun next(): String {
            while (queue.isNotEmpty()) {
                val next = queue.poll()
                visited.add(next.second)
                val neighbors = next.second.children
                val isWord = neighbors.containsKey(0.toChar())
                val isFork = isWord && neighbors.size > 2 || !isWord && neighbors.size > 1
                println("tmp wrd: $word")
                if (next.first != null) word.append(next.first!!)

                neighbors.forEach { char, node ->
                    if (char != 0.toChar() && node !in visited) queue.addFirst(char to node)
                }

                if (isFork) {
                    depthQueue.add(1)
                } else {
                    val tmp = depthQueue.poll() + 1
                    depthQueue.add(tmp)
                }

                val tmp = word.toString()
                if (neighbors.containsKey(0.toChar()) && neighbors.size == 1) {
                    val depth = depthQueue.poll()
                    depthQueue.add(queue.size)
                    for (i in 0 until depth) {
                        word.removeLast()
                    }
                }
                if (isWord) {
                    if (contains(tmp)) {
                        foundWordsAmount++
                        return tmp
                    }
                }
            }
            throw NoSuchElementException()
        }

        override fun remove() {
            this@Trie.remove(word.toString())
        }

    }
}