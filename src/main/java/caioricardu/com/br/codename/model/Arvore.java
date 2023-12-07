package krodrigodev.com.br.arvorebinaria.model;


import android.util.Log;

import krodrigodev.com.br.arvorebinaria.AlgoritmoLCS;

public class Arvore {

    private No raiz;
    private AlgoritmoLCS algoritmoLCS;

    public Arvore() {
        raiz = null;
    }

    public void addNo(String nome, String nomeCompanheiro, double porcentagem) {
        No novoNo = new No(nome, nomeCompanheiro, porcentagem);

        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            addNoRec(raiz, novoNo);
        }

        algoritmoLCS = new AlgoritmoLCS(raiz, novoNo);
        String LCS = algoritmoLCS.calcularLCS();

        Log.d("LCS", "LCS: " + LCS);

        // Defina a subsequência comum apenas se não estiver vazia
        if (!LCS.isEmpty()) {
            novoNo.setSubsequenciaComum(LCS);
        }
    }



    private void addNoRec(No noAtual, No novoNo) {
        int comparacao = Double.compare(noAtual.getPorcentagem(), novoNo.getPorcentagem());

        if (comparacao > 0) {
            if (noAtual.getEsquerda() == null) {
                noAtual.setEsquerda(novoNo);
            } else {
                addNoRec(noAtual.getEsquerda(), novoNo);
            }
        } else if (comparacao < 0) {
            if (noAtual.getDireita() == null) {
                noAtual.setDireita(novoNo);
            } else {
                addNoRec(noAtual.getDireita(), novoNo);
            }
        }
    }

    // getters e setters...

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }
}
