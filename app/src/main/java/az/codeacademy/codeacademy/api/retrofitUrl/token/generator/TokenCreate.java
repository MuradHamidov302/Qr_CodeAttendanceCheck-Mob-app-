package az.codeacademy.codeacademy.api.retrofitUrl.token.generator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Murad on 04/01/2018.
 */

public class TokenCreate {

    private String spesificKey = "5sda01bg350cx1g2b5gv1x32fs4d5f21gfsd1f25as6d1f56as1dgv651f23v15df31vb65df4153dsad1v54sv18v54zs35b4135zd4b15";

    public String getSpesificKey() {
        return spesificKey;
    }

    public static String get_SHA_512_SecurePassword(String passwordToHash){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }


}
