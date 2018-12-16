package pl.lodz.p.kryptografia.zadanie3.schnorr;

import java.math.BigInteger;

public class Sign {
    public final BigInteger s1;
    public final BigInteger s2;

    public Sign(BigInteger s1, BigInteger s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
}
