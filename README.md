Реализованы три алгоритма QuickSort на Kotlin:

1) Обычный однопоточный
2) Параллельный через ForkJoinPool (FJ)
3) Параллельный через корутины

Оба параллельных алгоритма тестировались с тремя значениями batch (максимальный размер части массива, на котором вместо параллельного применялся последовательный алгоритм): 10000, 100000, 1000000
После каждого запуска каждого алгоритма на заданном batch, отдельный класс Checker проверяет, что массив действительно отсортированный

Для сборки и запуска проекта:

1) ./gradlew build
2) ./gradlew run

Убедитесь, что в переменных среды есть JAVA_HOME для запуска сборки

Вывод в консоль после запуска лежит в файле "console log.txt", отдельно выделил среднее время работы в файле "time results.txt"