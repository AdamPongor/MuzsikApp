package hu.bme.aut.android.muzsikapp.fragments

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.BuildCompat
import androidx.preference.*
import hu.bme.aut.android.muzsikapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs, rootKey)
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when (key) {
            "themeSwitch" -> {
                val exampleSwitch = findPreference<SwitchPreferenceCompat>(key)
                when (this.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                    Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                }
            }
            "example_text" -> {
                val exampleText = findPreference<EditTextPreference>(key)
                // do something with the text
            }
        }
    }

}