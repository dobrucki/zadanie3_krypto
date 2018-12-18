package pl.lodz.p.kryptografia.zadanie3.schnorr;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Schnorr {


    public Signature generateSignature(int bits){
        Random rnd = new SecureRandom();
        int certainity = 100;

        BigInteger q = BigInteger.probablePrime(bits, rnd);
        BigInteger p;

        BigInteger qp = BigInteger.ONE;

        do{
            p = q.multiply(qp).multiply(BigInteger.valueOf(2)).add(BigInteger.ONE);
            qp = qp.add(BigInteger.ONE);
        }while(!p.isProbablePrime(certainity));
        BigInteger a, g;
        do{
            a = (BigInteger.valueOf(2).add(new BigInteger(bits, certainity, rnd))).mod(p);
            BigInteger ga = (p.subtract(BigInteger.ONE)).divide(q);
            g = a.modPow(ga, p);
        }while(g.compareTo(BigInteger.ONE) == 0);

        BigInteger w = new BigInteger(bits, rnd);
        BigInteger y = g.modPow(w, p);

        return new Signature(q, p, g, y, w);
    }

    public Sign sign(byte[] message, Signature sig) throws NoSuchAlgorithmException {

        Random rnd = new SecureRandom();
        BigInteger x, r, s1, s2;
        r = new BigInteger(sig.q.bitLength(), rnd);
        x = sig.g.modPow(r, sig.p);
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.reset();
        md.update(message);
        md.update(x.toByteArray()); // ???? sprawdzic (x.toString.getBytes())
        byte[] d = md.digest();
        s1 = new BigInteger(1, d);
        s2 = (r.subtract(sig.w.multiply(s1))).mod(sig.q);

        return new Sign(s1, s2);
    }

    public boolean verify(byte[] message, Sign sign, Signature signature) throws NoSuchAlgorithmException{
        Random rnd = new SecureRandom();
        BigInteger x1 = signature.g.modPow(sign.s2, signature.p);
        BigInteger x2 = signature.y.modPow(sign.s1, signature.p).mod(signature.p);
        BigInteger x = x1.multiply(x2).mod(signature.p);

        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.reset();
        md.update(message);
        md.update(x.toByteArray());
        byte[] d = md.digest();
        BigInteger h = new BigInteger(1, d);
        if(sign.s1.equals(h)) return true;
        return false;
    }
}
