package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.Topping;

@SpringBootTest
class ToppingRepositoryTest {
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		System.out.println("全件検索をするテスト");
		
		List<Topping> toppingList = toppingRepository.findAll();
		
		assertEquals(18, toppingList.size(), "件数が一致しません");
		assertEquals("ハワイアンソルト", toppingList.get(0).getName());
		assertEquals("コーラ", toppingList.get(17).getName());

		System.out.println("全件検索するテスト終了");
	}
	
	@Test
	public void testLoad() {
		Integer maxId = template.queryForObject("select max(id) from toppings;", new MapSqlParameterSource(),
				Integer.class);
		Topping resultTopping = toppingRepository.load(maxId);
		
		assertEquals("コーラ", resultTopping.getName(), "名前が登録されていません");
		assertEquals(200, resultTopping.getPriceM(), "Mの金額が登録されていません");
		assertEquals(300, resultTopping.getPriceL(), "Lの金額が登録されていません");
		
		System.out.println("主キー検索するテスト終了");
	}
}
