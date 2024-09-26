package FourthTask;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Задача 4
 * Вариант 4. Баранова Анастасия Андреевна
 * Вывести только допустимые типы ответа (поле "Ассерt") из
 * заголовков (запрос выполняется по адресу "https://httpbin.org/anything").
 *
 */

public class Task {
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/anything"))
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsNode = objectMapper.readTree(response.body().toString()).findValue("headers");
                //Map<String, Object> responseBody = objectMapper.readValue(response.body(), Map.class);
                var accept = jsNode.get("Accept");
                if (accept == null ) {
                    System.out.println("No accept header");
                }
                else {
                    System.out.println("Accept: " + accept.toString());
                }
            }
            else {
                System.out.println("Error: " + response.statusCode());
            }
        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();



        }
    }
}