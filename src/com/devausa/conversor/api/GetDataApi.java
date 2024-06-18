package com.devausa.conversor.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetDataApi {
    private String monedaAConvertir;

    public void setMonedaAConvertir(String monedaAConvertir) {
        this.monedaAConvertir = monedaAConvertir;
    }

    public String getMonedaAConvertir() throws IOException, InterruptedException {
        String direction = "https://v6.exchangerate-api.com/v6/c988bed266bda48a53e5e890/latest/" + monedaAConvertir;

        //instancias para hacer la peticion
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direction))
                .build();
        HttpResponse<String> response = client
                // agregando la excepciones para que Java maneje los errores
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
