package controller.security;

import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;

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
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            logger.error(LoggingMessagesHanldler.ERROR_ENCRYPT_ALGORITHM);
            throw new RuntimeException(e);
        }
        return generatedPassword;
    }
}
