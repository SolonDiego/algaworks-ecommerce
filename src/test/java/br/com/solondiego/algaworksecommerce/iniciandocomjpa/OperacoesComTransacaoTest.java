package br.com.solondiego.algaworksecommerce.iniciandocomjpa;

import br.com.solondiego.algaworksecommerce.EntityManagerTest;
import br.com.solondiego.algaworksecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirOperacaoComBancoDeDados() {

        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("Kindle Paperwhite 2ª Geração");
        produto.setPreco(new BigDecimal(799));

        entityManager.detach(produto);
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals("Kindle", produtoVerificacao.getNome());

    }

    @Test
    public void mostrarDiferencaPersistMerge() {

        Produto produtoPersist = new Produto();

        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O processador mais rápido");
        produtoPersist.setPreco(new BigDecimal("2000"));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone Two Plus");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
        Assertions.assertNotNull(produtoVerificacaoPersist);

        Produto produtoMerge = new Produto();

        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da categoria.");
        produtoMerge.setPreco(new BigDecimal("5000"));

        entityManager.getTransaction().begin();

        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell 2");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assertions.assertNotNull(produtoVerificacaoMerge);

    }

    @Test
    public void inserirObjetoComMerge() {

        Produto produto = new Produto();

        produto.setNome("Microfone Rode Videcmic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal("1000"));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificacao);

    }

    @Test
    public void atualizarObjetoGerenciado() {

        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("Kindle Paperwhite 2ª Geração");
        produto.setPreco(new BigDecimal(799));

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals("Kindle Paperwhite 2ª Geração", produtoVerificacao.getNome());

    }

    @Test
    public void atualizarObjeto() {

        Produto produto = new Produto();

        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificacao);
        Assertions.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());

    }

    @Test
    public void removerObjeto() {

        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

//        entityManager.clear(); Não é necessario para a remoção

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assertions.assertNull(produtoVerificacao);

    }

    @Test
    public void inserirOPrimeiroObjeto() {

        Produto produto = new Produto();

        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal("5000"));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificacao);

    }

    @Test
    public void abrirEFecharATrasacao() {

//        Produto produto = new Produto();

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();

    }

}
