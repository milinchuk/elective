package controller.security;

import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHolder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class for generation encrypt password
 *
 * @author Anastasia Milicnhuk
 */
public class EncryptPassword {
    /**
     * Logger for logging errors
     */
    private static final Logger logger = Logger.getLogger(EncryptPassword.class);

    /**
     * Name of algorithm that using for encryption
     */
    public static final String ALGORITHM = "MD5";
    public static final int RADIX = 16;
    public static final int SUFFIX = 0xff;
    public static final int PREFIX = 0x100;

    /**
     * Method for encryption
     * @param passwordToHash is password which will hash
     * @return encrypt password
     */
    public static String encrypt(String passwordToHash) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & SUFFIX) + PREFIX, RADIX).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            logger.error(LoggingMessagesHolder.ERROR_ENCRYPT_ALGORITHM);
            throw new RuntimeException(e);
        }
        return generatedPassword;
    }
}
