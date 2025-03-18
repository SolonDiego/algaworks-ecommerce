package br.com.solondiego.algaworksecommerce.mapeamentobasico;

import br.com.solondiego.algaworksecommerce.EntityManagerTest;
import br.com.solondiego.algaworksecommerce.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EstrategiaChavePrimaria extends EntityManagerTest {

    @Test
    public void testarEstrategiaChave() {

        Categoria categoria = new Categoria();

        categoria.setNome("Eletrônicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertNotNull(categoriaVerificacao);

    }

}
