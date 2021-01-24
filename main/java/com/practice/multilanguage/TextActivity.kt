package com.practice.multilanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChangeLanguage(this).setLanguage()
        setContentView(R.layout.activity_text)
    }
}