package com.recode.hanami.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.recode.hanami.dto.DadosArquivoDTO;
import com.recode.hanami.dto.ImportacaoResponseDTO;
import com.recode.hanami.exceptions.ArquivoInvalidoException;
import com.recode.hanami.exceptions.DadosInvalidosException;
import com.recode.hanami.service.CsvService;
import com.recode.hanami.service.ProcessamentoVendasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/csv")
public class CsvController {
    private static final Logger logger = LoggerFactory.getLogger(CsvController.class);

    private final CsvService csvService;
    private final ProcessamentoVendasService processamentoVendasService;

    public CsvController(CsvService csvService, ProcessamentoVendasService processamentoVendasService) {
        this.csvService = csvService;
        this.processamentoVendasService = processamentoVendasService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCsv(@RequestParam(value = "file", required = false) MultipartFile file) {
        logger.info("Iniciando processamento de upload de arquivo");


        if (file == null || file.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "status", "erro",
                            "mensagem", "Nenhum arquivo foi enviado ou o arquivo está vazio."
                    ));
        }

        try {
            logger.info("Iniciando conversão do arquivo CSV para JSON. Nome do arquivo: {}", file.getOriginalFilename());
            List<DadosArquivoDTO> listaProcessada = csvService.conversorCsvParaJson(file);
            logger.debug("Arquivo convertido com sucesso. Número de registros: {}", listaProcessada.size());

            logger.info("Iniciando salvamento dos dados do arquivo");
            processamentoVendasService.salvarDadosDoArquivo(listaProcessada);
            logger.info("Dados salvos com sucesso");



            ImportacaoResponseDTO resposta = new ImportacaoResponseDTO("sucesso", listaProcessada.size());
            logger.info("Processo de upload finalizado com sucesso. Registros processados: {}", listaProcessada.size());

            return ResponseEntity
                    .ok()
                    .body(resposta);

        } catch (ArquivoInvalidoException | DadosInvalidosException e) {
            logger.error("Erro de dados inválidos: {}", e.getMessage());

            return ResponseEntity
                    .unprocessableContent()
                    .body(Map.of(
                            "status", "erro_processamento",
                            "mensagem", e.getMessage()
                    ));
        } catch (Exception e) {
            logger.error("Erro inesperado durante o processamento do arquivo", e);
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of(
                            "status", "erro_interno",
                            "mensagem", "Ocorreu um erro inesperado: " + e.getMessage()
                    ));
        }
    }
}