
public class Rational extends Number implements Comparable<Rational> {

    private long numerator;
    private long denominator;

    // Constructor
    public Rational() {
        this(0, 1);
    }

    public Rational(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }

        long gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    // Find GCD
    private static long gcd(long n, long d) {
        while (d != 0) {
            long temp = d;
            d = n % d;
            n = temp;
        }
        return n;
    }

    // Add
    public Rational add(Rational second) {
        long n = numerator * second.denominator
                + denominator * second.numerator;
        long d = denominator * second.denominator;
        return new Rational(n, d);
    }

    // Subtract
    public Rational subtract(Rational second) {
        long n = numerator * second.denominator
                - denominator * second.numerator;
        long d = denominator * second.denominator;
        return new Rational(n, d);
    }

    // Multiply
    public Rational multiply(Rational second) {
        return new Rational(
                numerator * second.numerator,
                denominator * second.denominator
        );
    }

    // Divide
    public Rational divide(Rational second) {
        return new Rational(
                numerator * second.denominator,
                denominator * second.numerator
        );
    }

    // toString
    @Override
    public String toString() {
        if (denominator == 1) {
            return numerator + ""; 
        }else {
            return numerator + "/" + denominator;
        }
    }

    // Compare
    @Override
    public int compareTo(Rational o) {
        long diff = this.subtract(o).numerator;
        if (diff > 0) {
            return 1; 
        }else if (diff < 0) {
            return -1; 
        }else {
            return 0;
        }
    }

    // Equals
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rational)) {
            return false;
        }
        return this.compareTo((Rational) obj) == 0;
    }

    // Number methods
    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        return (double) numerator / denominator;
    }

    @Override
    public long longValue() {
        return (long) doubleValue();
    }
}
