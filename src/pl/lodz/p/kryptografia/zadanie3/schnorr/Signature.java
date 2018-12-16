package pl.lodz.p.kryptografia.zadanie3.schnorr;

import java.math.BigInteger;

public class Signature {

    // Public section
    public final BigInteger q;
    public final BigInteger p;
    public final BigInteger g;
    public final BigInteger y;

    // Private section
    public final BigInteger w;

    public Signature(BigInteger q, BigInteger p, BigInteger g, BigInteger y, BigInteger w) {
        this.q = q;
        this.p = p;
        this.g = g;
        this.y = y;
        this.w = w;
    }
}
