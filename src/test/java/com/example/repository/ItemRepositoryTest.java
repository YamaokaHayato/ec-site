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

import com.example.domain.Item;

@SpringBootTest
class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

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
	public void testfindAll() {
		System.out.println("全件検索するテスト開始");
		List<Item> itemList = itemRepository.findAll();

		assertEquals(18, itemList.size(), "件数が一致しません");
		assertEquals("アサイーボウル", itemList.get(0).getName(), "価格順に並んでいません");
		assertEquals("ラムファイヤーのマイタイ", itemList.get(17).getName(), "価格順に並んでいません");
		
		System.out.println("全件検索するテスト終了");
	}
	
	@Test
	public void testLoad() {
		System.out.println("主キー検索するテスト開始");
		
		Integer maxId = template.queryForObject("select max(id) from items;", new MapSqlParameterSource(), 
				Integer.class);
		Item resultItem = itemRepository.load(maxId);
		
		assertEquals("ニコスピア38のアヒステーキ", resultItem.getName(), "名前が登録されていません");
		assertEquals("ニコスピアで夜しか提供していないアヒ(マグロ)のステーキです。リピータが多く絶品です。", 
				resultItem.getDescription(), "説明が登録されていません");
		assertEquals(2700, resultItem.getPriceM(), "Mの金額が登録されていません");
		assertEquals(4050, resultItem.getPriceL(), "Lの金額が登録されていません");
		assertEquals("18.jpg", resultItem.getImagePath(), "写真が登録されていません");
		
		System.out.println("主キー検索するテスト終了");
	}
	
	@Test
	public void testFindByName() {
		System.out.println("商品名を検索するテスト開始");
		List<Item> resultItem = itemRepository.findbyName("アサイーボウル");
		
		assertEquals("アサイーボウル", resultItem.get(0).getName(), "名前が登録されていません");
		assertEquals("ブラジル発祥のデザートで、アメリカ合衆国のハワイ州で人気が出てよく知られるようになった商品です。"
				+ "アサイーのスムージーをボウルに盛りつけ、グラノーラなどのシリアル食品やバナナやリンゴやイチゴなど好みの果物をのせて蜂蜜をかけて供する。"
				+ "朝食とされることもある。",
				resultItem.get(0).getDescription(), "説明が登録されていません");
		assertEquals(1490, resultItem.get(0).getPriceM(), "Mの金額が登録されていません");
		assertEquals(2570, resultItem.get(0).getPriceL(), "Lの金額が登録されていません");
		assertEquals("2.jpg", resultItem.get(0).getImagePath(), "写真が登録されていません");
		
		System.out.println("商品名を検索するテスト終了");
	}

}
