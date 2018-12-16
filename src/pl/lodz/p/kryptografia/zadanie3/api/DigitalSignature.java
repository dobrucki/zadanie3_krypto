package pl.lodz.p.kryptografia.zadanie3.api;

import pl.lodz.p.kryptografia.zadanie3.schnorr.Schnorr;
import pl.lodz.p.kryptografia.zadanie3.schnorr.Sign;
import pl.lodz.p.kryptografia.zadanie3.schnorr.Signature;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Scanner;

public class DigitalSignature {

    private Signature signature;
    private Sign sign;
    private Schnorr sc;

    public DigitalSignature(int bits){
        sc = new Schnorr();
        signature = sc.generateSignature(bits);

    }

    public DigitalSignature(){
        this(512);
    }

    public void generateKeys(String pathToPrivate, String pathToPublic) throws FileNotFoundException {
        PrintWriter privateKey = new PrintWriter(new File(pathToPrivate));
        PrintWriter publicKey = new PrintWriter(new File(pathToPublic));
        publicKey.println(signature.p);
        publicKey.println(signature.g);
        publicKey.println(signature.q);
        publicKey.println(signature.y);

        privateKey.println(signature.w);

        privateKey.close();
        publicKey.close();
    }

    public void signFile(String pathToFile, String pathToPrivate, String pathToPublic, String pathToSignature) throws Exception {
        Scanner sc1 = new Scanner(new FileReader(pathToPrivate));
        Scanner sc2 = new Scanner(new FileReader(pathToPublic));

        BigInteger w = sc1.nextBigInteger();
        BigInteger p = sc2.nextBigInteger();
        BigInteger g = sc2.nextBigInteger();
        BigInteger q = sc2.nextBigInteger();
        BigInteger y = sc2.nextBigInteger();

        sc1.close();
        sc2.close();

        Signature signature = new Signature(q, p, g, y, w);
        byte[] data = Files.readAllBytes(new File(pathToFile).toPath());
        Sign sign = sc.sign(data, signature);
        PrintWriter pw = new PrintWriter(new File(pathToSignature));
        pw.println(sign.s1);
        pw.println(sign.s2);
        pw.close();
    }

    public boolean verifyFile(String pathToFile, String pathToSignature, String pathToPublic) throws Exception{
        Scanner sc1 = new Scanner(new FileReader(pathToSignature));
        Scanner sc2 = new Scanner(new FileReader(pathToPublic));

        BigInteger p = sc2.nextBigInteger();
        BigInteger g = sc2.nextBigInteger();
        BigInteger q = sc2.nextBigInteger();
        BigInteger y = sc2.nextBigInteger();
        BigInteger w = new BigInteger("2"); // Dummy value

        Signature signature = new Signature(q, p, g, y, w);

        BigInteger s1 = sc1.nextBigInteger();
        BigInteger s2 = sc1.nextBigInteger();

        sc1.close();
        sc2.close();

        Sign sign = new Sign(s1, s2);
        boolean val = sc.verify(Files.readAllBytes(new File(pathToFile).toPath()), sign, signature);
        return val;
    }

}
