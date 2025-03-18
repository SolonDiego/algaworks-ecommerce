package br.com.solondiego.algaworksecommerce.iniciandocomjpa;

import br.com.solondiego.algaworksecommerce.EntityManagerTest;
import br.com.solondiego.algaworksecommerce.model.Cliente;
import br.com.solondiego.algaworksecommerce.model.SexoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperacaoComClientes extends EntityManagerTest {

    @Test
    public void buscarCliente() {

        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assertions.assertEquals("João Lucas Gomes Moreira", cliente.getNome());

    }

    @Test
    public void atualizarCliente() {

        Cliente cliente = entityManager.find(Cliente.class, 1);

        cliente.setNome("João Lucas Gomes Moreira");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

    }

    @Test
    public void inserirCliente() {

        Cliente cliente = new Cliente();

        cliente.setNome("Maria Alícia Gomes Moreira");
        cliente.setSexo(SexoCliente.FEMININO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);

    }
}
