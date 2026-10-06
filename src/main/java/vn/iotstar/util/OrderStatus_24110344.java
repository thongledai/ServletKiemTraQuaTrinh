package vn.iotstar.util;

public enum OrderStatus_24110344 {

	NEW("0", "Đơn hàng mới", "#fef3c7", "#92400e"), CONFIRMED("1", "Đã xác nhận", "#dbeafe", "#1d4ed8"),
	PREPARING("2", "Chuẩn bị hàng", "#e0e7ff", "#4338ca"), SHIPPING("3", "Vận chuyển", "#cffafe", "#0e7490"),
	DELIVERING("4", "Giao hàng", "#ede9fe", "#6d28d9"), DELIVERED("5", "Đã giao", "#dcfce7", "#166534"),
	CANCELLED("6", "Đơn hàng hủy", "#fee2e2", "#b91c1c"), RETURNED("7", "Đơn hàng hoàn", "#f1f5f9", "#475569");

	private final String code;
	private final String label;
	private final String bg;
	private final String color;

	OrderStatus_24110344(String code, String label, String bg, String color) {
		this.code = code;
		this.label = label;
		this.bg = bg;
		this.color = color;
	}

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	// Mau nen cua badge
	public String getBg() {
		return bg;
	}

	// Mau chu cua badge.
	public String getColor() {
		return color;
	}

	// Tra ve null neu code khong hop le (hoac null/rong)
	public static OrderStatus_24110344 fromCode(String code) {
		if (code == null) {
			return null;
		}
		String c = code.trim();
		for (OrderStatus_24110344 s : values()) {
			if (s.code.equals(c)) {
				return s;
			}
		}
		return null;
	}
}