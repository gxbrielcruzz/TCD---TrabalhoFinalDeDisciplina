package com.mycompany.trabalhofinaldedisciplina;

import com.mycompany.trabalhofinaldedisciplina.entidades.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClienteDAO {

    private SessionFactory sessionFactory;

    public ClienteDAO() {
        // Aqui a gente configura o Hibernate e cria a "fábrica de sessões"
        sessionFactory = new Configuration()
                .configure() // Pega o hibernate.cfg.xml automaticamente
                .buildSessionFactory();
    }

    public void salvar(Cliente cliente) {
        // Salva um novo cliente no banco
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession(); // Abre uma conexão com o banco
            transaction = session.beginTransaction(); // Inicia a transação

            session.save(cliente); // Salva o cliente

            transaction.commit(); // Confirma no banco
            System.out.println("Cliente salvo com sucesso!");
        } catch (Exception e) {
            // Se der erro, desfaz a transação
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    System.err.println("Erro ao fazer rollback: " + ex.getMessage());
                }
            }
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        } finally {
            // Fecha a conexão com o banco
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Cliente> listar() {
        // Lista todos os clientes do banco
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Cliente", Cliente.class).list(); // HQL pra buscar tudo da tabela Cliente
        } catch (Exception e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            return null;
        }
    }

    public void atualizar(Cliente cliente) {
        // Atualiza os dados de um cliente existente
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(cliente); // Atualiza o cliente

            transaction.commit();
            System.out.println("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    System.err.println("Erro ao fazer rollback: " + ex.getMessage());
                }
            }
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deletar(Cliente cliente) {
        // Remove um cliente do banco
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.delete(cliente); // Deleta o cliente

            transaction.commit();
            System.out.println("Cliente deletado com sucesso!");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    System.err.println("Erro ao fazer rollback: " + ex.getMessage());
                }
            }
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Cliente buscarPorId(Integer id) {
        // Busca um cliente pelo ID
        try (Session session = sessionFactory.openSession()) {
            return session.get(Cliente.class, id); // Retorna o cliente com o ID informado
        } catch (Exception e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

    public void fechar() {
        // Fecha a SessionFactory quando não precisar mais
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
