package br.com.solondiego.algaworksecommerce.mapeamentobasico;

import br.com.solondiego.algaworksecommerce.EntityManagerTest;
import br.com.solondiego.algaworksecommerce.model.Cliente;
import br.com.solondiego.algaworksecommerce.model.SexoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    public void testarEnum() {

        Cliente cliente = new Cliente();

        cliente.setNome("Solon Diego");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assertions.assertNotNull(clienteVerificacao);

    }

}
