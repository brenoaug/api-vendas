package com.recode.hanami.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.recode.hanami.util.TratamentoDadosUtil;

import java.time.LocalDate;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosArquivoDTO {
    // Vendas
    @JsonProperty("id_transacao")
    private String idTransacao;
    @JsonProperty("data_venda")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataVenda;
    @JsonProperty("valor_final")
    private Double valorFinal;
    @JsonProperty("subtotal")
    private Double subtotal;
    @JsonProperty("desconto_percent")
    private Double descontoPercent;
    @JsonProperty("canal_venda")
    private String canalVenda;
    @JsonProperty("forma_pagamento")
    private String formaPagamento;
    //Cliente
    @JsonProperty("cliente_id")
    private String clienteId;
    @JsonProperty("nome_cliente")
    private String nomeCliente;
    @JsonProperty("idade_cliente")
    private Integer idadeCliente;
    @JsonProperty("genero_cliente")
    private Character generoCliente;
    @JsonProperty("cidade_cliente")
    private String cidadeCliente;
    @JsonProperty("estado_cliente")
    private String estadoCliente;
    @JsonProperty("renda_estimada")
    private Double rendaEstimada;
    //Produto
    @JsonProperty("produto_id")
    private String produtoId;
    @JsonProperty("nome_produto")
    private String nomeProduto;
    @JsonProperty("categoria")
    private String categoria;
    @JsonProperty("marca")
    private String marca;
    @JsonProperty("preco_unitario")
    private Double precoUnitario;
    @JsonProperty("quantidade")
    private Integer quantidade;
    @JsonProperty("margem_lucro")
    private Double margemLucro;

    //Logistica e Operacao
    @JsonProperty("regiao")
    private String regiao;
    @JsonProperty("status_entrega")
    private String statusEntrega;
    @JsonProperty("tempo_entrega_dias")
    private Integer tempoEntregaDias;
    @JsonProperty("vendedor_id")
    private String vendedorId;

    public String getIdTransacao() {
        return idTransacao;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getDescontoPercent() {
        return descontoPercent;
    }

    public String getCanalVenda() {
        return canalVenda;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Integer getIdadeCliente() {
        return idadeCliente;
    }

    public Character getGeneroCliente() {
        return generoCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public Double getRendaEstimada() {
        return rendaEstimada;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getMarca() {
        return marca;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public String getRegiao() {
        return regiao;
    }

    public String getStatusEntrega() {
        return statusEntrega;
    }

    public Integer getTempoEntregaDias() {
        return tempoEntregaDias;
    }

    public String getVendedorId() {
        return vendedorId;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = TratamentoDadosUtil.tratarData(dataVenda);
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void setDescontoPercent(Double descontoPercent) {
        this.descontoPercent = descontoPercent;
    }

    public void setCanalVenda(String canalVenda) {
        this.canalVenda = canalVenda;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setIdadeCliente(Integer idadeCliente) {
        this.idadeCliente = idadeCliente;
    }

    public void setGeneroCliente(Character generoCliente) {
        this.generoCliente = generoCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public void setRendaEstimada(Double rendaEstimada) {
        this.rendaEstimada = rendaEstimada;
    }

    public void setProdutoId(String produtoId) {
        this.produtoId = produtoId;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public void setStatusEntrega(String statusEntrega) {
        this.statusEntrega = statusEntrega;
    }

    public void setTempoEntregaDias(Integer tempoEntregaDias) {
        this.tempoEntregaDias = tempoEntregaDias;
    }

    public void setVendedorId(String vendedorId) {
        this.vendedorId = vendedorId;
    }
}
