package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * 商品登録を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class InsertUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー情報を登録する.
	 * 
	 * @param user　ユーザー
	 */
	public void insert(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.insert(user);
	}
	
	/**
	 * メールアドレスを検索する
	 * 
	 * @param email メールアドレス
	 * @return　メールアドレス
	 */
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
