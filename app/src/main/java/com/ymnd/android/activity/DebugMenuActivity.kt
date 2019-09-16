package com.ymnd.android.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ymnd.android.BuildConfig
import com.ymnd.android.databinding.ActivityDebugBinding
import com.ymnd.android.fragment.DebugMenuViewPagerFragment

class DebugMenuActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DebugMenuActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!BuildConfig.DEBUG) {
            finish()
        }
        val binding = ActivityDebugBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, DebugMenuViewPagerFragment.newInstance())
                .commit()
        }
    }
}