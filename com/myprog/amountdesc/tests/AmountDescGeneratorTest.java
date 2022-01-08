package com.myprog.amountdesc.tests;

import com.myprog.amountdesc.AmountDescGeneratorImpl;
import com.myprog.unittests.assertions.*;

public class AmountDescGeneratorTest {
    private static final AmountDescGeneratorImpl amountDescGenerator = new AmountDescGeneratorImpl();

    private static class AmountDescTestCase {
        private long amount;
        private String desc;

        public AmountDescTestCase(long amount, String desc) {
            this.amount = amount;
            this.desc = desc;
        }

        public long getAmount() {
            return amount;
        }

        public String getDesc() {
            return desc;
        }
    }

    private static final AmountDescTestCase[] testCases = new AmountDescTestCase[] {
        new AmountDescTestCase(3, "три рубля"),
        new AmountDescTestCase(8, "восемь рублей"),
        new AmountDescTestCase(713, "семьсот тринадцать рублей"),
        new AmountDescTestCase(1071, "одна тысяча семьдесят один рубль"),
        new AmountDescTestCase(2594, "две тысячи пятьсот девяносто четыре рубля"),
        new AmountDescTestCase(14036, "четырнадцать тысяч тридцать шесть рублей"),
        new AmountDescTestCase(170062, "сто семьдесят тысяч шестьдесят два рубля"),
        new AmountDescTestCase(158_242_022, "сто пятьдесят восемь миллионов двести сорок две тысячи двадцать два рубля"),
        new AmountDescTestCase(30_000_060_000L, "тридцать миллиардов шестьдесят тысяч рублей"),
        new AmountDescTestCase(400_000_000_002L, "четыреста миллиардов два рубля"),
    };


    public static void testAmountDescGeneratorWithZeroAmount() {
        String scenario = "Тест для нулевой суммы";
        try {
            Throwable actual = null;
            try {
                amountDescGenerator.getAmountDesc(0);
            } catch (Throwable e) {
                actual = e;
            }

            if (actual == null) {
                throw new AssertionError("Given code does not throw any exception");
            } else {
                Assertions.assertEquals(IllegalArgumentException.class, actual.getClass());
            }

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public static void testAmountDescGeneratorWithNegativeAmount() {
        String scenario = "Тест для отрицательной суммы";
        try {
            Throwable actual = null;
            try {
                amountDescGenerator.getAmountDesc(-10);
            } catch (Throwable e) {
                actual = e;
            }

            if (actual == null) {
                throw new AssertionError("Given code does not throw any exception");
            } else {
                Assertions.assertEquals(IllegalArgumentException.class, actual.getClass());
            }

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public static void testAmountDescGeneratorWithTooBigAmount() {
        String scenario = "Тест для слишком большой суммы";
        try {
            Throwable actual = null;
            try {
                amountDescGenerator.getAmountDesc(1_000_000_000_000L);
            } catch (Throwable e) {
                actual = e;
            }

            if (actual == null) {
                throw new AssertionError("Given code does not throw any exception");
            } else {
                Assertions.assertEquals(IllegalArgumentException.class, actual.getClass());
            }

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public static void testAmountDescGeneratorWithMinMaxAmount() {
        String scenario = "Тест для минимальной и максимальной сумм";
        try {
            String expected = "один рубль";
            String actual = amountDescGenerator.getAmountDesc(1);
            Assertions.assertEquals(expected, actual);

            String descFor999 = "девятьсот девяносто девять";
            expected = descFor999 + " миллиардов " +
                       descFor999 + " миллионов " +
                       descFor999 + " тысяч " +
                       descFor999 + " рублей";
            actual = amountDescGenerator.getAmountDesc(999_999_999_999L);
            Assertions.assertEquals(expected, actual);

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public static void testAmountDescGeneratorWithFixedAmounts() {
        String scenario = "Тест для заранее выбранных сумм";
        String expected;
        String actual;
        try {
            for (AmountDescTestCase testCase: testCases) {
                expected = testCase.getDesc();
                actual = amountDescGenerator.getAmountDesc(testCase.getAmount());
                Assertions.assertEquals(expected, actual);
            }

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Все тесты
        testAmountDescGeneratorWithZeroAmount();
        testAmountDescGeneratorWithNegativeAmount();
        testAmountDescGeneratorWithTooBigAmount();
        testAmountDescGeneratorWithMinMaxAmount();
        testAmountDescGeneratorWithFixedAmounts();
    }
 }
