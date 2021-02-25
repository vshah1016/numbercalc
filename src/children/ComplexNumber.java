package children;

import parents.Number;
import parents.RealNumber;

public class ComplexNumber implements Number {

    public final RealNumber realPart;
    public final RealNumber imaginaryPart;

    public ComplexNumber(RealNumber realPart, RealNumber imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    @Override
    public Number add(Number num) {
        ComplexNumber complexNumber = (ComplexNumber) num;
        RealNumber realSum = realPart.add(complexNumber.realPart);
        RealNumber imaginarySum = imaginaryPart.add(complexNumber.imaginaryPart);
        return new ComplexNumber(realSum, imaginarySum);
    }

    @Override
    public Number subtract(Number num) {
        ComplexNumber complexNumber = (ComplexNumber) num;
        RealNumber realSum = realPart.subtract(complexNumber.realPart);
        RealNumber imaginarySum = imaginaryPart.subtract(complexNumber.imaginaryPart);
        return new ComplexNumber(realSum, imaginarySum);
    }

    @Override
    public Number multiply(Number num) {
        ComplexNumber complexNumber = (ComplexNumber) num;
        RealNumber realProduct = realPart.multiply(complexNumber.realPart).add(imaginaryPart.multiply(complexNumber.imaginaryPart).multiply(Fraction.wholeNumber(-1)));
        RealNumber imaginaryProduct = realPart.multiply(complexNumber.imaginaryPart).add(imaginaryPart.multiply(complexNumber.realPart));
        return new ComplexNumber(realProduct, imaginaryProduct);
    }

    @Override
    public Number divide(Number num) {
        ComplexNumber complexNumber = (ComplexNumber) num;
        RealNumber realQuotient = realPart.multiply(complexNumber.realPart).add(imaginaryPart.multiply(complexNumber.imaginaryPart));
        realQuotient = realQuotient.divide(complexNumber.realPart.multiply(complexNumber.realPart).add(complexNumber.imaginaryPart.multiply(complexNumber.imaginaryPart)));
        RealNumber imagQuotient = imaginaryPart.multiply(complexNumber.realPart).subtract(realPart.multiply(complexNumber.imaginaryPart));
        imagQuotient = imagQuotient.divide(complexNumber.realPart.multiply(complexNumber.realPart).add(complexNumber.imaginaryPart.multiply(complexNumber.imaginaryPart)));
        return new ComplexNumber(realQuotient, imagQuotient);
    }

    @Override
    public String toString() {
        String toString = "";

        if (realPart.sign() != 0) toString += realPart.toString() + " ";
        if (imaginaryPart.sign() == 0) {
            if (toString.equals("")) return "0";
        } else if (imaginaryPart.sign() == -1) toString += imaginaryPart.getValue() != -1.0 ? "- " + imaginaryPart.multiply(Fraction.wholeNumber(-1)).toString() + "i" : "-" + "i";
        else toString += toString.equals("") ? imaginaryPart.getValue() == 1 ? "i" : imaginaryPart.toString() + "i" : "+ " + imaginaryPart.toString() + "i";

        return toString;
    }
}