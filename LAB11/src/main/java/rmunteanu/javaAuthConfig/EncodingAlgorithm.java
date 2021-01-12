package rmunteanu.javaAuthConfig;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodingAlgorithm {
    public static String encodeMD5(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toString();
    }

}
