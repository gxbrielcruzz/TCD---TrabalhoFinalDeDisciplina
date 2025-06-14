package com.mycompany.trabalhofinaldedisciplina;

// Importa as telas que foram criadas pra cada função do sistema
import com.mycompany.trabalhofinaldedisciplina.gui.TelaCadastro;
import com.mycompany.trabalhofinaldedisciplina.gui.TelaListagem;
import com.mycompany.trabalhofinaldedisciplina.gui.TelaAtualizacao;
import com.mycompany.trabalhofinaldedisciplina.gui.TelaExclusao;

import javax.swing.*;

public class TrabalhoFinalDeDisciplina {

    public static void main(String[] args) {
        // Aqui a gente define as opções que vão aparecer pro usuário no menu
        String[] opcoes = {"Cadastrar", "Listar", "Atualizar", "Excluir", "Sair"};

        // Esse laço vai ficar rodando até o usuário escolher "Sair"
        while (true) {
            // Mostra uma caixinha (JOptionPane) perguntando o que o usuário quer fazer
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma operação:",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0] // opção selecionada por padrão
            );

            // Dependendo da escolha, ele chama a tela correspondente
            switch (escolha) {
                case 0 ->
                    TelaCadastro.exibir();    // Abre a tela de cadastro de cliente
                case 1 ->
                    TelaListagem.exibir();    // Abre a tela que mostra todos os clientes
                case 2 ->
                    TelaAtualizacao.exibir(); // Abre a tela pra atualizar os dados de um cliente
                case 3 ->
                    TelaExclusao.exibir();    // Abre a tela pra excluir um cliente
                default -> {
                    System.exit(0); // Fecha o programa se escolher "Sair" ou fechar a janela
                }
            }
        }
    }
}
