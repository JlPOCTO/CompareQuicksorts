package quicksorts

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask

class QuicksortParallelForkJoin(
    private var array: Array<Int>,
    private val maxThreads: Int,
    private val minimumBatchSize: Int,
) {
    fun sort() {
        val forkJoinPool = ForkJoinPool(maxThreads)
        forkJoinPool.invoke(object : RecursiveTask<Unit>() {
            override fun compute() {
                quicksort(0, array.size - 1)
            }
        })
        forkJoinPool.shutdown()
    }

    fun getArray(): Array<Int> {
        return array
    }

    private fun quicksort(low: Int, high: Int) {
        if (high - low >= 1) {
            val partitionIndex = partition(low, high)

            if (high - low > minimumBatchSize) {
                val tasks = buildList {
                    add(object : RecursiveTask<Unit>() {
                        override fun compute() {
                            quicksort(low, partitionIndex - 1)
                        }
                    })
                    add(object : RecursiveTask<Unit>() {
                        override fun compute() {
                            quicksort(partitionIndex + 1, high)
                        }
                    })
                }
                tasks.map { it.fork() }.map { it.join() }
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