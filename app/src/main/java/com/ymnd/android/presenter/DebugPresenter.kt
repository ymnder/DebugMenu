package com.ymnd.android.presenter

import android.content.Context
import android.preference.PreferenceManager
import com.ymnd.android.BuildConfig
import com.ymnd.android.db.AccountDataBase
import com.ymnd.android.debug.*
import java.lang.reflect.Modifier

class DebugPresenter(
    private val context: Context,
    private val remoteConfigManager: RemoteConfigManager
) : DebugContract.Presenter {

    private lateinit var category: DebugMenuCategory
    private lateinit var view: DebugContract.View

    private val db = AccountDataBase.getInstance(context)

    override fun setUp(view: DebugContract.View) {
        this.view = view
    }

    override fun setArguments(category: DebugMenuCategory) {
        this.category = category
    }

    override suspend fun onActivityCreated() {
        view.showLoading()
        updateDebugList()
    }

    override suspend fun onRefresh() {
        updateDebugList()
    }

    private suspend fun updateDebugList() {
        runCatching {
            getDebugListItem(category)
        }.onSuccess { listItem ->
            view.updateDebugList(listItem)
        }
        view.hideLoading()
    }

    private suspend fun getDebugListItem(category: DebugMenuCategory): List<DebugMenuListItem> {
        return when (category) {
            DebugMenuCategory.BUILD_CONFIG -> getBuildConfigItemList()
            DebugMenuCategory.FEATURE -> getFeatureItemList()
            DebugMenuCategory.ACCOUNT -> getAccountItemList()
            DebugMenuCategory.PREFERENCES -> getPreferencesItemList()
            DebugMenuCategory.REMOTE_CONFIG -> getRemoteConfigItemList()
        }
    }

    private fun getBuildConfigItemList(): List<DebugMenuListItem> {
        return BuildConfig::class.java.declaredFields
            .filter { Modifier.isStatic(it.modifiers) }
            .map {
                DebugMenuListItem.SimpleListItem(it.name, it.get(Any::class.java).toString())
            }
            .toList()
    }

    private fun getFeatureItemList(): List<DebugMenuListItem> {
        return listOf(
            DebugMenuListItem.ButtonListItem(
                title = "ダイアログを表示する",
                description = "",
                submit = "表示",
                debugItemId = DebugItemID.HOGE
            ),
            DebugMenuListItem.ButtonListItem(
                title = "キャッシュを削除する",
                description = "",
                submit = "削除",
                debugItemId = DebugItemID.FUGA
            )
        )
    }

    private suspend fun getAccountItemList(): List<DebugMenuListItem> {
        val entity = db.accountDao().getAccount()
        return mapOf(
            "status" to entity.status
        ).map {
            DebugMenuListItem.SimpleListItem(it.key, it.value)
        }.toList()
    }

    private fun getPreferencesItemList(): List<DebugMenuListItem> {
        return PreferenceManager.getDefaultSharedPreferences(context).all.map {
            DebugMenuListItem.SimpleListItem(it.key, it.value.toString())
        }.toList()
    }

    private fun getRemoteConfigItemList(): List<DebugMenuListItem> {
        return mapOf(
            RemoteConfigKey.AB_TEST.key to remoteConfigManager.getABTest().toString(),
            RemoteConfigKey.TITLE.key to remoteConfigManager.getTitle()
        ).map {
            DebugMenuListItem.SimpleListItem(it.key, it.value)
        }.toList()
    }
}