package com.myprog.amountdesc;

public class AmountDescGeneratorImpl implements AmountDescGenerator {
    private CurrencyInfo currencyInfo;

    public AmountDescGeneratorImpl() {
        setCurrencyInfo(new CurrencyInfo());
    }

    @Override
    public void setCurrencyInfo(CurrencyInfo currencyInfo) {
        this.currencyInfo = currencyInfo;
    }

    @Override
    public String getAmountDesc(long amount) {
        return Long.toString(amount) + " " + currencyInfo.getDescForDigit((int) (amount % 10));
    }
}
