package sortedArrayChecker

class Checker(
    private val sortedArray: Array<Int>,
) {
    fun checkSorted(): Boolean {
        for (i in 0..<(sortedArray.size - 1)) {
            if (sortedArray[i] > sortedArray[i + 1]) {
                return false
            }
        }
        return true
    }
}