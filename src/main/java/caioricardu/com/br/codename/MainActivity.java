package krodrigodev.com.br.arvorebinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


import java.util.List;

import krodrigodev.com.br.arvorebinaria.connection.Api;
import krodrigodev.com.br.arvorebinaria.model.Arvore;
import krodrigodev.com.br.arvorebinaria.model.No;

public class MainActivity extends AppCompatActivity {

    // atributos
    TextInputEditText seuNome, nomeCompanheiro;
    TextView porcentagem, mensagem;
    Api api;
    ImageView coracaoPorcentagem;
    Arvore arvore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();
    }

    public void inicializar() {
        seuNome = findViewById(R.id.campoPrimeiroNome);
        nomeCompanheiro = findViewById(R.id.campoSegundoNome);
        porcentagem = findViewById(R.id.textPorcentagem);
        coracaoPorcentagem = findViewById(R.id.imgPorcentagem);
        mensagem = findViewById(R.id.textComplemento);
        arvore = new Arvore();

        ocultarExibir(0);
    }

    public void calcularAmor(View view) {
        String nome = seuNome.getText().toString();
        String parceiro = nomeCompanheiro.getText().toString();

        if (!nome.isEmpty() && !parceiro.isEmpty()) {
            api = new Api(this);
            api.execute(nome, parceiro);
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            ocultarExibir(0);
        }
    }


    public void ocultarExibir(int alternativa) {
        if (alternativa == 0) {
            coracaoPorcentagem.setVisibility(View.INVISIBLE);
            porcentagem.setVisibility(View.INVISIBLE);
            mensagem.setVisibility(View.INVISIBLE);
        } else if (alternativa == 1) {
            coracaoPorcentagem.setVisibility(View.VISIBLE);
            mensagem.setVisibility(View.VISIBLE);
            porcentagem.setVisibility(View.VISIBLE);
        }
    }

    public void atualizarResultados(List<String> result) {
        if (result.size() == 1) {
            double valor = Double.parseDouble(result.get(0));
            porcentagem.setText(valor + "%");

            // Adiciona o resultado à árvore
            arvore.addNo(seuNome.getText().toString(), nomeCompanheiro.getText().toString(), valor);

            imprimirEmOdem(arvore.getRaiz());

        }
    }

    private void imprimirEmOdem(No noAtual) {
        if (noAtual != null) {
            imprimirEmOdem(noAtual.getEsquerda());
            Log.d("ArvoreBinaria", "Nome: " + noAtual.getNome() + ", Nome Companheiro: " + noAtual.getNomeCompanheiro() + ", Porcentagem: " + noAtual.getPorcentagem() + "%");
            imprimirEmOdem(noAtual.getDireita());
        }
    }


}
