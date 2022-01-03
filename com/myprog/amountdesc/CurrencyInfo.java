package com.myprog.amountdesc;

import java.util.Arrays;
import java.util.Objects;

public class CurrencyInfo {
    private final String fullUniqueName;
    private final KindOfWord kindOfWord;
    private final String[] digitToDesc;

    public CurrencyInfo() {
        fullUniqueName = "РУБЛЬ";
        kindOfWord = KindOfWord.MALE;
        digitToDesc = new String[] {
          // 0, 1, 2, 3, 4
          "рублей", "рубль", "рубля", "рубля", "рубля",
          // 5, 6, 7, 8, 9
          "рублей", "рублей", "рублей", "рублей", "рублей"
        };
    }

    public CurrencyInfo(String fullUniqueName, KindOfWord kindOfWord, String[] digitToDesc) {
        this.fullUniqueName = fullUniqueName;
        this.kindOfWord = kindOfWord;
        this.digitToDesc = new String[digitToDesc.length];
        for(int i = 0; i < digitToDesc.length; i++) {
            this.digitToDesc[i] = digitToDesc[i];
        }
    }

    public String getDescForDigit(int digit) {
        return digitToDesc[digit];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyInfo that = (CurrencyInfo) o;
        return fullUniqueName.equals(that.fullUniqueName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullUniqueName);
    }

    @Override
    public String toString() {
        return "CurrencyInfo{" +
                "fullUniqueName='" + fullUniqueName + '\'' +
                ", kindOfWord=" + kindOfWord +
                ", digitToDesc=" + Arrays.toString(digitToDesc) +
                '}';
    }
}
