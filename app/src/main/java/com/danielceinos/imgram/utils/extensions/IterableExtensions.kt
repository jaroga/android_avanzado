package com.danielceinos.imgram.utils.extensions

fun <T> List<T>.addNotNull(element: T?): List<T> {
    return if (element != null) this + element else this
}