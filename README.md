# Algorithm

*NOTE: all decimals printed are rounded to four decimal places. If you use very small numbers you mights see 0, but it would have
actually calculated it.*

#File Structure
We have four modules in `src/`.
> * Children
>   * Complex Number
>   * Double
>   * Fraction
> * Error
>   * DivideByZero
> * Parents
>   * Number - Given by Mr. Geary
>   * RealNumber
> * Runtime
>   * Client
>   * Main

## Main.java

This is where the program starts.

We start with the instructions being printed to the console, so the user knows what to do. We enter a While Loop, so the user can keep using the calculator
and doesn't have to keep running the program again and again.

So we get the expression that they want to evaluate from `System.in` using a `Scanner`. Then we check if the expression is 
one for the complex calculator by simply checking for a left parenthesis.

Now, assuming `complexSolving = true`, we remove all of the spaces from the input. I split it based on the operation in the middle
using RegEx magic. Then I assign two variables, `a`, and `b`, using a function called `parseComplex` which parses the input `String`
into a `ComplexNumber` object. Then based on the sign the program does that operation.

If we are doing a normal solve, we use the same logic, we assign two variables `a`, and `b` using a function that we wrote: `parseReal`. Then based on
the sign we do the correct operation. We then display the answer.

## Client.java
This just handles the While Loop in the main class. This also holds the scanner object.

## RealNumber
An abstract class and a parent class to `Fraction` and `Double`. This just implements `Number` the interface given to us.
It adds a few attributes:
> ```java
> boolean whole;
> public abstract double getValue();
> public abstract int sign();
> ```

This is, so we can tell if a number is whole e.x "5". The next to get the value in a double, so we can do arithmetic.

`sign()` returns an `int` based on the sign of the value.
> * 0 for zero
> * 1 for positive
> * -1 for negative

## Children Class
This is where most of the logic went. I will explain it quickly.

## Fraction.java

`Fraction` class extends `RealNumber`. It contains three variables, `numerator`, `denominator`, and `whole` (this one because it extends `RealNumber`).

### Constructors
 I wanted to keep the numerator negative if the fraction was negative at all for consistency. Therefore, the first thing I do in the constructor
 is check if the denominator is negative, if it is, the program will multiply both the numerator and denominator by -1.
Next, it finds the greatest common denominator and self simplifies itself, this way we never have to simplify a fraction, it will always be simplified.

Then the constructor just sets `whole` to true if the number is whole and then makes sure the denominator is not zero.

The `.wholeNumber` constructor just makes a `new Fraction(wholeNumber, 1)`;
### Methods
> * sign() - this returns the sign. Explained in `RealNumber`
> * getValue() - ^
> * `add()` / `multiply()` / `subtract()` / `divide()` - explanatory
> 

The process to do any arithmetic was easy. If they were whole numbers, they were treated as `Fractions` over one.
These were the equations I used:
> Lets say we have a, b, c, d representing two fractions: a/b and c/d.
> 
> > addition: (a * d) + (c * b) / (b * d)
> 
> > subtraction: (a * d) - (c * b) / (b * d)
> 
> > multiplication: (a * c) / (b * d)
> 
> > division: (a * d) / (b * c)



## Double.java
This is a more simple "base" version almost of the `Fraction` class. It employs less logic.

The `Double` is just my own implementation of a java `double` but as a `RealNumber` hence, `Double extends RealNumber` in my application.
There are a few fields: the first is a static `DecimalFormat("0.####")` this static object is used to round any decimal numbers
to four decimal digits so that things are better to read.
There is a `double` primitive value called `value` that holds the `value` of the `Double` `RealNumber` object.
Finally, because this is a `RealNumber` there is also a `boolean whole` field as well. This is set using the boolean expression:
`whole = value % 1 == 0`. The `sign()` method is also analogous to the one in the `Fraction` class.


### Methods

Add, subtract, multiply, and divide were even easier. After you filter out the whole numbers by doing a simple 
`if(whole) return Fraction.wholeNumber((int) value).OPERATION(num);` you can just do a simple `return new Double(value OPERATION ((RealNumber) num).getValue())'`.

There is also a `toString()` all it does it return the whole number if it is whole, otherwise it will use teh `df` `DecimalFormat` object to round the
Double to four digits.

## ComplexNumber

This was the tough one to do. I did not give up though! This was the extra credit. This was my approach to the `ComplexNumber`.

Firstly, the `ComplexNumber` would hold simply two fields: `realPart` and `imaginaryPart`. Can you guess what they do? They are both `RealNumber` objects.
The process was actually really easy I came up with some formulas to solve it using a, b, c and d.