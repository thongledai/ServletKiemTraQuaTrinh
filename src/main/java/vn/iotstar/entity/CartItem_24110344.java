package vn.iotstar.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItems")
public class CartItem_24110344 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CartItemId")
	private Integer cartItemId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CartId", nullable = false)
	private Cart_24110344 cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VideoId", nullable = false)
	private Video_24110344 video;

	@Column(name = "Quantity", nullable = false)
	private Integer quantity;

	public CartItem_24110344() {
	}

	/** Thanh tien = don gia x so luong (dung trong JSP: ${item.subtotal}). */
	public BigDecimal getSubtotal() {
		if (video == null || video.getPrice() == null || quantity == null) {
			return BigDecimal.ZERO;
		}
		return video.getPrice().multiply(BigDecimal.valueOf(quantity));
	}

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Cart_24110344 getCart() {
		return cart;
	}

	public void setCart(Cart_24110344 cart) {
		this.cart = cart;
	}

	public Video_24110344 getVideo() {
		return video;
	}

	public void setVideo(Video_24110344 video) {
		this.video = video;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}