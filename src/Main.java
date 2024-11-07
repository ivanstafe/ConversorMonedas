import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/197806055870768b13a0a417/latest/USD"; //
    private static double tasaARS, tasaBOB, tasaBRL, tasaCLP, tasaCOP, tasaUSD;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String baseCurrency = "USD"; // Divisa base

        System.out.println("Bienvenido al Conversor de Monedas");

        obtenerTasaCambio(baseCurrency);

        while (true) {
            System.out.println("\n1. Realizar conversión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción (1/2): ");

            int opcion = -1;
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                System.out.println("Opción inválida, por favor ingrese 1 o 2.");
                scanner.next();
                continue;
            }

            if (opcion == 1) {
                realizarConversion(scanner);
            } else if (opcion == 2) {
                System.out.println("¡Gracias por usar el Conversor de Monedas! Hasta pronto.");
                break;
            } else {
                System.out.println("Opción inválida, por favor intente de nuevo.");
            }
        }
    }

    public static void obtenerTasaCambio(String baseCurrency) {
        try {
            String url_str = API_URL;
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

            tasaARS = conversionRates.get("ARS").getAsDouble();
            tasaBOB = conversionRates.get("BOB").getAsDouble();
            tasaBRL = conversionRates.get("BRL").getAsDouble();
            tasaCLP = conversionRates.get("CLP").getAsDouble();
            tasaCOP = conversionRates.get("COP").getAsDouble();
            tasaUSD = conversionRates.get("USD").getAsDouble();

        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }
    }

    private static void realizarConversion(Scanner scanner) {
        System.out.println("\nSeleccione la moneda de origen (USD, ARS, BOB, BRL, CLP, COP):");
        String monedaOrigen = scanner.next().toUpperCase();

        if (!monedaValida(monedaOrigen)) {
            System.out.println("Moneda inválida. Las monedas válidas son: USD, ARS, BOB, BRL, CLP, COP.");
            return;
        }

        System.out.println("Seleccione la moneda destino (USD, ARS, BOB, BRL, CLP, COP):");
        String monedaDestino = scanner.next().toUpperCase();

        if (!monedaValida(monedaDestino)) {
            System.out.println("Moneda inválida. Las monedas válidas son: USD, ARS, BOB, BRL, CLP, COP.");
            return;
        }

        System.out.print("Ingrese el monto a convertir: ");
        double monto;

        if (!scanner.hasNextDouble()) {
            System.out.println("Monto inválido. Por favor ingrese un número válido.");
            scanner.next();
            return;
        }

        monto = scanner.nextDouble();

        if (monto <= 0) {
            System.out.println("El monto debe ser un número positivo.");
            return;
        }

        double resultado = convertirMoneda(monto, monedaOrigen, monedaDestino);
        System.out.printf("\nEl monto convertido de %s a %s es: %.2f\n", monedaOrigen, monedaDestino, resultado);
    }

    private static double convertirMoneda(double monto, String monedaOrigen, String monedaDestino) {
        double tasaOrigen = obtenerTasa(monedaOrigen);
        double tasaDestino = obtenerTasa(monedaDestino);
        return (monto / tasaOrigen) * tasaDestino;
    }

    private static double obtenerTasa(String moneda) {
        return switch (moneda) {
            case "ARS" -> tasaARS;
            case "BOB" -> tasaBOB;
            case "BRL" -> tasaBRL;
            case "CLP" -> tasaCLP;
            case "COP" -> tasaCOP;
            case "USD" -> tasaUSD;
            default -> throw new IllegalArgumentException("Código de moneda no soportado: " + moneda);
        };
    }

    private static boolean monedaValida(String moneda) {
        return moneda.equals("USD") || moneda.equals("ARS") || moneda.equals("BOB") || moneda.equals("BRL") || moneda.equals("CLP") || moneda.equals("COP");
    }
}
