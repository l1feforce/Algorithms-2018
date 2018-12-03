@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson5


/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 */
fun Graph.findEulerLoop(): List<Graph.Edge> {
    TODO()
}

/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */
fun Graph.minimumSpanningTree(): Graph {
    TODO()
}

//common part for the last two tasks
abstract class Result<T> {
    abstract fun remove(): T
    abstract fun doActions(vertex: Graph.Vertex): T
    abstract fun getResult(): T
}

fun <T> dfs(start: Graph.Vertex, graph: Graph, result: Result<T>): T {
    fun <T> dfs(start: Graph.Vertex, graph: Graph, visited: MutableSet<Graph.Vertex>, result: Result<T>)
            : T {
        val neighbors = graph.getNeighbors(start).filter { it !in visited }
        visited.add(start)
        neighbors.forEach {
            result.doActions(it)
            dfs(it, graph, visited, result)
        }
        result.remove()
        visited.remove(start)
        return result.getResult()
    }
    return dfs(start, graph, mutableSetOf(), result)
}


/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 *
 * Ресурсоемкость - O(n), n - кол-во вершин в графе
 * Трудоемкость - O(n+e), n - кол-во вершин в графе, e - кол-во ребер в графе
 */
fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {
    val first = vertices.first()

    class IndependentVertexCounter : Result<Set<Graph.Vertex>>() {
        var counter = 0
        val pair = MyPair(mutableSetOf(), mutableSetOf())
        val actionsResult = setOf<Graph.Vertex>()

        init {
            pair.second.add(first)
        }

        override fun remove(): Set<Graph.Vertex> {
            pair.reverse()
            return getResult()
        }

        override fun doActions(vertex: Graph.Vertex): Set<Graph.Vertex> {
            if (pair.isFirst) pair.first.add(vertex) else pair.second.add(vertex)
            pair.reverse()
            return getResult()
        }

        override fun getResult(): Set<Graph.Vertex> {
            return pair.toList().reversed().maxBy{ it.size }?.toSet() ?: setOf()
        }
    }

    val result = IndependentVertexCounter()
    return dfs(first, this, result)
}

class MyPair(val first: MutableSet<Graph.Vertex>, val second: MutableSet<Graph.Vertex>) {
    var isFirst = true

    fun reverse() {
        isFirst = !isFirst
    }

    fun toList(): List<MutableSet<Graph.Vertex>> {
        return listOf(first, second)
    }
}

/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 *
 * Complexity: both O(!n), n - edges in graph
 */
fun Graph.longestSimplePath(): Path {
    val first = vertices.first()

    class LongestSimplePathFinder : Result<Path>() {
        var path = Path(first)
        var maxPath = path

        override fun remove(): Path {
            path = path.removeLast()
            return getResult()
        }

        override fun doActions(vertex: Graph.Vertex): Path {
            path = Path(path, this@longestSimplePath, vertex)
            maxPath = maxOf(path, maxPath)
            return getResult()
        }

        override fun getResult(): Path {
            return maxPath
        }
    }

    val result = LongestSimplePathFinder()
    return dfs(first, this, result)
}