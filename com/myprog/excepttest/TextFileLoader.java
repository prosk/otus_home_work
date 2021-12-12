package com.myprog.excepttest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextFileLoader {
    final private String fileName;
    private List<String> fileRowList;

    public TextFileLoader(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("Имя файла не задано!");
        }
        if (fileName.length() == 0) {
            throw new IllegalArgumentException("Имя файла не может быть пустым!");
        }

        this.fileName = fileName;
    }

    // возвращает кол-во прочитанных строк
    public int loadRowsToList() {
        fileRowList = new ArrayList<>();
        BufferedReader fileBufferedReader = null;
        try {
            fileBufferedReader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
            String line;
            while((line = fileBufferedReader.readLine()) != null) {
                fileRowList.add(line);
            }
            return fileRowList.size();

        }catch(FileNotFoundException e) {
            System.err.println("Файл не найден!");
            return 0;
        }catch(IOException e) {
            System.err.println("Не удается прочитать файл, ошибка: " + e);
            return 0;
        }finally{
            if (fileBufferedReader != null) {
                try {
                    fileBufferedReader.close();
                }catch(IOException e) {
                    // Ничего не делаем
                }
            }
        }
    }

    public List<String> getRowList() {
        if (fileRowList == null) {
            return null;
        }
        return Collections.unmodifiableList(fileRowList);
    }


}
