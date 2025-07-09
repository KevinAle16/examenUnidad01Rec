package com.example.examenunidad01rec

class calculadora {

    var num1: Float = 0.0f
    var num2: Float = 0.0f

    fun suma(): Float {
        return num1 + num2
    }

    fun resta(): Float {
        return num1 - num2
    }

    fun multiplicacion(): Float {
        return num1 * num2
    }

    fun division(): Float {
        return if (num2 != 0f) num1 / num2 else Float.NaN
    }
}