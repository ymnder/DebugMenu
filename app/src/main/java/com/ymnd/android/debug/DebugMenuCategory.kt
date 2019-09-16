package com.ymnd.android.debug

import androidx.fragment.app.Fragment
import com.ymnd.android.fragment.DebugMenuFragment

/**
 * 開発者モードの項目を定義する
 * 項目の定義順に画面が表示される
 */
enum class DebugMenuCategory(val title: String) {
    REMOTE_CONFIG("RemoteConfig"),
    PREFERENCES("Preferences"),
    ACCOUNT("Account"),
    BUILD_CONFIG("BuildConfig"),
    FEATURE("Feature"),
    ;

    val fragment: Fragment = DebugMenuFragment.newInstance(this)
}

