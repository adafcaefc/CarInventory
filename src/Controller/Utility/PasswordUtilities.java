package Controller.Utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtilities
{
    private PasswordUtilities() { }

    private static final Random rng = new SecureRandom();

    public static String bytesToHex(byte[] hash)
    {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash)
        {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) { hexString.append('0'); }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String generateSalt()
    {
        byte[] salt = new byte[32];
        rng.nextBytes(salt);
        return bytesToHex(salt);
    }

    public static byte[] sha256Raw(String str) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String sha256(String str) throws NoSuchAlgorithmException
    {
        return bytesToHex(sha256Raw(str));
    }

    public static String sha256Salted(String str, String salt) throws NoSuchAlgorithmException
    {
        return sha256(sha256(str) + sha256(salt));
    }
}
