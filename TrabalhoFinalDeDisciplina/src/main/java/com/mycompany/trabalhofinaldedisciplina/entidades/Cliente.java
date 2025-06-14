package com.mycompany.trabalhofinaldedisciplina.entidades;

// Essas anotações vêm da JPA (usada junto com Hibernate pra mapear a classe pro banco)
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity // Diz que essa classe representa uma tabela no banco de dados
public class Cliente {

    @Id // Esse é o campo que vai ser a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco vai gerar esse ID automaticamente (auto incremento)
    private Integer id;

    // Atributos básicos do cliente
    private String nome;
    private String email;
    private String telefone;

    // Métodos getters e setters (usados pra acessar e alterar os valores dos atributos)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
