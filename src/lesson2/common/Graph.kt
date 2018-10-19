package lesson2.common


class Graph {
    data class Vertex(val name: String, val letter: String) {
        val neighbors = mutableSetOf<Vertex>()
    }

    private val vertices = mutableMapOf<String, Vertex>()

    operator fun get(name: String) = vertices[name] ?: throw IllegalArgumentException()

    fun addVertex(name: String, letter: String) {
        vertices[name] = Vertex(name, letter)
    }

    private fun connect(first: Vertex, second: Vertex) {
        first.neighbors.add(second)
        second.neighbors.add(first)
    }

    fun connect(first: String, second: String) = connect(this[first], this[second])

    fun neighbors(name: String) = vertices[name]?.neighbors?.map { it.name to it.letter } ?: listOf()

    fun wordsSearch(start: String, dictionary: PrefixTree): Set<String> = wordsSearch(this[start], dictionary, mutableSetOf(), StringBuilder(this[start].letter), mutableSetOf())

    private fun wordsSearch(start: Vertex, dictionary: PrefixTree, visited: MutableSet<Vertex>, word: StringBuilder, setOfWords: MutableSet<String>): Set<String> {
        val min = start.neighbors.filter { it !in visited }
        visited.add(start)
        min.forEach {
            word.append(it.letter)
            if (dictionary.contains(word.toString())) {
                setOfWords.add(word.toString())
            }
            if (!dictionary.isWordHaveEnding(word.toString())) {
                if (word.isNotEmpty()) word.setLength(word.length - 1)
                return@forEach
            }
            wordsSearch(it, dictionary, visited, word, setOfWords)
        }
        visited.remove(start)
        if (word.isNotEmpty()) word.setLength(word.length - 1)
        return setOfWords
    }

}
