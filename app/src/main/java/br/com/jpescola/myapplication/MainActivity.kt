package br.com.jpescola.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var primeiraImg: ImageView
    private lateinit var segundaImg: ImageView
    private var primeiraEscolha = -1
    private var segundaEscolha = -1
    private var cartas = IntArray(16)
    private var jogadas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // definindo as cartas
        cartas[0] = R.drawable.fig0
        cartas[1] = R.drawable.fig0
        cartas[2] = R.drawable.fig1
        cartas[3] = R.drawable.fig1
        cartas[4] = R.drawable.fig2
        cartas[5] = R.drawable.fig2
        cartas[6] = R.drawable.fig3
        cartas[7] = R.drawable.fig3
        cartas[8] = R.drawable.fig4
        cartas[9] = R.drawable.fig4
        cartas[10] = R.drawable.fig5
        cartas[11] = R.drawable.fig5
        cartas[12] = R.drawable.fig6
        cartas[13] = R.drawable.fig6
        cartas[14] = R.drawable.fig7
        cartas[15] = R.drawable.fig7

        // embaralha
        cartas.shuffle()
    }

    fun novo(){
        finish(); // fecha o app
        startActivity(intent); // inicia o app atual
    }

    fun play(view: View){
        // armazena a img escolhida
        val img = findViewById<ImageView>(view.id)
        if (!img.isEnabled) // ignora clicks em cartas viradas
            return

        // verifica a carta escolhida
        val escolha = view.tag.toString().toInt()
        // mostra a carta
        img.setImageResource(cartas[escolha])
        // atualiza o score
        title = "${getString(R.string.app_name)} - jogadas: ${++jogadas}"

        // na terceira escolha, verifica o resultado
        if (primeiraEscolha != -1 && segundaEscolha != -1){ // verifica se acertou
            if (cartas[primeiraEscolha] != cartas[segundaEscolha]){ // errou
                primeiraImg.setImageResource(R.drawable.help)
                segundaImg.setImageResource(R.drawable.help)
                primeiraImg.isEnabled = true
                segundaImg.isEnabled = true
            }
            primeiraEscolha = -1
            segundaEscolha = -1
        }

        // primeira e segunda escolhas
        if (primeiraEscolha == -1) {
            primeiraEscolha = escolha
            primeiraImg = img
            primeiraImg.isEnabled = false

        }
        else{
            segundaEscolha = escolha
            segundaImg = img
            segundaImg.isEnabled = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.game_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuNovo)
            novo()
        else if (item.itemId == R.id.mnuSair)
            finish()
        return super.onOptionsItemSelected(item)
    }
}