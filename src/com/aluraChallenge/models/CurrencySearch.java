package com.aluraChallenge.models;

import com.aluraChallenge.record.CurrencyRecord;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencySearch {

    public CurrencyRecord searchCurrency(String currencyCode){

        URI url = URI.create("https://v6.exchangerate-api.com/v6/fde6afcc3caeeb0e9e35f84e/latest/"+currencyCode);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), CurrencyRecord.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al realizar la petición a la API: " + e.getMessage());
        }

    }
}
