package com.epam.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final String TEXT_PATTERN =
            "([^\\s]+(\\.(?i)(txt))$)";

    public static boolean isFileValid(MultipartFile file) {
        Pattern pattern = Pattern.compile(TEXT_PATTERN);
        Matcher matcher = pattern.matcher(file.getOriginalFilename());
        return !file.isEmpty() && matcher.matches();
    }
}
