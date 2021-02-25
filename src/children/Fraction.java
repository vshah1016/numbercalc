package children;

import error.DivideByZero;
import parents.Number;
import parents.RealNumber;

public class Fraction extends RealNumber {
    public final int numerator;
    public final int denominator;

    public final boolean whole;

    public Fraction(int numerator, int denominator) {
        if (denominator < 0) {
            denominator *= -1;
            numerator *= -1;
        }
        int gcd = 1;
        if (numerator != 0) gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
        whole = this.denominator == 1;
        assert (this.denominator != 0);
    }

    public static Fraction wholeNumber(int number) {
        return new Fraction(number, 1);
    }

    public double getValue() {
        return ((double) numerator) / denominator;
    }

    @Override
    public int sign() {
        if (getValue() == 0) return 0;
        else if (getValue() < 0) return -1;
        else return 1;
    }

    @Override
    public RealNumber add(Number num) {
        if (num instanceof Double) {
            if (((Double) num).whole) return add(Fraction.wholeNumber((int) ((Double) num).value));
            return ((Double) num).add(this);
        }
        Fraction n = (Fraction) num;
        return new Fraction(n.numerator * denominator + numerator * n.denominator, n.denominator * denominator);
    }

    @Override
    public RealNumber subtract(Number num) {
        if (num instanceof Double) {
            if (((Double) num).whole) return subtract(Fraction.wholeNumber((int) ((Double) num).value));
            return ((Double) num).subtract(this).multiply(Fraction.wholeNumber(-1));
        }
        Fraction n = (Fraction) num;
        return new Fraction(numerator * n.denominator - n.numerator * denominator, n.denominator * denominator);
    }

    @Override
    public RealNumber multiply(Number num) {
        if (num instanceof Double) {
            if (((Double) num).whole) return multiply(Fraction.wholeNumber((int) ((Double) num).value));
            return ((Double) num).multiply(this);
        }

        Fraction n = (Fraction) num;
        return new Fraction(numerator * n.numerator, denominator * n.denominator);
    }

    @Override
    public RealNumber divide(Number num) {
        if (num instanceof Double) {
            if (((Double) num).getValue() == 0.0) throw new DivideByZero();
            if (((Double) num).whole) return divide(Fraction.wholeNumber((int) ((Double) num).value));
            return new Double(Math.pow(((Double) num).divide(this).getValue(), -1));
        }
        Fraction n = (Fraction) num;
        return new Fraction(numerator * n.denominator, denominator * n.numerator);
    }

    @Override
    public String toString() {
        return whole ? numerator + "" : numerator + "/" + denominator;
    }

    private int gcd(int numerator, int denominator) {
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        int gcd = 0;
        for (int i = 1; i <= numerator && i <= denominator; i++) {
            if (numerator % i == 0 && denominator % i == 0)
                gcd = i;
        }
        return gcd;
    }
}
