package com.ymnd.android.activity

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ymnd.android.databinding.ActivityMainBinding
import com.ymnd.android.db.AccountDataBase
import com.ymnd.android.db.entity.AccountEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AccountDataBase.getInstance(this)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) {
                db.accountDao().create(AccountEntity(status = "premium"))
                pref.edit().putBoolean("isShown", true).commit()
                pref.edit().putBoolean("hasDone", true).commit()
            }
        }

        binding.debugMenuButton.setOnClickListener {
            startActivity(DebugMenuActivity.createIntent(this))
        }
    }
}
