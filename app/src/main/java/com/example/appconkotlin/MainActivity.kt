package com.example.appconkotlin

import android.R.attr.tag
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
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

var editText = findViewById<EditText>(R.id.editText)
        /*Ejecutando el boton*/
        findViewById<Button>(R.id.savebutton).setOnClickListener {
            /*Guardamos el ingreso de texto*/
           // var text = findViewById<EditText>(R.id.editText).text.toString()
            var text = editText.text.toString()
            /*solo imprime cuando no esta vacio*/
            if(text.isNotEmpty()){
                Log.i("Aplicando lo aprendido", "$text")
            }else{
                text = "Es necesraio ingresar datos"
            }
//            val params = Bundle()
//            params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.5f)
            speaker?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            editText.setText("")
        }

        speaker = TextToSpeech(this, this)


    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            speaker?.setLanguage(Locale("en","eu"))
            findViewById<TextView>(R.id.speech).text = "TextToSpeech esta disponible"
        }else{
            findViewById<TextView>(R.id.speech).text = "TextToSpeech no esta disponible"
        }

        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
    }
}