package krodrigodev.com.br.arvorebinaria;


import krodrigodev.com.br.arvorebinaria.model.No;

public class AlgoritmoLCS {

    private int[][] c; // Matriz para armazenar os valores dos subproblemas
    private int[][] b; // Matriz para armazenar as direções durante a construção da LCS

    private String X; // Sequência de caracteres X
    private String Y; // Sequência de caracteres Y

    // Construtor que inicializa as sequências X e Y, e as matrizes c e b
    public AlgoritmoLCS(No X, No Y) {
        this.X = X.getNome();
        this.Y = Y.getNome();
        this.c = new int[this.X.length() + 1][this.Y.length() + 1];
        this.b = new int[this.X.length() + 1][this.Y.length() + 1];
    }

    // Método principal para calcular a LCS entre X e Y
    public String calcularLCS() {
        int m = X.length();
        int n = Y.length();

        for (int i = 1; i <= m; i++) {
            c[i][0] = 0;
        }

        for (int j = 0; j <= n; j++) {
            c[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;
                }

            }
        }

        return reconstructLCS(m, n);
    }

    // reconstruir a LCS a partir das matrizes b
    private String reconstructLCS(int i, int j) {
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (b[i][j] == 1) {
                lcs.insert(0, X.charAt(i - 1)); // Adiciona o caractere ao início da LCS
                i--;
                j--;
            } else if (b[i][j] == 2) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.toString();
    }

}
