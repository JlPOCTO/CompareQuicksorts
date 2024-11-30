package quicksorts

import kotlinx.coroutines.*

class QuicksortParallelCoroutines (
    private var array: Array<Int>,
    private val maxThreads: Int,
    private val minimumBatchSize: Int,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun sort() {
        runBlocking(context = Dispatchers.Default.limitedParallelism(maxThreads)) {
            coroutineScope {
                quicksort(0, array.size - 1)
            }
        }
    }

    fun getArray(): Array<Int> {
        return array
    }

    private suspend fun quicksort(low: Int, high: Int) {
        if (high - low >= 1) {
            val partitionIndex = partition(low, high)

            if (high - low > minimumBatchSize) {
                coroutineScope {
                    launch {quicksort(low, partitionIndex - 1)}
                    launch {quicksort(partitionIndex + 1, high)}
                }
            } else {
                quicksort(low, partitionIndex - 1)
                quicksort(partitionIndex + 1, high)
            }
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