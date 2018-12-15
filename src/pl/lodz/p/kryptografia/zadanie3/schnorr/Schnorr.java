package pl.lodz.p.kryptografia.zadanie3.schnorr;


import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Random;

public class Schnorr {

    public static void sign() throws NoSuchAlgorithmException{
        Random rnd = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(1024, rnd);
        BigInteger q;
        do{
            q = BigInteger.probablePrime(160, rnd);
        }while(p.subtract(BigInteger.ONE).mod(q).compareTo(BigInteger.ZERO) == 0);

//        System.out.println("P: " + p);
//        System.out.println("Q: " + q);
        BigInteger a;
        do{
            a = new BigInteger(40, rnd);
        }while(a.compareTo(BigInteger.ONE) != 0 && a.modPow(q, p).compareTo(BigInteger.ONE) == 0);
//        System.out.println(a);


        // Private key
        BigInteger s;
        do {
            s = new BigInteger(40, rnd);
        } while(s.compareTo(q) != -1 && s.compareTo(BigInteger.ONE) != 1);

        System.out.println("Private key: " + s);

        // Public key
        BigInteger v;
        v = a.modPow(s.negate(), p);
        System.out.println("Public key: " + v);


        // Digital Signature part

        // Example message
        String M = "Jakas wiadomosc. ";

        BigInteger r;
        do{
            r = new BigInteger(40, rnd);
        }while(s.compareTo(q) != -1 && s.compareTo(BigInteger.ONE) != 1);
        BigInteger x = a.modPow(r, p);
        String message = M + x.toString();

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.reset();
        messageDigest.digest(message.getBytes());
        byte[] md = messageDigest.digest();
        BigInteger e = new BigInteger(md);
        System.out.println(e);


        BigInteger se = s.multiply(e);
        BigInteger y = (r.add(se)).mod(q);


        BigInteger x1 = (a.modPow(y, p).multiply(v.modPow(e, p))).mod(p);

        String message1 = M + x1.toString();

        MessageDigest mes = MessageDigest.getInstance("SHA-256");
        messageDigest.reset();
        messageDigest.digest(message1.getBytes());
        byte[] md1 = messageDigest.digest();
        BigInteger e1 = new BigInteger(md1);

        System.out.println(e1);



    }
}
