package com.myprog.amountdesc;

public class Main {

    public static void main(String[] args) {
        IOService ioService = new IOStreamsService(System.in, System.out);
        AmountDescGenerator amountDescGenerator = new AmountDescGeneratorImpl();
        AmountDescApplication application = new AmountDescApplication(ioService, amountDescGenerator);
        application.run();
    }
}