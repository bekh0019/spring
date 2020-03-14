package com.epam.util;

import com.epam.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class MapperUtils {

    public static List mapJSONToEntity(MultipartFile file, Object entity){
        ObjectMapper mapper = new ObjectMapper();
        List objects = new ArrayList<>();
        try {
            objects  =	mapper.readValue(file.getBytes(), mapper.getTypeFactory().constructCollectionType(List.class, entity.getClass()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
