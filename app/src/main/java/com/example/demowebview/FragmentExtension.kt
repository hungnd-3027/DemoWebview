package com.example.demowebview

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun addFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    fragmentManager: FragmentManager
) {
    fragmentManager.apply {
        beginTransaction().apply {
            if (addToBackStack) {
                addToBackStack(fragment::class.java.simpleName)
            }
            add(containerId, fragment, fragment::class.java.simpleName)
        }.commit()
    }
}

fun replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    fragmentManager: FragmentManager
) {
    fragmentManager.apply {
        beginTransaction().apply {
            if (addToBackStack) {
                addToBackStack(fragment::class.java.simpleName)
            }
            replace(containerId, fragment, fragment::class.java.simpleName)
        }.commit()
    }
}
