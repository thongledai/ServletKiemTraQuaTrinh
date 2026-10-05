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

/**
 * Luu "anh chup" ten video, poster, gia tai thoi diem dat hang nen khong lien
 * ket FK toi Videos: admin sua gia / xoa video thi don cu khong bi anh huong.
 */
@Entity
@Table(name = "OrderDetails")
public class OrderDetail_24110344 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderDetailId")
	private Integer orderDetailId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrderId", nullable = false)
	private Order_24110344 order;

	@Column(name = "VideoId", length = 50, nullable = false)
	private String videoId;

	@Column(name = "VideoTitle", columnDefinition = "NVARCHAR(200)")
	private String videoTitle;

	@Column(name = "Poster", columnDefinition = "NVARCHAR(500)")
	private String poster;

	@Column(name = "Price", precision = 18, scale = 0, nullable = false)
	private BigDecimal price;

	@Column(name = "Quantity", nullable = false)
	private Integer quantity;

	public OrderDetail_24110344() {
	}

	public BigDecimal getSubtotal() {
		if (price == null || quantity == null) {
			return BigDecimal.ZERO;
		}
		return price.multiply(BigDecimal.valueOf(quantity));
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Order_24110344 getOrder() {
		return order;
	}

	public void setOrder(Order_24110344 order) {
		this.order = order;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}