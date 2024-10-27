package com.user.app.Security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import com.user.app.Models.Common.userDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


@Component
class JwtUtils (
) {

    companion object{
        private const val ISSUER     ="issuer"
        private const val USER_ID    ="userId"
        private const val CHANNEL_ID ="channelId"
        private const val SESSION_ID ="uuid"
        private const val DEFAULT_KEY="iELVqXtWsYw_7xxDSEwyscPPQraaRUBB2ilqwfyshc4"
        private const val AES_KEY    ="1234567890123456"
        private const val SECURE_JWT =true
    }


    fun generateJwtToken(userDetails: userDetails): String {
        val secret = DEFAULT_KEY
        println("KEY: $secret")
        val algorithm = Algorithm.HMAC256(secret)
        val now = System.currentTimeMillis()
        return encrypt(JWT.create()
            .withIssuer(ISSUER)
            .withClaim(USER_ID, userDetails.userId)
            .withClaim(CHANNEL_ID, userDetails.channelId)
            .withClaim(SESSION_ID, userDetails.uuid.toString())
            .withIssuedAt(Date(now))
            .withExpiresAt(Date(now + 1000 * 60 * 600)) // Token valid for 1 hour
            .sign(algorithm))
    }



    fun validateJwtToken(token: String): Boolean {
        return try {
            val secret = DEFAULT_KEY
            val algorithm = Algorithm.HMAC256(secret)
            val verifier: JWTVerifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
            verifier.verify(decrypt(token))
            !isTokenExpired(token,secret)
        } catch (exception: JWTVerificationException) {
            false
        }
    }

    fun isTokenExpired(token: String, secret: String): Boolean {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            val verifier: JWTVerifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
            val decodedJWT: DecodedJWT = verifier.verify(decrypt(token))
            decodedJWT.expiresAt.before(Date())
        } catch (exception: JWTVerificationException) {
            true
        }
    }

    fun extractUserId(token: String): String? {
        return extractClaim(token, USER_ID)
    }

    fun extractUuid(token: String): UUID {
        return UUID.fromString(extractClaim(token, SESSION_ID) as String)
    }

    fun extractChannelId(token: String): String? {
        return extractClaim(token,  CHANNEL_ID)
    }

    private fun extractClaim(token: String, claim: String): String? {
        return try {
            val secret =  DEFAULT_KEY
            val algorithm = Algorithm.HMAC256(secret)
            val verifier: JWTVerifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
            val decodedJWT: DecodedJWT = verifier.verify(decrypt(token))
            decodedJWT.getClaim(claim).asString()
        } catch (exception: JWTVerificationException) {
            null
        }
    }


    // Encryption function
    fun encrypt(text: String): String {
        return if(SECURE_JWT){
            val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            val key: Key = SecretKeySpec(AES_KEY.toByteArray(Charsets.UTF_8), "AES")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val encryptedBytes: ByteArray = cipher.doFinal(text.toByteArray(Charsets.UTF_8))
            Base64.getEncoder().encodeToString(encryptedBytes)
        }else{
            text
        }

    }

    // Decryption function
    fun decrypt(text: String): String {
        return if(SECURE_JWT){
            val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            val key: Key = SecretKeySpec(AES_KEY.toByteArray(Charsets.UTF_8), "AES")
            cipher.init(Cipher.DECRYPT_MODE, key)
            val encryptedBytes: ByteArray = Base64.getDecoder().decode(text)
            val decryptedBytes: ByteArray = cipher.doFinal(encryptedBytes)
            String(decryptedBytes, Charsets.UTF_8)
        }else{
            text
        }
    }

}