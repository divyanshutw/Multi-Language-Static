package com.practice.multilanguage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    var preferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ChangeLanguage(this).setLanguage()

        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        editor= preferences!!.edit()

        val currentLanguage=preferences!!.getString("language", "English")
        if(currentLanguage=="English")
            findViewById<RadioButton>(R.id.radioButton_english).isChecked=true
        else
            findViewById<RadioButton>(R.id.radioButton_hindi).isChecked=true

        findViewById<Button>(R.id.button_ok).setOnClickListener {
            val selectedId=findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId
            when(selectedId){
                R.id.radioButton_english -> {
                    setLanguage("English", "en")
                }
                R.id.radioButton_hindi -> {
                    setLanguage("Hindi", "hi")
                }
            }
        }

        findViewById<Button>(R.id.button_test).setOnClickListener {
            startActivity(Intent(this,TextActivity::class.java))
        }
    }

    private fun setLanguage(language: String, languageCode: String) {
        editor?.putString("language", language)
        editor?.commit()
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        Toast.makeText(this, "Language changed", Toast.LENGTH_LONG).show()
        finish()
    }
}