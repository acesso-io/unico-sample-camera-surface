package com.unico.camerasurface.camera.utils.exif;// TODO: Move this class to under util package.
/**
 * The rational data type of EXIF tag. Contains a pair of longs representing the
 * numerator and denominator of a Rational number.
 */
public class Rational {
    private final long mNumerator;
    private final long mDenominator;
    /**
     * Create a Rational with a given numerator and denominator.
     *
     * @param numerator
     * @param denominator
     */
    public Rational(long numerator, long denominator) {
        mNumerator = numerator;
        mDenominator = denominator;
    }
    /**
     * Create a copy of a Rational.
     */
    public Rational(Rational r) {
        mNumerator = r.mNumerator;
        mDenominator = r.mDenominator;
    }
    /**
     * Gets the numerator of the rational.
     */
    public long getNumerator() {
        return mNumerator;
    }
    /**
     * Gets the denominator of the rational
     */
    public long getDenominator() {
        return mDenominator;
    }
    /**
     * Gets the rational value as type double. Will cause a divide-by-zero error
     * if the denominator is 0.
     */
    public double toDouble() {
        return mNumerator / (double) mDenominator;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Rational) {
            Rational data = (Rational) obj;
            return mNumerator == data.mNumerator && mDenominator == data.mDenominator;
        }
        return false;
    }
    @Override
    public String toString() {
        return mNumerator + "/" + mDenominator;
    }
}