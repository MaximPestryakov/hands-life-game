package com.github.maximpestryakov.hands.utils

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> Activity.viewBinding(inflate: LayoutInflater.() -> T) = fastLazy { layoutInflater.inflate() }
