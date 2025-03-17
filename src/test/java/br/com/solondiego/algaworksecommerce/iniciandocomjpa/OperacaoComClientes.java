package br.com.solondiego.algaworksecommerce.iniciandocomjpa;

import br.com.solondiego.algaworksecommerce.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperacaoComClientes extends EntityManagerTest{

    @Test
    public void buscarCliente(){
       Cliente cliente =  entityManager.find(Cliente.class, 1);


        Assertions.assertEquals("João Lucas Gomes Moreira", cliente.getNome());
    }

    @Test
    public void atualizarCliente(){

        Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        cliente.setNome("João Lucas Gomes Moreira");
//        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
    }

    @Test
    public void inserirCliente(){

        Cliente cliente = new Cliente();

        cliente.setId(3);
        cliente.setNome("Maria Alícia Gomes Moreira");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 3);
        Assertions.assertNotNull(clienteVerificacao);
    }
}
