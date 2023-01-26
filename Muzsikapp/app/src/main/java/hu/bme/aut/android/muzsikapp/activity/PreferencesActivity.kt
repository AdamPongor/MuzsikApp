package hu.bme.aut.android.muzsikapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import hu.bme.aut.android.muzsikapp.R
import hu.bme.aut.android.muzsikapp.fragments.SettingsFragment

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, SettingsFragment())
            .commit()
    }


}

