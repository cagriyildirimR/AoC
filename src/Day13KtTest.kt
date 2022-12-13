import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class Day13KtTest {

    @Test
    fun compareTest() {
        val tests = listOf(
            Pair(Pair("]", "]"), true),
            Pair(Pair("", "]"), true),
            Pair(Pair("]", ""), false),
            Pair(Pair("[1,1,3,1,1]", "[1,1,5,1,1]"), true),
            Pair(Pair("3", "1"), false),
            Pair(Pair("1,3", "1,1"), false),
            Pair(Pair("[9]", "[[8,7,6]]"), false),//[7,7,7,7] vs [7,7,7]
            Pair(Pair("[[4,4],4,4]", "[[4,4],4,4,4]"), true),
            Pair(Pair("[7,7,7,7]", "[7,7,7]"), false),
            Pair(Pair("[[1],[2,3,4]]", "[[1],4]"), true),
            Pair(Pair("[]", "[3]"), true),//[[[]]] vs [[]]
            Pair(Pair("[[[]]]", "[[]]"), false),
            Pair(Pair("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]"), false),
            Pair(Pair("[2]", "[[1]]"), false),
            Pair(Pair("[2,2,2]", "2"), false),
            Pair(Pair("2","[2,2,2]"), true),
            Pair(Pair("[[7,8,6,10],[8,[]]]","[[7,[8],10],[5,4,[]]]"), true),
        )
        for (test in tests) {
            assertEquals(test.second, compare2(test.first.first, test.first.second))
        }
    }

    @Test
    fun firstAndRestTest() {
        val tests = listOf(
            // input, want
            Pair("[1,1,3,1,1]", Pair("[", "1,1,3,1,1]")),
            Pair("11],1,3,1,1]", Pair("11", "],1,3,1,1]")),
            Pair("[]", Pair("[", "]")),
            Pair("]", Pair("]", "")),
            Pair("1]", Pair("1", "]")),
            )
        for (test in tests) {
            assertEquals(test.second, firstAndRest(test.first))
        }
    }
}