package com.epam.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapperUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperUtils.class);

    private MapperUtils() {
    }

    public static List mapJSONToEntity(MultipartFile file, Object entity){
        ObjectMapper mapper = new ObjectMapper();
        List objects = new ArrayList<>();
        try {
            objects  =	mapper.readValue(file.getBytes(), mapper.getTypeFactory().constructCollectionType(List.class, entity.getClass()));
        } catch (IOException e) {
           LOGGER.error(e.getMessage());
        }
        return objects;
    }
}
