package com.browngoo.nembers.global.validation


interface Password {
    fun fire(plainText: String): String
    fun compare(plainText: String, hashedText: String): Boolean
    fun isValid(plainText: String): Boolean
}