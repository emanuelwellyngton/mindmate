package xyz.codenestsolucoes.mindmate.assistent.service;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GroqService {
    public void getDocSummarize (List<String> docs) throws IOException, InterruptedException {
        Dotenv dotenv = Dotenv.load();

        HttpClient httpClient = HttpClient.newHttpClient();

        //TODO Implement a system message
        String requestBody = "{" +
                "  \"messages\": [" +
                "    {" +
                "      \"role\": \"system\"," +
                "      \"content\": \"Você é um assistente pessoal e com base nos documentos a seguir deve relembrar ao usuário conteúdo de suas anotações e tarefas pendentes: " +
                docs.toString() + "\"" +
                "    }," +
                "    {" +
                "      \"role\": \"user\"," +
                "      \"content\": \"Olá, pode me dar uma base começar meu dia\" " +
                "    }" +
                "  ]," +
                "  \"model\": \"llama3-8b-8192\"," +
                "  \"temperature\": 1," +
                "  \"max_tokens\": 1024," +
                "  \"top_p\": 1," +
                "  \"stream\": false," +
                "  \"response_format\": {" +
                "    \"type\": \"json_object\"" +
                "  }," +
                "  \"stop\": null" +
                "}";

        System.out.println(requestBody);

        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
                        .header("Content-Type", "applicarion/json")
                        .header("Authorization", "Bearer " + dotenv.get("GROKAPIKEY"))
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

        HttpResponse<String> response =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
