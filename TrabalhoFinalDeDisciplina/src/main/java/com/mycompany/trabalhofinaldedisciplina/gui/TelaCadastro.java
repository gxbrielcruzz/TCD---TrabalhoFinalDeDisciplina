package com.mycompany.trabalhofinaldedisciplina.gui;

import com.mycompany.trabalhofinaldedisciplina.ClienteDAO;
import com.mycompany.trabalhofinaldedisciplina.entidades.Cliente;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro {

    public static void exibir() {
        // Aqui a gente cria os campos onde o usuário vai digitar os dados do cliente
        JTextField nomeField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField telefoneField = new JTextField(20);

        // Criamos um painel com um layout que permite posicionar os campos certinho
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Define espaços entre os componentes pra não ficar tudo grudado
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Linha 0 - Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        painel.add(nomeField, gbc);

        // Linha 1 - Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        painel.add(emailField, gbc);

        // Linha 2 - Telefone
        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        painel.add(telefoneField, gbc);

        // Ajusta a fonte dos textos (labels) pra ficar mais bonitinho
        Font fonteLabel = new Font("Serif", Font.PLAIN, 14);
        for (Component comp : painel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(fonteLabel);
            }
        }

        // Mostra a caixinha de diálogo com os campos pro usuário preencher
        int option = JOptionPane.showConfirmDialog(null, painel, "Cadastrar Cliente",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Se o usuário clicar em "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Cria um novo cliente com os dados que foram digitados
            Cliente cliente = new Cliente();
            cliente.setNome(nomeField.getText().trim());
            cliente.setEmail(emailField.getText().trim());
            cliente.setTelefone(telefoneField.getText().trim());

            // Salva no banco usando o DAO (Data Acess Object)
            ClienteDAO dao = new ClienteDAO();
            dao.salvar(cliente);
            dao.fechar();

            // Mostra mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
