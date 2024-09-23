package com.example.testkeyboars

import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    println("Введите имя игрока")
    val playerName = readln()

    println("Задайте количество попыток от 10 до 30")
    val attempts = readln().toInt()

    println("Задайте скорость игры от 1 до 9")
    val speed = readln().toInt()

    println("Стартуем? Д/Н")
    val start = readln()

    if (start.equals("Д", ignoreCase = true)) {
        var score = 0
        val alphabet = ('А'..'Я') + ('а'..'я')

        repeat(attempts) {
            val letter = alphabet.random()
            var letterPosition = 0
            var running = true

            val letterThread = thread {
                while (letterPosition < 50 && running) {
                    print("\r${" ".repeat(letterPosition)}$letter")
                    Thread.sleep((1000 / speed).toLong())
                    letterPosition++
                }
            }

                val input = System.`in`.read().toChar()
                if (input == letter) {
                    score++
                    running = false
                }


            letterThread.join()

        }

        println("\nИгра окончена! Ваш счет: $score")
    } else {
        println("Пока!")
    }
}