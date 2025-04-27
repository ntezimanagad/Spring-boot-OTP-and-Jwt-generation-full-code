package com.books.book.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.book.model.Otp;
import com.books.book.repository.OtpRepository;

@Service
public class OtpService {
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private EmailService emailService;
    private static final int EXIPIRED_IN = 5;
    public void generateAndSendOtp(String username, String email, String purpose){
        String otpCode = String.format("%06d", new Random().nextInt(999999));
        Instant now = Instant.now();
        Instant expiry = now.plus(EXIPIRED_IN, ChronoUnit.MINUTES);

        Otp otp = new Otp(username, otpCode, now, expiry, purpose);
        otpRepository.save(otp);

        emailService.sendEmail(email, "Your OTP", "Your Otp is" + otpCode);
    }

    public boolean validateOTP(String username, String otpCode, String purpose){
        Optional<Otp> optionalOtp = otpRepository.findByUsernameAndPurpose(username, purpose);
        if (optionalOtp.isEmpty()) {
            return false;
        }
        Otp otp = optionalOtp.get();
        if (otp.getExpiredAt().isBefore(Instant.now())) {
            return false;
        }
        return otp.getOtpCode().equals(otpCode);
    }

    public void deleteOtp(String username, String purpose){
        otpRepository.findByUsernameAndPurpose(username, purpose)
            .ifPresent(otpRepository::delete);
    }
}
