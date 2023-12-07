package krodrigodev.com.br.arvorebinaria.connection;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import krodrigodev.com.br.arvorebinaria.MainActivity;

public class Api extends AsyncTask<String, Void, List<String>> {

    // atributos

    private static final String API_URL = "https://love-calculator.p.rapidapi.com/getPercentage";
    private static final String API_KEY = "b28cdb04f2msh4fe677ce1a73419p13fe25jsn7b16aa31919b";
    private MainActivity mainActivityReference;

    // construtor

    public Api(MainActivity mainActivityReference) {
        this.mainActivityReference = mainActivityReference;
    }

    // métodos com reescrita

    @Override
    protected List<String> doInBackground(String... params) {

        if (params.length < 2) {
            // Verificar se foram fornecidos pelo menos dois nomes
            return null;
        }

        String seuNome = params[0];
        String nomeCompanheiro = params[1];

        try {

            String apiUrlWithParams = API_URL + "?sname=" + seuNome + "&fname=" + nomeCompanheiro;

            URL url = new URL(apiUrlWithParams);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Adicione os cabeçalhos necessários
            urlConnection.setRequestProperty("X-RapidAPI-Key", API_KEY);
            urlConnection.setRequestProperty("X-RapidAPI-Host", "love-calculator.p.rapidapi.com");

            // Faça a requisição GET
            urlConnection.setRequestMethod("GET");

            // Leia a resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            // Feche a conexão
            in.close();
            urlConnection.disconnect();

            // Analise o JSON e retorne uma lista de strings
            return parseJson(response.toString());

        } catch (IOException e) {
            Log.e("API Request", "Erro ao fazer a requisição à API", e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<String> result) {
        if (!result.isEmpty()) {
            mainActivityReference.atualizarResultados(result);
            mainActivityReference.ocultarExibir(1);
        } else {
            // Mensagem de erro se os resultados não foram obtidos
            Toast.makeText(mainActivityReference, "Não foi possível obter os resultados. Tente novamente.", Toast.LENGTH_SHORT).show();
            mainActivityReference.ocultarExibir(0);
        }
    }

    private List<String> parseJson(String result) {
        List<String> listResultado = new ArrayList<>();

        try {
            JSONObject jsonResult = new JSONObject(result);

            String percentage = jsonResult.getString("percentage");

            listResultado.add(percentage);

        } catch (JSONException e) {
            Log.e("JSON Parsing", "Erro ao analisar o JSON da API", e);
        }

        return listResultado;
    }

}
