package com.devausa.conversor.principal;

import com.devausa.conversor.api.GetDataApi;
import com.devausa.conversor.monedas.Monedas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner opcionIngresada = new Scanner(System.in);
        GetDataApi DataToApi = new GetDataApi();
        // creo una instancia de DecimalFormat con el formato ,00
        DecimalFormat df = new DecimalFormat("#.00");
        int opcion;
        double cantidad;

//        List<Titulo> titulos = new ArrayList<>();

        //uso el GsonBuilder
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        while (true){

            System.out.println("************************************************\n");
            System.out.println("Bienveido a Ausa Change - Tu conversor de confianza\n");
            System.out.println("1.- Dolar => Peso Argentino");
            System.out.println("2.- Peso Argentino => Dolar");
            System.out.println("3.- Dolar => Real Brasileño");
            System.out.println("4.- Real Brasileño => Dolar");
            System.out.println("5.- Dolar => Peso Colombiano");
            System.out.println("6.- Peso Colombiano => Dolar");
            System.out.println("7.- Dolar => Peso Chileno");
            System.out.println("8.- Peso Chileno => Dolar");
            System.out.println("9.- Dolar => Boliviano");
            System.out.println("10.- Boliviano => Dolar");
            System.out.println("0.- Salir");
            System.out.println("************************************************");

            System.out.println("Ingrese la opcion que desea hacer: ");
            opcion = opcionIngresada.nextInt();

            if (opcion == 0) {
                System.out.println("Gracias por utilizar nuestros servicios.");
                break;
            }

            DataToApi.setMonedaAConvertir("USD");
            String moneyExchange = null;
            try {
                moneyExchange = DataToApi.getMonedaAConvertir();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Monedas monedasList = gson.fromJson(moneyExchange, Monedas.class);
            // accedo al valor específico de "ARS" y las demas monedas
            Double usdValor = monedasList.conversion_rates().get("USD");
            Double arsValor = monedasList.conversion_rates().get("ARS");
            Double brlValor = monedasList.conversion_rates().get("BRL");
            Double copValor = monedasList.conversion_rates().get("COP");
            Double clpValor = monedasList.conversion_rates().get("CLP");
            Double bobValor = monedasList.conversion_rates().get("BOB");

            try {

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese la cantidad de USD a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " USD es igual a " + df.format(cantidad*arsValor) + " Pesos Argentinos.");
                        break;
                    case 2:
                        System.out.println("Ingrese la cantidad de ARS a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " ARS es igual a " + df.format(cantidad/arsValor) + " Dolares.");
                        break;
                    case 3:
                        System.out.println("Ingrese la cantidad de USD a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " USD es igual a " + df.format(cantidad*brlValor) + " Reales Brasileños.");
                        break;
                    case 4:
                        System.out.println("Ingrese la cantidad de BRL a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " BRL es igual a " + df.format(cantidad/brlValor) + " Dolares.");
                        break;
                    case 5:
                        System.out.println("Ingrese la cantidad de USD a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " USD es igual a " + df.format(cantidad*copValor) + " Pesos Colombianos.");
                        break;
                    case 6:
                        System.out.println("Ingrese la cantidad de COP a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " COP es igual a " + df.format(cantidad/copValor) + " Dolares.");
                        break;
                    case 7:
                        System.out.println("Ingrese la cantidad de USD a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " USD es igual a " + df.format(cantidad*clpValor) + " Pesos Chilenos.");
                        break;
                    case 8:
                        System.out.println("Ingrese la cantidad de CLP a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " CLP es igual a " + df.format(cantidad/clpValor) + " Dolares.");
                        break;
                    case 9:
                        System.out.println("Ingrese la cantidad de USD a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " USD es igual a " + df.format(cantidad*bobValor) + " Bolivianos.");
                        break;
                    case 10:
                        System.out.println("Ingrese la cantidad de BOB a convertir: ");
                        cantidad = opcionIngresada.nextDouble();
                        System.out.println("El valor de " + cantidad + " BOB es igual a " + df.format(cantidad/bobValor) + " Dolares.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }
}
