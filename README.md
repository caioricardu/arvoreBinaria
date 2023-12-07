# Arvore Binária

<h3>O código que você forneceu implementa o algoritmo de Programação Dinâmica para encontrar a Maior Subsequência Comum (LCS - Longest Common Subsequence) entre duas sequências de caracteres, representadas por `X` e `Y`. A LCS é a maior sequência de caracteres que é comum a ambas as sequências, mas não necessariamente de forma contígua.</h3>

Aqui está uma explicação passo a passo do código:

1. Declaração de Matrizes e Sequências:
   - `private int[][] c;`: Matriz para armazenar os valores dos subproblemas. É uma matriz bidimensional usada para armazenar as soluções parciais dos subproblemas. `c[i][j]` representará o comprimento da LCS entre os primeiros `i` caracteres de `X` e os primeiros `j` caracteres de `Y`.
   - `private int[][] b;`: Matriz para armazenar as direções durante a construção da LCS. As direções são usadas para reconstruir a LCS no final.
   - `private String X;`: Sequência de caracteres X.
   - `private String Y;`: Sequência de caracteres Y.

2. Construtor:
   - Inicializa as sequências `X` e `Y`.
   - Inicializa as matrizes `c` e `b` com base no comprimento das sequências.

3. Método `calcularLCS`:
   - Calcula a LCS entre as sequências `X` e `Y` usando programação dinâmica.
   - Preenche a matriz `c` com os comprimentos das LCS parciais.
   - Preenche a matriz `b` com informações sobre as direções para reconstruir a LCS.

4. Método `reconstructLCS`:
   - Reconstrói a LCS a partir das matrizes `b`.
   - Inicia a partir da última célula (`c[m][n]`) e segue as direções indicadas por `b`.
   - Adiciona os caracteres correspondentes ao StringBuilder `lcs` enquanto percorre as direções.

5. Retorno:
   - Retorna a LCS como uma String.

O algoritmo usa a abordagem de programação dinâmica para preencher as matrizes `c` e `b` e, em seguida, usa a matriz `b` para reconstruir a LCS. A eficiência desse algoritmo é garantida pela subestrutura ótima e pela sobreposição de subproblemas, características comuns em algoritmos de programação dinâmica.
