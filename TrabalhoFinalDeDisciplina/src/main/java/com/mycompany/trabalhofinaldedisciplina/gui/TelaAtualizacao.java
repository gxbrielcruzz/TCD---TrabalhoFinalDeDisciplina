package com.mycompany.trabalhofinaldedisciplina.gui;

import com.mycompany.trabalhofinaldedisciplina.ClienteDAO;
import com.mycompany.trabalhofinaldedisciplina.entidades.Cliente;

import javax.swing.*;
import java.awt.*;

public class TelaAtualizacao {

    public static void exibir() {
        // Primeiro, pedimos o ID do cliente que será atualizado
        String idStr = JOptionPane.showInputDialog(null, "ID do Cliente a Atualizar:", "Atualizar Cliente", JOptionPane.QUESTION_MESSAGE);
        
        // Se o usuário cancelar ou deixar vazio, a função já termina aqui
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            Integer id = Integer.parseInt(idStr.trim());

            ClienteDAO dao = new ClienteDAO();
            Cliente cliente = dao.buscarPorId(id); // busca o cliente pelo ID informado

            // Se não encontrar o cliente, avisa e encerra
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
                dao.fechar();
                return;
            }

            // Cria os campos pré-preenchidos com os dados do cliente
            JTextField nomeField = new JTextField(cliente.getNome(), 20);
            JTextField emailField = new JTextField(cliente.getEmail(), 20);
            JTextField telefoneField = new JTextField(cliente.getTelefone(), 20);

            // Painel com layout organizado (linha por linha)
            JPanel painel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 10, 8, 10); // espaçamento
            gbc.anchor = GridBagConstraints.WEST; //Homenagem a West, nosso professor zero bala; Posicionamento da tabela

            // Linha 1 - Nome
            gbc.gridx = 0; gbc.gridy = 0;
            painel.add(new JLabel("Nome:"), gbc);
            gbc.gridx = 1;
            painel.add(nomeField, gbc);

            // Linha 2 - Email
            gbc.gridx = 0; gbc.gridy = 1;
            painel.add(new JLabel("Email:"), gbc);
            gbc.gridx = 1;
            painel.add(emailField, gbc);

            // Linha 3 - Telefone
            gbc.gridx = 0; gbc.gridy = 2;
            painel.add(new JLabel("Telefone:"), gbc);
            gbc.gridx = 1;
            painel.add(telefoneField, gbc);

            // Deixa os rótulos com uma fonte clássica
            Font fonteLabel = new Font("Serif", Font.PLAIN, 14);
            for (Component comp : painel.getComponents()) {
                if (comp instanceof JLabel) {
                    comp.setFont(fonteLabel);
                }
            }

            // Mostra a janelinha com os campos para edição
            int option = JOptionPane.showConfirmDialog(null, painel, "Atualizar Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Se o usuário clicar em OK, atualiza os dados do cliente
            if (option == JOptionPane.OK_OPTION) {
                cliente.setNome(nomeField.getText().trim());
                cliente.setEmail(emailField.getText().trim());
                cliente.setTelefone(telefoneField.getText().trim());

                dao.atualizar(cliente); // salva no banco

                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }

            dao.fechar(); // sempre fecha o DAO

        } catch (NumberFormatException e) {
            // Caso o ID não seja número válido
            JOptionPane.showMessageDialog(null, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
