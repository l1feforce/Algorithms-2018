package lesson2

import org.junit.jupiter.api.Tag
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class AlgorithmsTestsKotlin : AbstractAlgorithmsTests() {
    @Test
    @Tag("Easy")
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring() {
        assertEquals("СЕРВАТОР", lesson2.longestCommonSubstring("ОБСЕРВАТОРИЯ", "КОНСЕРВАТОРЫ"))
        assertEquals("стр", lesson2.longestCommonSubstring("абстрыяока", "жзстрмпока"))
        assertEquals("ока", lesson2.longestCommonSubstring("абокаыястр", "жзстрмпока"))
    }

    @Test
    @Tag("Easy")
    fun testCalcPrimesNumber() {
        calcPrimesNumber { calcPrimesNumber(it) }
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher() {
        assertEquals(setOf("ТРАВА", "КРАН", "АКВА", "НАРТЫ"),
                lesson2.baldaSearcher("input/balda_in1.txt", setOf("ТРАВА", "КРАН", "АКВА", "НАРТЫ", "РАК")))

    }
}