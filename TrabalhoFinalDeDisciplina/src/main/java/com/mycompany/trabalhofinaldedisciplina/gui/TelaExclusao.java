package com.mycompany.trabalhofinaldedisciplina.gui;

import com.mycompany.trabalhofinaldedisciplina.ClienteDAO;
import com.mycompany.trabalhofinaldedisciplina.entidades.Cliente;

import javax.swing.*;
import java.awt.*;

public class TelaExclusao {

    public static void exibir() {
        // Pede ao usuário o ID do cliente que será excluído
        String idStr = JOptionPane.showInputDialog(null, "Informe o ID do Cliente a Excluir:", "Excluir Cliente", JOptionPane.QUESTION_MESSAGE);
        
        // Se cancelar ou deixar em branco, encerra a função
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            Integer id = Integer.parseInt(idStr.trim());

            ClienteDAO dao = new ClienteDAO();
            Cliente cliente = dao.buscarPorId(id); // Tenta buscar o cliente pelo ID

            // Se o cliente não for encontrado, avisa e finaliza
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
                dao.fechar();
                return;
            }

            // Mensagem de confirmação bonitinha com fonte serifada
            JTextArea mensagem = new JTextArea(
                "Tem certeza que deseja excluir o cliente:\n\n" +
                "ID: " + cliente.getId() + "\n" +
                "Nome: " + cliente.getNome() + "\n\n" +
                "Essa ação não pode ser desfeita."
            );

            // Estilo do texto para parecer mais profissional e claro
            mensagem.setFont(new Font("Serif", Font.PLAIN, 14));
            mensagem.setEditable(false);
            mensagem.setOpaque(false);     // Fundo transparente
            mensagem.setFocusable(false);  // Não pode ser selecionado
            mensagem.setLineWrap(true);    // Quebra automática
            mensagem.setWrapStyleWord(true); // Quebra por palavra

            // Caixa de diálogo para confirmação
            int confirm = JOptionPane.showConfirmDialog(null, mensagem, "Confirmação de Exclusão",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            // Se o usuário confirmar, exclui o cliente
            if (confirm == JOptionPane.YES_OPTION) {
                dao.deletar(cliente);
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }

            dao.fechar(); // Fecha o DAO no fim, sempre
        } catch (NumberFormatException e) {
            // Caso o usuário digite um ID inválido (não numérico, por exemplo)
            JOptionPane.showMessageDialog(null, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
