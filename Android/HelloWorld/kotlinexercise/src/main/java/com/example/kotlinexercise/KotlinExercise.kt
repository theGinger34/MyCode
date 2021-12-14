package com.example.kotlinexercise

import java.util.*

//a
data class Person(val name: String, val age: Int? = null)
//e
class Rectangle(val width: Int, val height: Int = 15)
//f
enum class Color{RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET}
//b
fun main() {
    //c
    val persons = listOf(Person("Bob", 29), Person("Campbell"))
    val oldest = persons.maxByOrNull { it -> it.age?:0 }
    println("The oldest is: $oldest")
    //d
    fun max(one: Int, two: Int){
        if (one >= two) {
            println(one)
        }
        else {
            println(two)
        }
    }//max
    max(1,2)
    //e
    val rectangle = Rectangle(41,43)
    println(rectangle.width)
    println(rectangle.height)
    fun isSquare(rectangle: Rectangle){
        if (rectangle.width == rectangle.height){
            println(true)
        } else {
            println(false)
        }
    }//isSquare
    isSquare(rectangle)
    val rectangle2 = Rectangle(25)
    isSquare(rectangle2)
    //f
    fun getMnemonic(color: Color){
        if(color == Color.RED){
            println("Richard")
        }
        if(color == Color.ORANGE){
            println("Of")
        }
        if(color == Color.YELLOW){
            println("York")
        }
        if(color == Color.GREEN){
            println("Gave")
        }
        if(color == Color.BLUE){
            println("Battle")
        }
        if(color == Color.INDIGO){
            println("In")
        }
        if(color == Color.VIOLET){
            println("Vain")
        }
    }//getMnemonic
    val color = Color.BLUE
    getMnemonic(color)
    fun getWarmth(color: Color){
        when (color){
            Color.RED, Color.ORANGE, Color.YELLOW -> println("warm")
            Color.GREEN -> println("neutral")
            Color.BLUE, Color.INDIGO, Color.VIOLET -> println("cold")
        }//when
    }//getWarmth
    val color2 = Color.ORANGE
    getWarmth(color2)
    //g
    for (i in 100 downTo 1 step 2) print(i)
    println("\nDone")
    //h
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }
    for (i in binaryReps) println(i)
    //i
    fun String.lastChar(): String {
        return takeLast(1)
    }
    var string = "Mobile App Dev II"
    println(string.lastChar())
    //j
    fun printAllCaps(string : String? = null){
        if (string == null){
            println(null)
        } else{
            println(string.toString().uppercase())
        }
    }//printAllCaps
    printAllCaps("abc")
    printAllCaps()
    //k
    fun strLenSafe(string : String?){
        if (string == null){
            println(0)
        } else{
            println(string.length)
        }
    }//strLenSafe
    strLenSafe("abc")
    strLenSafe(null)
}//main