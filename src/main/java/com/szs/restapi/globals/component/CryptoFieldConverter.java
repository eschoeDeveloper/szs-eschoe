package com.szs.restapi.globals.component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class CryptoFieldConverter implements AttributeConverter<String, String> {

    private final CryptoComponent cryptoComponent;

    @Override
    public String convertToDatabaseColumn(String attribute) {

        String encryptText = "";

        if(attribute == null || attribute.isEmpty()) return encryptText;

        try {
            encryptText = cryptoComponent.encryptAES256(attribute);
        } catch (Exception e) {
            e.printStackTrace();
            encryptText = "";
        }

        return encryptText;

    }

    @Override
    public String convertToEntityAttribute(String dbData) {

        String decryptText = "";

        if(dbData == null || dbData.isEmpty()) return decryptText;

        try {
            decryptText = cryptoComponent.decryptAES256(dbData);
        } catch (Exception e) {
            e.printStackTrace();
            decryptText = "";
        }

        return decryptText;

    }

}