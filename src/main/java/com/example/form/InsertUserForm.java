package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * ユーザー登録のパラメータを受け取るフォーム.
 * 
 * @author yamaokahayato
 *
 */
public class InsertUserForm {

	@NotBlank(message="姓を入力してください")
	private String firstName;
	@NotBlank(message="名を入力してください")
	private String lastName;
	@NotBlank(message="メールアドレスを入力してください")
	@Email(message="メールアドレスの形式が不正です")
	private String email;
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}$",message="郵便番号はXXX-XXXXの形式で入力してください")
	private String zipCode;
	@NotBlank(message="住所を入力をしてください")
	private String address;
	@NotBlank(message="電話番号を入力してください")
	@Pattern(regexp="^\\d{2,4}-\\d{2,4}-\\d{4}$",message="電話番号は-を含んで正しい入力をしてください")
	private String telephone;
	@NotBlank(message="パスワードを入力してください")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,16}$", message="パスワードはアルファベットの大文字・小文字・記号（.?/-）を含む  ８文字以上１６文字以内で設定してください")
	private String password;
	@NotBlank(message="パスワードと確認用パスワードが一致しません")
	private String confimationPassword;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfimationPassword() {
		return confimationPassword;
	}

	public void setConfimationPassword(String confimationPassword) {
		this.confimationPassword = confimationPassword;
	}

	@Override
	public String toString() {
		return "InsertUserForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", zipCode="
				+ zipCode + ", address=" + address + ", telephone=" + telephone + ", password=" + password
				+ ", confimationPassword=" + confimationPassword + "]";
	}

}
