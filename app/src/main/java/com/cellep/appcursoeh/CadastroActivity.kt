package com.cellep.appcursoeh

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.cellep.appcursoeh.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Criando uma lista de opções para o spinner
        val listaContinentes = arrayListOf("Continente","África","Antártida","América","Europa","Oceania","Ásia")
        //Criando um adaptador para o Spinner
        val spinnerAdapter = ArrayAdapter(
                this,                                           //Contexto
                android.R.layout.simple_spinner_dropdown_item,  //Layout
                listaContinentes                                //dados
        )

        //Plugando o adaptador no Spinner
        binding.spnCadastroContinente.adapter = spinnerAdapter
        //Quando o botão CADASTRAR for clicado
        binding.btnCadastroCadastrar.setOnClickListener {
            //os dados digitados são capturados e salvos em variáveis
            val nome = binding.edtCadastroNome.text.toString().trim()
            val sobrenome = binding.edtCadastroSobrenome.text.toString().trim()
            val email = binding.edtCadastroEmail.text.toString().trim()
            val senha = binding.edtCadastroSenha.text.toString().trim()
            val continente = binding.spnCadastroContinente.selectedItem.toString()

            //Aqui os campos do formulário são validados
            if (nome.isEmpty()      ||
                sobrenome.isEmpty() ||
                email.isEmpty()     ||
                senha.isEmpty()     ||
                continente == listaContinentes[0]
            ){
                //se qualquer campo não estiver prenchido uma mensagem de erro será exibida
                Toast.makeText(
                        this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                //Se todos os campos froam preenchidos o escopo do ELSe vai ser executado
                //Aqui estamos uma referência a um arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences(
                        "cadastro_$email",
                        Context.MODE_PRIVATE
                )
                //aqui tornamos a referência editável
                val editPrefs = sharedPrefs.edit()
                //Aqui os dados são preparados para serem slavos no arquivo
                //Os dados são salvos no formato chave->valor
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("CONTINENTE", continente)

                //Aqui os dados são sslvos no arquivo
                //o método apply realiza o processo de salvar fora do thread principal para
                // que o app não fique travado caso o processo demore
                editPrefs.apply()

                val mIntent = Intent(this, MainActivity::class.java)

                // Aqui é mostrado como passar dados entre activities
                // o email sera utilizado pela MainActivity para acessar o arquivo
                // de prefêrencias e exibir os dados
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)
                // este método remove todas as actvities do empilhamento
                finishAffinity()
            }
        }


    }
}