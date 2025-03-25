package com.example.tracknba.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    public static String makeHTTPRequest(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("X-RapidAPI-Key", "212d734f82msh63a8f35631feeadp11bb0ejsn3c5f43f66a62");
        connection.setRequestProperty("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();

        try {
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput)
                return scanner.next();
            else return null;
        }
        finally {
            connection.disconnect();
        }
    }
    public static String makeHTTPRequest2(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();

        try {
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput)
                return scanner.next();
            else return null;
        }
        finally {
            connection.disconnect();
        }
    }
}
