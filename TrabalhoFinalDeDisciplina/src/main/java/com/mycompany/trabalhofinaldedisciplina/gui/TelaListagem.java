package com.mycompany.trabalhofinaldedisciplina.gui;

import com.mycompany.trabalhofinaldedisciplina.ClienteDAO;
import com.mycompany.trabalhofinaldedisciplina.entidades.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaListagem {

    public static void exibir() {
        // Primeiro, a gente pega todos os clientes do banco
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listar();
        dao.fechar(); // sempre fecha o DAO depois de usar

        // Painel principal que vai segurar tudo que vai aparecer
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(new EmptyBorder(15, 15, 15, 15)); // espaço nas bordas

        // Criando o título da tela com uma fonte mais bonitinha
        JLabel titulo = new JLabel("Lista de Clientes");
        titulo.setFont(new Font("Serif", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(titulo, BorderLayout.NORTH); // coloca o título no topo do painel

        // Aqui a gente vai montar o texto da listagem
        StringBuilder sb = new StringBuilder();
        // Cabeçalho com os nomes das colunas
        sb.append(String.format("%-5s %-20s %-30s %-15s%n", "ID", "Nome", "Email", "Telefone"));
        sb.append("---------------------------------------------------------------\n");

        // Preenche a lista com os dados dos clientes
        for (Cliente c : clientes) {
            sb.append(String.format("%-5d %-20s %-30s %-15s%n",
                    c.getId(),
                    c.getNome(),
                    c.getEmail(),
                    c.getTelefone()));
        }

        // Caixa de texto que vai mostrar a listagem
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false); // só leitura
        area.setFont(new Font("Monospaced", Font.PLAIN, 14)); // monoespaçada pra alinhar direitinho
        area.setBackground(new Color(245, 245, 245)); // cor de fundo mais clara

        // Rola a lista se ela for muito grande
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(500, 300));
        painel.add(scroll, BorderLayout.CENTER); // adiciona no meio do painel

        // Mostra tudo num JOptionPane
        JOptionPane.showMessageDialog(null, painel, "Clientes", JOptionPane.INFORMATION_MESSAGE);
    }
}
