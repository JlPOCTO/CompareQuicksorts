import quicksorts.QuicksortParallelCoroutines
import quicksorts.QuicksortParallelForkJoin
import quicksorts.QuicksortSequential
import sortedArrayChecker.Checker
import kotlin.system.measureTimeMillis

fun main(){
    var sumMsSeq = 0L
    var sumMsParFJ10000 = 0L
    var sumMsParFJ100000 = 0L
    var sumMsParFJ1000000 = 0L
    var sumMsParCor10000 = 0L
    var sumMsParCor100000 = 0L
    var sumMsParCor1000000 = 0L

    val iterationsCount = 10
    val maxThreads = 4
    for (q in 1..iterationsCount) {
        Thread.sleep(1000)
        println("Iteration $q")
        val array = Array(100000000) { (0..1000000000).random() }

        if (true) {
            var quicksortSequential: QuicksortSequential? = QuicksortSequential(array.clone())
            val timeSequential = measureTimeMillis { quicksortSequential!!.sort() }
            println("   Time used (in ms) for sequential: $timeSequential")
            println("   Is array sorted? Checker says: ${Checker(quicksortSequential!!.getArray()).checkSorted()}")
            sumMsSeq += timeSequential
            quicksortSequential = null
        }

        Thread.sleep(1000)

        if (true) {
            val batch = 10000
            var quicksortParallel: QuicksortParallelForkJoin? = QuicksortParallelForkJoin(array.clone(), maxThreads, batch)
            val timeParallel = measureTimeMillis { quicksortParallel!!.sort() }
            sumMsParFJ10000 += timeParallel
            println("   Time used (in ms) for parallel fork-join with batch $batch: $timeParallel")
            println("   Is array sorted? Checker says: ${Checker(quicksortParallel!!.getArray()).checkSorted()}")
            quicksortParallel = null
        }

        Thread.sleep(1000)

        if (true) {
            val batch = 10000
            var quicksortParallel: QuicksortParallelCoroutines? = QuicksortParallelCoroutines(array.clone(), maxThreads, batch)
            val timeParallel = measureTimeMillis { quicksortParallel!!.sort() }
            sumMsParCor10000 += timeParallel
            println("   Time used (in ms) for parallel coroutines with batch $batch: $timeParallel")
            println("   Is array sorted? Checker says: ${Checker(quicksortParallel!!.getArray()).checkSorted()}")
            quicksortParallel = null
        }

        Thread.sleep(1000)

        if (true) {
            val batch = 100000
            var quicksortParallel: QuicksortParallelForkJoin? = QuicksortParallelForkJoin(array.clone(), maxThreads, batch)
            val timeParallel = measureTimeMillis { quicksortParallel!!.sort() }
            sumMsParFJ100000 += timeParallel
            println("   Time used (in ms) for parallel fork-join with batch $batch: $timeParallel")
            println("   Is array sorted? Checker says: ${Checker(quicksortParallel!!.getArray()).checkSorted()}")
            quicksortParallel = null
        }

        Thread.sleep(1000)

        if (true) {
            val batch = 100000
            var quicksortParallel: QuicksortParallelCoroutines? = QuicksortParallelCoroutines(array.clone(), maxThreads, batch)
            val timeParallel = measureTimeMillis { quicksortParallel!!.sort() }
            sumMsParCor100000 += timeParallel
            println("   Time used (in ms) for parallel coroutines with batch $batch: $timeParallel")
            println("   Is array sorted? Checker says: ${Checker(quicksortParallel!!.getArray()).checkSorted()}")
            quicksortParallel = null
        }

        Thread.sleep(1000)

        if (true) {
            val batch = 1000000
            var quicksortParallel: QuicksortParallelForkJoin? = QuicksortParallelForkJoin(array.clone(), maxThreads, batch)
            val timeParallel = measureTimeMillis { quicksortParallel!!.sort() }
            sumMsParFJ1000000 += timeParallel
            println("   Time used (in ms) for parallel fork-join with batch $batch: $timeParallel")
            println("   Is array sorted? Checker says: ${Checker(quicksortParallel!!.getArray()).checkSorted()}")
            quicksortParallel = null
        }

        Thread.sleep(1000)

        if (true) {
            val batch = 1000000
            var quicksortParallel: QuicksortParallelCoroutines? = QuicksortParallelCoroutines(array.clone(), maxThreads, batch)
            val timeParallel = measureTimeMillis { quicksortParallel!!.sort() }
            sumMsParCor1000000 += timeParallel
            println("   Time used (in ms) for parallel coroutines with batch $batch: $timeParallel")
            println("   Is array sorted? Checker says: ${Checker(quicksortParallel!!.getArray()).checkSorted()}")
            quicksortParallel = null
        }

        Thread.sleep(1000)
    }
    println("Average time for quicksortSequential: ${sumMsSeq.toDouble() / iterationsCount}")
    println("Average time for quicksortParallelFJ with batch 1000: ${sumMsParFJ10000.toDouble() / iterationsCount}")
    println("Average time for quicksortParallelFJ with batch 10000: ${sumMsParFJ100000.toDouble() / iterationsCount}")
    println("Average time for quicksortParallelFJ with batch 100000: ${sumMsParFJ1000000.toDouble() / iterationsCount}")
    println("Average time for quicksortParallelCor with batch 1000: ${sumMsParCor10000.toDouble() / iterationsCount}")
    println("Average time for quicksortParallelCor with batch 10000: ${sumMsParCor100000.toDouble() / iterationsCount}")
    println("Average time for quicksortParallelCor with batch 100000: ${sumMsParCor1000000.toDouble() / iterationsCount}")
}