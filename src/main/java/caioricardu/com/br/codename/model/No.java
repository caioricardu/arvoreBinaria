package krodrigodev.com.br.arvorebinaria.model;

public class No {

    // atributos
    private String nome;
    private String nomeCompanheiro;
    private String subsequenciaComum;
    private double porcentagem;
    private No esquerda;
    private No direita;

    // construtor
    public No(String nome, String nomeCompanheiro, double porcentagem) {
        this.nome = nome;
        this.nomeCompanheiro = nomeCompanheiro;
        this.porcentagem = porcentagem;
        this.subsequenciaComum = "";
        this.esquerda = null;
        this.direita = null;
    }

    // gets e sets

    public String getSubsequenciaComum() {
        return subsequenciaComum;
    }

    public void setSubsequenciaComum(String subsequenciaComum) {
        this.subsequenciaComum = subsequenciaComum;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCompanheiro() {
        return nomeCompanheiro;
    }

    public void setNomeCompanheiro(String nomeCompanheiro) {
        this.nomeCompanheiro = nomeCompanheiro;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}
