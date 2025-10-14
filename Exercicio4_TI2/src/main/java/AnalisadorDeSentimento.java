import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AzureSentimentAnalysis {
    public static void main(String[] args) throws IOException {
        // Substitua pelas suas informações
        String endpoint = "https://SEU_ENDPOINT.cognitiveservices.azure.com/";
        String apiKey = "SUA_CHAVE_API";

        // URL específica da API de Análise de Sentimentos
        String apiUrl = endpoint + "text/analytics/v3.1/sentiment";

        // Texto de exemplo para análise
        String inputJson = "{ \"documents\": [{ \"id\": \"1\", \"language\": \"pt\", \"text\": \"Estou muito feliz com o serviço!\" }] }";

        // Conexão HTTP
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", apiKey);
        connection.setDoOutput(true);

        // Envia o texto para a API
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = inputJson.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Lê a resposta da API
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Resposta da API:");
            System.out.println(response.toString());
        }
    }
}
