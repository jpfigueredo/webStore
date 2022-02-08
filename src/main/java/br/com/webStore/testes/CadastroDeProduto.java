package br.com.webStore.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.webStore.dao.CategoriaDao;
import br.com.webStore.dao.ProdutoDao;
import br.com.webStore.model.Categoria;
import br.com.webStore.model.Produto;
import br.com.webStore.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		Long id = 1L;

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(id);
		System.out.println(p.getPreco());

		List<Produto> todos = produtoDao.buscarTodos();
		
		todos.forEach(p2 -> System.out.println(p2.getNome()));
		
		BigDecimal precoDoProdutoDao = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi 9");
		System.out.println("Preço do produto: "+precoDoProdutoDao);
		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi 9", "Redmi 9 is a cellphone from Xiaomi company.",
				new BigDecimal("800"), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		produtoDao.cadastrar(celular);
		categoriaDao.cadastrar(celulares);

		em.getTransaction().commit();
		em.close();
	}

}
