package com.epam.converter;

import com.epam.model.User;
import com.epam.util.GeneratePDFUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

public class PdfMessageConverter extends AbstractHttpMessageConverter<User>{
    public PdfMessageConverter() {
        super(new MediaType("application","pdf"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    protected User readInternal(Class<? extends User> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        ByteArrayInputStream usersPDF = GeneratePDFUtils.getUsersPDF(Arrays.asList(user));
        httpOutputMessage.getBody().write(usersPDF.read());
    }
}
