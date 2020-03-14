package com.epam.controller;

import com.epam.constant.Constants;
import com.epam.model.Phone;
import com.epam.model.PhoneCompany;
import com.epam.model.User;
import com.epam.service.PhoneCompanyService;
import com.epam.service.PhoneService;
import com.epam.service.UserService;
import com.epam.util.MapperUtils;
import com.epam.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadFileController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneCompanyService phoneCompanyService;

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (!ValidationUtils.isFileValid(file)) {
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, Constants.ERROR_FORMAT_FILE_MESSAGE);
            return Constants.REDIRECT_TO_ENTRY_PAGE;
        }
        saveEntitiesToDB(file);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE,
                Constants.SUCCESS_UPLOAD_MESSAGE + file.getOriginalFilename());

        return Constants.REDIRECT_TO_ENTRY_PAGE;
    }

    private void saveEntitiesToDB(MultipartFile file) {
        if (file.getOriginalFilename().startsWith(Constants.USER)) {
            userService.saveUsers(MapperUtils.mapJSONToEntity(file, new User()));
        } else if (file.getOriginalFilename().startsWith(Constants.PHONE)) {
            phoneService.savePhones(MapperUtils.mapJSONToEntity(file, new Phone()));
        } else if (file.getOriginalFilename().startsWith(Constants.COMPANY)) {
            phoneCompanyService.savePhoneCompanies(MapperUtils.mapJSONToEntity(file, new PhoneCompany()));
        }
    }
}
