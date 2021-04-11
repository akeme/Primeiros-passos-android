package com.cellep.appcursoeh

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.cellep.appcursoeh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recuperar o email passado por meio do Intent
        val email = intent.getStringExtra("INTENT_EMAIL")
        
        //acessar o aquivovo de preferências compartilhadas
        val sharedPrefs = getSharedPreferences(
                "cadastro_$email",           //nome do arquivo
                 Context.MODE_PRIVATE               //modo de acesso
        )
        //recuperar dados no arquivo de preferÊncia compartilhadas
        //as aspas vazias definem o valor padrão em caso de erro
        val nome = sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val continente = sharedPrefs.getString("CONTINENTE","")

        //exibir os dados
        binding.txvMainNome.text = "$nome $sobrenome"
        binding.txvMainEmail.text = email
        binding.txvMainContinente.text = continente

        //escutnado o clique do botão sair
        binding.btnMainSair.setOnClickListener{

            //criando uma caixa de diálogo
            val alert = AlertDialog.Builder(this)

            //definindo o titulo da caixa de diálogo
            alert.setTitle("Atenção!!!")

            //definindo o corpo da mensaggem
            alert.setMessage("Você deseja mesmo sair? ")

            // definido o rótulo do botão sair e escutando o seu clique
            alert.setPositiveButton("Sair"){dialog, which ->
                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)

                //eliminar as telas da pilha
                finishAffinity()
            }

            //definindo o rótulo do botão não e escutando o seu clique
            alert.setNeutralButton("Não"){dialog, which -> }
            /**desabilita a possibilidade do usuário de cancelar a caixa de
             * diálogo ao clicar fora da mesma, dessa forma o usuário é obrigado
             * a interagir com o botão
             **/
            alert.setCancelable(false)

            //exibindo a caixa de dialogo
            alert.show()

            //escutando o clique do botão site cellep
            binding.btnMainSite.setOnClickListener{
                val mIntent = Intent(this,WebActivity::class.java )
                startActivity(mIntent)
            }

        }





    }
}