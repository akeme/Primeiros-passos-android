package com.cellep.appcursoeh

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cellep.appcursoeh.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //Técnica utilizada para inicializar uma variável sem que ela seja nula
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Quando o botão ENTRAR for clicado, faça
        binding.btnLoginEntrar.setOnClickListener {
            // aqui os dados digitados são capturados e salvos em varáveis
            val email = binding.edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = binding.edtLoginSenha.text.toString().trim()

            //Aqui é feita a validação dos campos
            // Se ambos estiverem preenchidos o escopo do else é executado
            if (email.isEmpty()){
                binding.edtLoginEmail.error = "Campo obrigatório"
                binding.edtLoginEmail.requestFocus()
            }else if (senha.isEmpty()){
                binding.edtLoginSenha.error = "Campo obrigatório"
                binding.edtLoginSenha.requestFocus()
            }else{
                //acessando o arquivo de preferências compartilhado
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )

                //recuperando dados no arquivo de preferencia compartilhado
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                //Aqui será validado se o email e senha batem
                if (email == emailPrefs && senha == senhaPrefs) {

                    // Se tudo estiver certo uma mensagem de sucesso será exibida
                    Toast.makeText(this, "Usuário Logado", Toast.LENGTH_LONG).show()

                    //E em seguida , a MainActvity será aberta
                    val mIntent = Intent(this, MainActivity::class.java)
                    //passando o email via intent para a mainActivity
                    mIntent.putExtra("INTENT_EMAIL", email)


                    startActivity(mIntent)

                    //Então eliminanos esta activity da lista
                    finish()
                }else{
                    // Se o email e senha não baterem , então uma mensagem de erro será exibida
                    Toast.makeText(this,"Email ou senha inválidos ", Toast.LENGTH_LONG).show()
                }
            }
        }

        //Quando o botão CADASTRAR for clicado, faça
        binding.btnLoginCadastrar.setOnClickListener {
            //A CadastroActivity é aberta
            val mIntent = Intent(this,CadastroActivity::class.java )
            startActivity(mIntent)
        }
    }
}