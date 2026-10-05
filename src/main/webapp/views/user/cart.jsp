<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="page-header">
    <h1 class="page-title">🛒 Giỏ Hàng Của Bạn</h1>
</div>

<c:if test="${not empty success}">
    <div class="alert alert-success">${success}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<c:choose>
    <c:when test="${empty items}">
        <div class="card" style="text-align: center; padding: 48px 24px;">
            <p style="font-size: 16px; color: var(--text-muted); margin-bottom: 16px;">
                Giỏ hàng của bạn đang trống.
            </p>
            <a href="${ctx}/user/home" class="btn btn-primary">
                🎬 Tiếp tục xem và chọn video
            </a>
        </div>
    </c:when>

    <c:otherwise>
        <div class="card" style="padding: 0; overflow: hidden; margin-bottom: 20px;">
            <div class="table-responsive" style="border: none;">
                <table class="table-custom">
                    <thead>
                        <tr>
                            <th style="width: 120px;">Hình ảnh</th>
                            <th>Tên video</th>
                            <th style="width: 140px;">Đơn giá</th>
                            <th style="width: 170px;">Số lượng</th>
                            <th style="width: 150px;">Thành tiền</th>
                            <th style="width: 90px; text-align: center;">Xóa</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}">
                            <tr>
                                <td>
                                    <img src="${ctx}/uploads/${item.video.poster}"
                                         alt="${item.video.title}"
                                         style="width: 100px; height: 65px; object-fit: cover; border-radius: var(--radius-sm);"
                                         onerror="this.onerror=null;this.src='https://placehold.co/100x65?text=No+Image';">
                                </td>
                                <td>
                                    <a href="${ctx}/video/detail?id=${item.video.videoId}"
                                       style="font-weight: 600; color: #0f172a; text-decoration: none;">
                                        <c:out value="${item.video.title}" />
                                    </a>
                                    <div style="font-size: 12px; color: var(--text-muted); margin-top: 4px;">
                                        Mã: ${item.video.videoId} &bull; Loại: ${item.video.category.categoryname}
                                    </div>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${item.video.price}" pattern="#,##0" /> đ
                                </td>
                                <td>
                                    <form action="${ctx}/user/cart" method="post" style="display: flex; gap: 6px; align-items: center;">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="cartItemId" value="${item.cartItemId}">
                                        <input type="number" name="quantity" value="${item.quantity}"
                                               min="1" max="${maxQuantity}" class="form-control"
                                               style="width: 70px; padding: 6px;" required>
                                        <button type="submit" class="btn btn-secondary btn-sm" title="Cập nhật">
                                            ✓
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <strong style="color: #e11d48;">
                                        <fmt:formatNumber value="${item.subtotal}" pattern="#,##0" /> đ
                                    </strong>
                                </td>
                                <td style="text-align: center;">
                                    <form action="${ctx}/user/cart" method="post"
                                          onsubmit="return confirm('Bạn có chắc muốn xóa video này khỏi giỏ hàng?');"
                                          style="display: inline;">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="cartItemId" value="${item.cartItemId}">
                                        <button type="submit" class="btn btn-danger btn-sm" style="padding: 4px 8px;">
                                            ✕
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="card" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 16px;">
            <div>
                <form action="${ctx}/user/cart" method="post"
                      onsubmit="return confirm('Bạn có chắc muốn xóa toàn bộ giỏ hàng?');"
                      style="display: inline;">
                    <input type="hidden" name="action" value="clear">
                    <button type="submit" class="btn btn-secondary btn-sm" style="color: #ef4444;">
                        🗑️ Xóa toàn bộ giỏ
                    </button>
                </form>
                <a href="${ctx}/user/home" class="btn btn-secondary btn-sm" style="margin-left: 8px;">
                    &larr; Tiếp tục chọn video
                </a>
            </div>

            <div style="display: flex; align-items: center; gap: 20px;">
                <div style="font-size: 16px;">
                    Tổng cộng:
                    <span style="font-size: 22px; font-weight: 700; color: #e11d48; margin-left: 6px;">
                        <fmt:formatNumber value="${total}" pattern="#,##0" /> đ
                    </span>
                </div>
                <a href="${ctx}/user/checkout" class="btn btn-primary" style="padding: 10px 24px; font-size: 15px;">
                    💳 Tiến hành thanh toán (COD) &rarr;
                </a>
            </div>
        </div>
    </c:otherwise>
</c:choose>
