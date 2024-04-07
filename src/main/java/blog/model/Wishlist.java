package blog.model;

public class Wishlist {
	protected int wishListId;
	protected String userName;
	protected String productId;

	public Wishlist(int wishListId, String userName, String productId) {
		this.wishListId = wishListId;
		this.userName = userName;
		this.productId = productId;
	}

	public int getWishListId() {
		return wishListId;
	}

	public void setWishListId(int wishListId) {
		this.wishListId = wishListId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}