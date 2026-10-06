package vn.iotstar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import vn.iotstar.util.OrderStatus_24110344;

@Entity
@Table(name = "Orders")
public class Order_24110344 implements Serializable {

	private static final long serialVersionUID = 1L;

	// Don hang moi = "0"
	public static final String STATUS_NEW = OrderStatus_24110344.NEW.getCode();

	public static final String PAYMENT_COD = "COD";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private Integer orderId;

	@Column(name = "Username", length = 50, nullable = false)
	private String username;

	@Column(name = "ReceiverName", columnDefinition = "NVARCHAR(100)", nullable = false)
	private String receiverName;

	@Column(name = "Phone", columnDefinition = "NVARCHAR(15)", nullable = false)
	private String phone;

	@Column(name = "Address", columnDefinition = "NVARCHAR(300)", nullable = false)
	private String address;

	@Column(name = "Note", columnDefinition = "NVARCHAR(500)")
	private String note;

	@Column(name = "TotalAmount", precision = 18, scale = 0, nullable = false)
	private BigDecimal totalAmount = BigDecimal.ZERO;

	@Column(name = "PaymentMethod", columnDefinition = "NVARCHAR(20)", nullable = false)
	private String paymentMethod = PAYMENT_COD;

	@Column(name = "Status", columnDefinition = "NVARCHAR(20)", nullable = false)
	private String status = STATUS_NEW;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OrderDate", nullable = false)
	private Date orderDate = new Date();

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@OrderBy("orderDetailId ASC")
	private List<OrderDetail_24110344> details = new ArrayList<>();

	public Order_24110344() {
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderDetail_24110344> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail_24110344> details) {
		this.details = details;
	}

	// Ham ho tro hien thi (JSP). Entity dung field access nen khong anh xa
	// xuong DB

	public OrderStatus_24110344 getStatusInfo() {
		return OrderStatus_24110344.fromCode(status);
	}

	public String getStatusLabel() {
		OrderStatus_24110344 s = getStatusInfo();
		return s != null ? s.getLabel() : status;
	}

	public String getStatusBg() {
		OrderStatus_24110344 s = getStatusInfo();
		return s != null ? s.getBg() : "#e2e8f0";
	}

	public String getStatusColor() {
		OrderStatus_24110344 s = getStatusInfo();
		return s != null ? s.getColor() : "#334155";
	}
}