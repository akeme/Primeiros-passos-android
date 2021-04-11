package com.cellep.appcursoeh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.cellep.appcursoeh.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //habilitando a execução de códigos em JavaScript
        binding.wbvWeb.settings.javaScriptEnabled = true

        //carregando um endereço web
        binding.wbvWeb.loadUrl("https://br.cellep.com/estacaohack")

        //definindo o webwiew como cliente padrão
        binding.wbvWeb.webViewClient = WebViewClient()


    }
}