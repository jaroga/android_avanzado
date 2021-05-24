package com.danielceinos.imgram.utils.extensions

fun Boolean.alsoIfTrue(cb: () -> Unit): Boolean {
    if (this) cb()

    return this
}