package com.example.grid.utils

import android.content.Context

fun loadJSONFromAsset(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}