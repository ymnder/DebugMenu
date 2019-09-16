package com.ymnd.android.debug

class RemoteConfigManager{
    fun getABTest() = true
    fun getTitle() = "狭山茶"
}

enum class RemoteConfigKey(val key: String) {
    AB_TEST("ab_test"),
    TITLE("title"),
}