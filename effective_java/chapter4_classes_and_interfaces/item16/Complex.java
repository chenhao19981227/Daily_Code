package effective_java.chapter4_classes_and_interfaces.item16;

public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // 只提供getter，不提供setter
    public double realPart() { return re; }
    public double imaginaryPart() { return im; }

    //加
    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    //减
    public Complex subtract(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    //乘
    public Complex multiply(Complex c) {
        return new Complex(re * c.re - im * c.im,
                re * c.im + im * c.re);
    }

    //除
    public Complex divide(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
                (im * c.re - re * c.im) / tmp);
    }

    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Complex))
            return false;

        Complex c = (Complex) o;
        return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
    }

    @Override public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31 * result + hashDouble(im);
        return result;
    }

    private int hashDouble(double val) {
        long longBits = Double.doubleToLongBits(re);
        return (int) (longBits ^ (longBits >>> 32));
    }

    @Override public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
