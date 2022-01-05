package com.myprog.amountdesc;

public class AmountDescGeneratorImpl implements AmountDescGenerator {
    private static final long MAX_AMOUNT_VALUE = 1_000_000_000_000L;
    private CurrencyInfo currencyInfo;

    private static final String[][] ONES_DESC = new String[][] {
        {"один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять" }, // мужской род
        {"одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять" } // женский род
    };

    private static final String[] TENS_DESC = new String[] {
        "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    };

    private static final String[] NUMBERS_11_TO_19_DESC = new String[] {
        "одинадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };

    private static final String[] HUNDREDS_DESC = new String[] {
        "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"
    };

    private static final String[] DIGIT_TO_THOUSANDS_DESC = new String[] {
        // 0, 1, 2, 3, 4
        "тысяч", "тысяча", "тысячи", "тысячи", "тысячи",
        // 5, 6, 7, 8, 9
        "тысяч", "тысяч", "тысяч", "тысяч", "тысяч"
    };

    private static final String[] DIGIT_TO_MILLIONS_DESC = new String[] {
            // 0, 1, 2, 3, 4
            "миллионов", "миллион", "миллиона", "миллиона", "миллиона",
            // 5, 6, 7, 8, 9
            "миллионов", "миллионов", "миллионов", "миллионов", "миллионов"
    };

    private static final String[] DIGIT_TO_BILLIONS_DESC = new String[] {
            // 0, 1, 2, 3, 4
            "миллиардов", "миллиард", "миллиарда", "миллиарда", "миллиарда",
            // 5, 6, 7, 8, 9
            "миллиардов", "миллиардов", "миллиардов", "миллиардов", "миллиардов"
    };

    public AmountDescGeneratorImpl() {
        setCurrencyInfo(new CurrencyInfo());
    }

    @Override
    public void setCurrencyInfo(CurrencyInfo currencyInfo) {
        this.currencyInfo = currencyInfo;
    }

    @Override
    public String getAmountDesc(long amount) {
        if (amount <= 0 || amount >= MAX_AMOUNT_VALUE) {
            throw new IllegalArgumentException("Значение должно быть больше нуля и меньше 1 триллиона");
        }

        // Выделяем блоки по 3 цифры: начальные (с 1 до 999), тысячи, миллионы, миллиарды
        int beginAmount = (int) (amount % 1000);
        int thousandsAmount = (int) ( (amount / 1000) % 1000 );
        int millionsAmount = (int) ( (amount / 1_000_000) % 1000 );
        int billionsAmount = (int) ( (amount / 1_000_000_000) % 1000 );

        String fullDesc = "";

        String billionsDesc = get3digitBlockDesc(billionsAmount, NumberBlockType.BILLIONS);
        if (!billionsDesc.isEmpty()) {
            fullDesc = fullDesc + billionsDesc + " ";
        }
        String millionsDesc = get3digitBlockDesc(millionsAmount, NumberBlockType.MILLIONS);
        if (!millionsDesc.isEmpty()) {
            fullDesc = fullDesc + millionsDesc + " ";
        }
        String thousandsDesc = get3digitBlockDesc(thousandsAmount, NumberBlockType.THOUSANDS);
        if (!thousandsDesc.isEmpty()) {
            fullDesc = fullDesc + thousandsDesc + " ";
        }
        String beginDesc = get3digitBlockDesc(beginAmount, NumberBlockType.BEGIN);
        if (!beginDesc.isEmpty()) {
            fullDesc = fullDesc + beginDesc + " ";
        }

        fullDesc = fullDesc + currencyInfo.getDescForAmount(amount);

        return fullDesc;

        // return "/" + beginAmount + "/" + thousandsAmount + "/" + millionsAmount + "/" + billionsAmount;

        //return Long.toString(amount) + " " + currencyInfo.getDescForDigit((int) (amount % 10));
    }

    private String get3digitBlockDesc(int amount, NumberBlockType blockType) {
        if (amount == 0) {
            return "";
        }
        int onesDigit = amount % 10;
        int tensDigit = (amount / 10) % 10;
        int hundredsDigit = (amount / 100) % 10;

        String BlockDesc = "";

        int descriptionIndex = 0; // индекс для описания тысяч, миллионов, миллиардов

        if (hundredsDigit > 0) {
            // есть сотни
            BlockDesc += HUNDREDS_DESC[hundredsDigit-1] + " ";
        }

        // Отдельная обработка 10 - 19
        if (tensDigit == 1 && onesDigit >= 0 && onesDigit <= 9  ) {
            if (onesDigit == 0) {
                BlockDesc += TENS_DESC[0] + " ";
            }
            else {
                BlockDesc += NUMBERS_11_TO_19_DESC[onesDigit - 1] + " ";
            }
        }
        else {
            // кроме 10- 19

            if (tensDigit >= 2) {
                // есть десятки от двадцати и более
                BlockDesc += TENS_DESC[tensDigit-1] + " ";
            }

            if (onesDigit >= 1) {
                // есть единицы
                KindOfWord targetKindOfWord;
                if (blockType == NumberBlockType.BEGIN) {
                    // Для начальных цифр учитываем род валюты
                    // (например, один рубль, но одна рупия)
                    targetKindOfWord = currencyInfo.getKindOfWord();
                }else {
                    if (blockType == NumberBlockType.THOUSANDS) {
                        // у тысяч женский род
                        targetKindOfWord = KindOfWord.FEMALE;
                    } else {
                        // у миллионов и миллиардов мужской род
                        targetKindOfWord = KindOfWord.MALE;
                    }
                }
                BlockDesc += ONES_DESC[targetKindOfWord.ordinal()][onesDigit-1] + " ";
                descriptionIndex = onesDigit;
            }

        }
        // Добавляем описание для тысяч, миллионов, миллиардов
        if (blockType == NumberBlockType.THOUSANDS) {
            BlockDesc += DIGIT_TO_THOUSANDS_DESC[descriptionIndex];
        }
        else if (blockType == NumberBlockType.MILLIONS) {
            BlockDesc += DIGIT_TO_MILLIONS_DESC[descriptionIndex];
        }
        else if (blockType == NumberBlockType.BILLIONS) {
            BlockDesc += DIGIT_TO_BILLIONS_DESC[descriptionIndex];
        }

        return BlockDesc.trim();
    }
}
