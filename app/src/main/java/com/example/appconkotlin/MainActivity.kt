package com.example.appconkotlin

import android.R.attr.tag
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
import kotlin.math.log

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var speaker: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /*Guardamos el ingreso de texto*/
        val text = findViewById<EditText>(R.id.editText).text

        /*Ejecutando el boton*/
        findViewById<Button>(R.id.savebutton).setOnClickListener {
            /*solo imprime cuando no esta vacio*/
            if(text.isNotEmpty()){
                Log.i("Aplicando lo aprendido", "$text")
                speaker?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
                findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
            }

        }

        speaker = TextToSpeech(this, this)


    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            speaker?.setLanguage(Locale("it","IT"))
        }
    }
}