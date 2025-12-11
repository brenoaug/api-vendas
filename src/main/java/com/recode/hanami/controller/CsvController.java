package com.recode.hanami.controller;

import com.recode.hanami.service.CsvService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    private final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/upload")
    public List<Map<String, String>> uploadCsv(@RequestParam("file") MultipartFile file) throws IOException {
        return csvService.convertCsvToJson(file.getInputStream());
    }
}