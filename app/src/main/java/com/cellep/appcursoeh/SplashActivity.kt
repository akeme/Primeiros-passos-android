package com.cellep.appcursoeh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Executando instruções após determinado tempo
        Handler(Looper.getMainLooper()).postDelayed({
            //Uma das utilidades da classe Intent é usá-la para abrir uma activity
            val mIntent = Intent(this,LoginActivity::class.java)

            //Método responsável por executar a Intent
            startActivity(mIntent)

            //Remove a telha da pilha
            finish()
        }, 5000) // tempo em milisegundos

    }
}