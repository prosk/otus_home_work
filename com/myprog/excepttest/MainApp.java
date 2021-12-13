package com.myprog.excepttest;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final String INPUT_FILE_NAME =
            "G:\\Мой диск\\Programs\\Java\\otus_home_work\\com\\myprog\\excepttest\\input_file.txt";

    private static final String URL_STRING = "https://www.rbc.ru/";
    private static final int MAX_URL_LINES = 100000;

    public static void main(String[] args) {
        TextFileLoader fileLoader = new TextFileLoader(INPUT_FILE_NAME);

        int rowsCount = fileLoader.loadRowsToList();
        if(rowsCount == 0) {
            System.err.println("Не удалось прочитать входные параметры из файла: " + INPUT_FILE_NAME);
            System.exit(100);
        }else{
            System.out.println("Кол-во строк в файле: " + rowsCount);
        }

        List<String> fileRows = fileLoader.getRowList();
        System.out.println("FileRows = " + fileRows);

        // Открываем URL
        try {
            var url = new URL(URL_STRING);
            URLConnection connection = url.openConnection();

            connection.connect();

            String encoding = connection.getContentEncoding();
            if (encoding == null) {
                encoding = "UTF-8";
            }

            try (var in = new Scanner(connection.getInputStream(), encoding)) {
                for (int n = 1; in.hasNextLine() && n < MAX_URL_LINES; n++ ) {
                    String currUrlLine = in.nextLine();
                    boolean isSearchWord = false;
                    for(String searchWord: fileRows) {
                        if (currUrlLine.contains(searchWord)) {
                            isSearchWord = true;
                            break;
                        }
                    }
                    if (isSearchWord) {
                        System.out.println(currUrlLine);
                    }
                }
            }

        }catch(Exception e) {
            // выбрасваем наверх
            // например, Caused by: java.net.ConnectException: Connection timed out: connect
            throw new RuntimeException("Ошибка при обращении к URL", e);
        }

    }
}
