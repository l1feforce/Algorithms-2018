@file:Suppress("UNUSED_PARAMETER")

package lesson2

import lesson2.common.Graph
import lesson2.common.PrefixTree
import java.io.File
import java.io.IOException


/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    TODO()
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 *
 * time and space complexity: O(n*m)
 */
fun longestCommonSubstring(first: String, second: String): String {
    var substring = StringBuilder()
    if (first.isEmpty() || second.isEmpty())
        return ""

    val num = Array(first.length) { IntArray(second.length) }
    var maxlen = 0
    var lastSubsBegin = 0

    for (i in 0 until first.length) {
        for (j in 0 until second.length) {
            if (first[i] == second[j]) {
                if (i == 0 || j == 0)
                    num[i][j] = 1
                else
                    num[i][j] = 1 + num[i - 1][j - 1]

                if (num[i][j] > maxlen) {
                    maxlen = num[i][j]
                    val thisSubsBegin = i - num[i][j] + 1
                    if (lastSubsBegin == thisSubsBegin) {
                        substring.append(first[i])
                    } else {
                        lastSubsBegin = thisSubsBegin
                        substring = StringBuilder()
                        substring.append(first.substring(lastSubsBegin, i + 1))
                    }
                }
            }
        }
    }

    return substring.toString()
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
fun calcPrimesNumber(limit: Int): Int {
    TODO()
}

/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 *
 * complexity: O(?)
 */
fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    val listOfVertexNames = mutableListOf<String>()
    val setOfWords = mutableSetOf<String>()

    //creating graph from the field
    fun split(inputName: String): Graph {
        val graph = Graph()
        if (!File(inputName).exists()) throw IOException()
        val array = File(inputName).readLines().map {
            it.split(" ")
        }

        for (y in 0 until array.size) {
            for (x in 0 until array[y].size) {
                graph.addVertex("cell$y$x", array[y][x].toUpperCase())
                listOfVertexNames.add("cell$y$x")
            }
        }

        for (y in 0 until array.size) {
            for (x in 0 until array[y].size) {
                //upper cell
                if (y - 1 >= 0) graph.connect("cell$y$x", "cell${y - 1}$x")
                //right cell
                if (x + 1 <= array[y].size - 1) graph.connect("cell$y$x", "cell$y${x + 1}")
                //bottom cell
                if (y + 1 <= array.size - 1) graph.connect("cell$y$x", "cell${y + 1}$x")
                //left cell
                if (x - 1 >= 0) graph.connect("cell$y$x", "cell$y${x - 1}")
            }
        }
        return graph
    }

    //adding dictionary in trie
    val dictionary = PrefixTree()
    words.forEach { dictionary.insert(it.toUpperCase()) }

    val field = split(inputName)
    listOfVertexNames.forEach { vertex ->
        val foundSet = field.wordsSearch(vertex, dictionary)
        foundSet.forEach { setOfWords.add(it) }
    }
    return setOfWords
}
