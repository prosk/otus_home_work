package com.myprog.amountdesc;

public class AmountDescApplication {
    private final IOService ioService;
    private final AmountDescGenerator amountDescGenerator;

    public AmountDescApplication(IOService ioService, AmountDescGenerator amountDescGenerator) {
        this.ioService = ioService;
        this.amountDescGenerator = amountDescGenerator;
    }

    public void run() {
        while (true) {
            ioService.outputString("Введите целое число больше 0 и менее 1 триллиона или команду \"exit\"");
            String command = ioService.inputString();
            if (command.equals("exit")) {
                break;
            } else {
                long amount = Long.parseLong(command);
                String amountDesc = amountDescGenerator.getAmountDesc(amount);
                ioService.outputString("Сумма прописью: %s", amountDesc);
            }
        }
    }

}
