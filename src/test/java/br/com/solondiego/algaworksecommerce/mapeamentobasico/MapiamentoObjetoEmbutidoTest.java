package br.com.solondiego.algaworksecommerce.mapeamentobasico;

import br.com.solondiego.algaworksecommerce.EntityManagerTest;
import br.com.solondiego.algaworksecommerce.model.EnderecoEntregaPedido;
import br.com.solondiego.algaworksecommerce.model.Pedido;
import br.com.solondiego.algaworksecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapiamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido(){

        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();

        endereco.setCep("58900-000");
        endereco.setLogradouro("Rua José Leôncio da Silva");
        endereco.setNumero("275");
        endereco.setBairro("Jardim Primavera");
        endereco.setComplemento("Atrás do IFPB");
        endereco.setCidade("Cajazeiras");
        endereco.setEstado("PB");

        Pedido pedido = new Pedido();

        pedido.setDataPedido(LocalDateTime.now());
        pedido.setDataConclusao(null);
        pedido.setNotaFiscalId(null);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEndereco(endereco);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao);

    }

}
