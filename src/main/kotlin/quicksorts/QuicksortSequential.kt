package quicksorts

class QuicksortSequential(
    private var array: Array<Int>
) {
    fun sort() {
        quicksort(0, array.size - 1)
    }

    fun getArray(): Array<Int> {
        return array
    }

    private fun quicksort(low: Int, high: Int) {
        if (high - low >= 1) {
            val partitionIndex = partition(low, high)

            quicksort(low, partitionIndex - 1)
            quicksort(partitionIndex + 1, high)
        }
    }

    private fun partition(low: Int, high: Int): Int {
        val pivot = array[high]
        var i = low - 1

        for (j in low until high) {
            if (array[j] <= pivot) {
                i++
                val temp = array[i]
                array[i] = array[j]
                array[j] = temp
            }
        }
        val temp = array[i + 1]
        array[i + 1] = array[high]
        array[high] = temp
        return i + 1
    }
}