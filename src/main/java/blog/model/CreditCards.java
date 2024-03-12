package blog.model;

import java.util.Date;

public class CreditCards {
	protected int cardNumber;
	protected Date expiration;
	protected String userName;

	public CreditCards(int cardNumber, Date expiration, String userName) {
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.userName = userName;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
