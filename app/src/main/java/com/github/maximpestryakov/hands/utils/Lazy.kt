package com.github.maximpestryakov.hands.utils

fun <T> fastLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
