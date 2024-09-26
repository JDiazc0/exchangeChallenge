package com.aluraChallenge.main;

import com.aluraChallenge.models.CurrencyConverter;
import com.aluraChallenge.models.CurrencySearch;
import com.aluraChallenge.record.CurrencyRecord;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner
        Scanner scanner = new Scanner(System.in);
        // While condition
        boolean isRunning = true;
        // Create request
        CurrencySearch currencySearch = new CurrencySearch();
        CurrencyRecord currencyRecord = currencySearch.searchCurrency("USD");



        while (isRunning){
            System.out.println("Bienvenido a Currency Challenge Converter");
            System.out.println("Ingrese una de las siguientes opciones para continuar");
            System.out.println("1. Para iniciar conversión");
            System.out.println("2. Para conocer las diferentes 'Currency Codes'");
            System.out.println("3. Para terminar el programa");

            String option = scanner.nextLine();

            switch (option){
                case "1":
                    System.out.println("Ingrese el 'Currency Code' de su divisa");
                    System.out.println("Ejemplo: USD (Dolár estadounidense)");
                    String search = scanner.nextLine().toUpperCase();
                    try {
                        CurrencyRecord currencyUser = currencySearch.searchCurrency(search);
                        System.out.println("Ingrese la cantidad a convertir");
                        double amount = Double.parseDouble(scanner.nextLine());
                        CurrencyConverter initialCurrency = new CurrencyConverter(search,currencyUser.conversion_rates().get(search));
                        initialCurrency.setAmount(amount);
                        System.out.println("Ingrese el 'Currency Code' a la divisa que desea convertir");
                        String secondSearch = scanner.nextLine().toUpperCase();
                        CurrencyConverter secondCurrency = new CurrencyConverter(secondSearch,currencyUser.conversion_rates().get(secondSearch));
                        System.out.println("El valor de la conversion es $"
                                +initialCurrency.convertAmount(secondCurrency)+ " "+
                                secondSearch);

                        System.out.println("¿Desea convertir otra divisa de nuevo? Y/N");
                        String continueConverting = scanner.nextLine().toUpperCase();
                        if (continueConverting.equals("Y")){
                            break;
                        } else if (continueConverting.equals("N")) {
                            isRunning = false;
                            break;
                        }else {
                            System.out.println("Opción inexistente reiniciando programa");
                            break;
                        }
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    for (Map.Entry<String, Double> entry : currencyRecord.conversion_rates().entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;
                case "3":
                    isRunning =false;
                    break;

            }
        }
    }
}