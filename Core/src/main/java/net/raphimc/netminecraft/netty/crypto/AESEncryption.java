package net.raphimc.netminecraft.netty.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.GeneralSecurityException;
import java.security.Key;

/**
 * An encryption implementation using "AES/CFB8/NoPadding" encryption.
 */
public class AESEncryption {

    private final Cipher inCipher;
    private final Cipher outCipher;

    /**
     * Creates a new AESEncryption instance.
     *
     * @param key Key to use when encrypting/decrypting data.
     * @throws GeneralSecurityException If a security error occurs.
     */
    public AESEncryption(final Key key) throws GeneralSecurityException {
        this.inCipher = Cipher.getInstance("AES/CFB8/NoPadding");
        this.inCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(key.getEncoded()));
        this.outCipher = Cipher.getInstance("AES/CFB8/NoPadding");
        this.outCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(key.getEncoded()));
    }

    public int getDecryptOutputSize(final int length) {
        return this.inCipher.getOutputSize(length);
    }

    public int getEncryptOutputSize(final int length) {
        return this.outCipher.getOutputSize(length);
    }

    public int decrypt(final byte[] input, final int inputOffset, final int inputLength, final byte[] output, final int outputOffset) throws Exception {
        return this.inCipher.update(input, inputOffset, inputLength, output, outputOffset);
    }

    public int encrypt(final byte[] input, final int inputOffset, final int inputLength, final byte[] output, final int outputOffset) throws Exception {
        return this.outCipher.update(input, inputOffset, inputLength, output, outputOffset);
    }

}
