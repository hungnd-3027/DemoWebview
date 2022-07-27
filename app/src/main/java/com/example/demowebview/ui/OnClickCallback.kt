package com.example.demowebview.ui

interface OnClickCallback {
    fun htmlCallback(urlRequest: String)= Unit

    fun tabsCallback(urlRequest: String) = Unit

    fun browserCallback(urlRequest: String) = Unit
}
