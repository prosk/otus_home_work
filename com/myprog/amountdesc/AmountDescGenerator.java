package com.myprog.amountdesc;

public interface AmountDescGenerator {
    void setCurrencyInfo(CurrencyInfo currencyInfo);

    String getAmountDesc(long Amount);
}

