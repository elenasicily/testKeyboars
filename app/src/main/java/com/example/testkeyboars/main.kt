package com.example.testkeyboars


import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    println("Введите имя игрока")
    val playerName = readln()

    println("Задайте количество попыток от 10 до 30")
    val attempts = readln().toInt()

    println("Задайте скорость игры от 1 до 9")
    val speed = readln().toInt().takeIf { it in 1..9 } ?: 6

    println("Стартуем? Д/Н")
    val start = readln()

    System.setProperty("org.jline.terminal", "debug")

    if (start.equals("Д", ignoreCase = true)) {
        var score = 0
        val alphabet = ('А'..'Я') + ('а'..'я')

        val terminal: Terminal = TerminalBuilder.terminal()

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

            val inputThread = thread {
                while (running) {
                    val inputChar = terminal.reader().read().toChar()
                    if (inputChar == letter) {
                        score++
                        running = false
                    }
                }
            }

            /*
                val input = System.`in`.read().toChar()
                if (input == letter) {
                    score++
                    running = false
                }
             */


            letterThread.join()
            inputThread.interrupt()

        }

        println("\nИгра окончена! Ваш счет: $score")
    } else {
        println("Пока!")
    }
}