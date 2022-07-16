package awaken.awakenauthservice.utils;

import org.springframework.security.crypto.codec.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import static org.apache.tomcat.util.buf.HexUtils.fromHexString;

public class PasswordHasher {

  private static final int ITERATIONS = 10000;
  private static final int SALT_SIZE = 30;
  private static final int HASH_SIZE = 30;
  private static final String SEPARATOR = ":";
  private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA512";

  /**
   * Creates a hash from a String ( password );
   *
   * @param password String to hash
   * @return hashed String
   */
  public static String hashString(String password) {
    byte[] salt = getRandomBytes(SALT_SIZE);
    byte[] hash = computeHash(password.toCharArray(), salt, ITERATIONS, HASH_SIZE);
    return ITERATIONS + SEPARATOR + Hex.encode(salt) + SEPARATOR + Hex.encode(hash);
  }

  public static boolean match(String password, String expectedPasswordHash) {
    String[] hashComponents = expectedPasswordHash.split(SEPARATOR);
    int iterations = Integer.parseInt(hashComponents[0]);
    byte[] salt = fromHexString(hashComponents[1]);
    byte[] expectedHashResult = fromHexString(hashComponents[2]);
    byte[] passwordHash = computeHash(password.toCharArray(),
                                      salt,
                                      iterations,
                                      expectedHashResult.length);
    return slowEquals(passwordHash, expectedHashResult);
  }

  /**
   * Compares two byte arrays in length-constant time. This comparison method
   * is used so that password hashes cannot be extracted from an on-line
   * system using a timing attack and then attacked off-line.
   *
   * @param a the first byte array
   * @param b the second byte array
   * @return true if both byte arrays are the same, false if not
   */
  private static boolean slowEquals(byte[] a, byte[] b) {
    int diff = a.length ^ b.length;
    for (int i = 0; i < a.length && i < b.length; i++)
      diff |= a[i] ^ b[i];
    return diff == 0;
  }

  /**
   * Creates a hash from char bytes
   *
   * @param password   the password to hash.
   * @param salt       the salt
   * @param iterations strength of hash algorithm
   * @param bytes      the length of the hash to compute in bytes
   * @return password hash as bytes
   */
  private static byte[] computeHash(char[] password, byte[] salt, int iterations, int bytes) {
    try {
      PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
      SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
      return skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Generate buffer of random bytes with the given size;
   *
   * @param biteSize desired size for the created byte
   * @return array of random bytes;
   */
  private static byte[] getRandomBytes(int biteSize) {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[biteSize];
    random.nextBytes(salt);
    return salt;
  }
}