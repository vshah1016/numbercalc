package children;

import error.DivideByZero;
import parents.Number;
import parents.RealNumber;

import java.text.DecimalFormat;

public class Double extends RealNumber{
    public static final DecimalFormat df = new DecimalFormat("0.####");
    public final double value;
    public final boolean whole;

    public Double(double value) {
        this.value = value;
        whole = value % 1 == 0;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public int sign() {
        if(value == 0) return 0;
        else return value > 0 ? 1 : -1;
    }

    @Override
    public RealNumber add(Number num) {
        if(whole) return Fraction.wholeNumber((int) value).add(num);
        return new Double(value + ((RealNumber) num).getValue());
    }

    @Override
    public RealNumber subtract(Number num) {
        if(whole) return Fraction.wholeNumber((int) value).subtract(num);
        return new Double(value - ((RealNumber) num).getValue());
    }

    @Override
    public RealNumber multiply(Number num) {
        if(whole) return Fraction.wholeNumber((int) value).multiply(num);
        return new Double(value * ((RealNumber) num).getValue());
    }

    @Override
    public RealNumber divide(Number num) {
        if (((RealNumber) num).getValue() == 0) throw new DivideByZero();
        if(whole) return Fraction.wholeNumber((int) value).divide(num);
        return new Double(value / ((RealNumber) num).getValue());
    }

    @Override
    public String toString() {
        if (whole) return java.lang.Integer.toString((int)value);
        return df.format(value);
    }
}
