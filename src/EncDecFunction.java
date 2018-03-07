import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncDecFunction {


    //KeyGen Method
    public static void keyGen() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(256,secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytes = secretKey.getEncoded();
        String ec = DatatypeConverter.printHexBinary(bytes);
        FileReadWrite.WriteFile("key.txt",ec);
    }

    //to get the key from text file
    public static SecretKeySpec getSecretKey(){
        String encodedKey = FileReadWrite.ReadFile("key.txt");
        byte[] bytes = DatatypeConverter.parseHexBinary(encodedKey);
        SecretKeySpec secretKey = new SecretKeySpec(bytes, "AES");
        return secretKey;
    }

    //CBC Encryption
    public static byte[] encryptCBC(String plainText) throws Exception {
        byte[] inputText = plainText.getBytes();

        // Generating IV.
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        //Writing IV to file
        FileReadWrite.WriteFile("iv.txt",DatatypeConverter.printHexBinary(iv));

        //Fetching the key
        SecretKeySpec secretKey = getSecretKey();

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(inputText);

        // Combine IV and encrypted part.
        byte[] cipherWithIVText = new byte[ivSize + encrypted.length];
        System.arraycopy(iv, 0, cipherWithIVText, 0, ivSize);
        System.arraycopy(encrypted, 0, cipherWithIVText, ivSize, encrypted.length);

        return cipherWithIVText;
    }

    //CBC Decryption
    public static String decryptCBC(byte[] cipherWithIVText) throws Exception {
        int ivSize = 16;

        // Extract IV.
        byte[] iv = new byte[ivSize];
        System.arraycopy(cipherWithIVText, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extract Cipher.
        int cipherSize = cipherWithIVText.length - ivSize;
        System.out.println(cipherSize);
        byte[] cipherBytes = new byte[cipherSize];
        System.arraycopy(cipherWithIVText, ivSize, cipherBytes, 0, cipherSize);

        //Fetching the key
        SecretKeySpec secretKey = getSecretKey();

        // Decrypt.
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(cipherBytes);

        return new String(decrypted);
    }

    //ECB Encryption
    public static byte[] encryptECB(String plainText) throws Exception {

        //Fetching the key
        SecretKeySpec secretKey = getSecretKey();

        //encrypt
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        return encrypted;
    }

    public static String decryptECB(byte[] cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //Fetching the key
        SecretKeySpec secretKey = getSecretKey();

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(cipherText));
    }

}
