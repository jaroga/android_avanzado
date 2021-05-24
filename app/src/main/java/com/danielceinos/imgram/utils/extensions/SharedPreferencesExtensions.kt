package com.danielceinos.imgram.utils.extensions

import android.content.SharedPreferences

fun SharedPreferences.getString(key: String) = getString(key, null)!!

fun SharedPreferences.getLong(key: String) = getLong(key, 0)