package com.kh.rupp_dev.boukryuniversity.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kh.rupp_dev.boukryuniversity.service.QrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class QrServiceImpl implements QrService {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean vlaidationToken(String token) {
        return token != null && !token.isBlank();
    }

    @Override
    public byte[] generateQrCode(String token) {
        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bitMatrix = qrCodeWriter.encode(token, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);

            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // O(n^2) jam kea pel kroy vea ort torn best practice te just make it work first.
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x,y,bitMatrix.get(x,y) ? 0x000000 : 0xFFFFFF);
                }
            }


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ImageIO.write(image, "PNG", outputStream);

            return outputStream.toByteArray();

        } catch (WriterException | IOException e) {
            log.error("Failed to generate QR Code", e);
            throw new RuntimeException("Unable to generate QR Code", e);
        }
    }

}
