package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * 登録画面を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class LoginUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * メールアドレスとパスワードでログインする
	 * 
	 * @param email　メールアドレス
	 * @param password　パスワード
	 * @return　ユーザー情報
	 */
	public User login(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

}
