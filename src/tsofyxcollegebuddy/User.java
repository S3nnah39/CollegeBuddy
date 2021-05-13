/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsofyxcollegebuddy;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author TashaOgoti
 * 
 * 
 * 
 * Source: UserID and genUserId is from the professor's Library UML file
 */
public class User implements Serializable{
    private String firstName;
    private String lastName;
    private String password;
    private String hometown;
    private Double yourIncome;
    private HashMap<String, Double> studentExpenditure;
    private ArrayList< String> graphStudentIncome;
    
    private static SecretKeySpec secret;
    private static byte[] key;
    private static String secretKey = "lindaMlango";
    
    public User(){
        firstName ="";
        lastName = "";
        password = "";
        hometown = "Columbia, Missouri";
        yourIncome = 0.0;
        studentExpenditure = new HashMap<>();
        graphStudentIncome = new ArrayList<>();
    }
    
    public User(String firstName, String lastName, String password){
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = encrypt(password);
    }

    
    //Source code: https://www.kodejava.org/how-do-i-create-an-encrypted-string-for-password/

    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secret = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (Exception e){
             e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt) 
    {
        try
        {
            setKey(secretKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    private static String decrypt(String strToDecrypt) 
    {
        try
        {
            setKey(secretKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param match
     * @return the true/false
     */
    public boolean getPassword(String match) {
        String passcode = decrypt(password);
        return match.equals(passcode);
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = encrypt(password);
    }
    
    public Double getTotalExpenditure(){
        Double total = 0.0;
        for(Double i : getStudentExpenditure().values())
        {
            total = total + i;
        }
        return total;
    }

    /**
     * @return the studentExpenditure
     */
    public HashMap<String, Double> getStudentExpenditure() {
        return studentExpenditure;
    }

    /**
     * @return the hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * @param hometown the hometown to set
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * @return the yourIncome
     */
    public Double getYourIncome() {
        return yourIncome;
    }

    /**
     * @param yourIncome the yourIncome to set
     */
    public void setYourIncome(Double yourIncome) {
        this.yourIncome = yourIncome;
    }

    /**
     * @return the graphStudentIncome
     */
    public ArrayList<String> getGraphStudentIncome() {
        return graphStudentIncome;
    }
    


   
}
