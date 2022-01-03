package com.myprog.amountdesc;

public interface IOService {
    String inputString();
    void outputString(String message);
    void outputString(String template, Object ...args);
}