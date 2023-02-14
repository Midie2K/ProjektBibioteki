package pl.edu.wszib.biblioteka;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.biblioteka.core.Authenticator;

public class Hashing {
    public static void main(String[] args) {
        Authenticator authenticator = Authenticator.getIstance();
        System.out.println(DigestUtils.md5Hex("admin" + authenticator.seed));
    }
}
