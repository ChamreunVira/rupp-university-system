package com.kh.rupp_dev.boukryuniversity.service;

public interface QrService {

    String generateToken();

    boolean vlaidationToken(String token);

    byte[] generateQrCode(String token);

}
