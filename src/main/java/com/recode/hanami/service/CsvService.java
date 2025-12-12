package com.recode.hanami.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.recode.hanami.dto.DadosArquivoDTO;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class CsvService {
    public List<DadosArquivoDTO> conversorCsvParaJson(InputStream arquivo) {
        try {
            CsvMapper csvMapper = new CsvMapper();

            csvMapper.registerModule(new JavaTimeModule());

            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

            MappingIterator<DadosArquivoDTO> it = csvMapper
                    .readerFor(DadosArquivoDTO.class)
                    .with(csvSchema)
                    .readValues(arquivo);

            return it.readAll();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao processar o arquivo CSV: " + e.getMessage());
        }

    }
}