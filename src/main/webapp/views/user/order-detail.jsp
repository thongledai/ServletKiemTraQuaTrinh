<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<c:if test="${success}">
    <div class="alert alert-success" style="display: flex; align-items: center; gap: 10px;">
        <span style="font-size: 20px;">🎉</span>
        <div>
            <strong>Đặt hàng thành công!</strong> Cảm ơn bạn đã mua sắm. Bạn sẽ thanh toán bằng tiền mặt khi nhận hàng (COD).
        </div>
    </div>
</c:if>

<div class="page-header">
    <h1 class="page-title">Chi Tiết Đơn Hàng #${order.orderId}</h1>
    <a href="${ctx}/user/orders" class="btn btn-secondary btn-sm">
        &larr; Quay lại lịch sử đơn hàng
    </a>
</div>

<div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 24px; margin-bottom: 24px;">
    <!-- Thông tin đơn hàng -->
    <div class="card">
        <h3 style="font-size: 16px; margin-bottom: 12px; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
            Thông Tin Đơn Hàng
        </h3>
        <p style="font-size: 14px; margin-bottom: 8px;">
            <strong>Mã đơn:</strong> #${order.orderId}
        </p>
        <p style="font-size: 14px; margin-bottom: 8px;">
            <strong>Ngày đặt:</strong>
            <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm:ss" />
        </p>
        <p style="font-size: 14px; margin-bottom: 8px;">
            <strong>Trạng thái:</strong>
            <span class="badge" style="background: ${order.statusBg}; color: ${order.statusColor};">
                <c:out value="${order.statusLabel}" />
            </span>
        </p>
        <p style="font-size: 14px;">
            <strong>Phương thức thanh toán:</strong>
            <c:choose>
                <c:when test="${order.paymentMethod == 'COD'}">Thanh toán khi nhận hàng (COD)</c:when>
                <c:otherwise><c:out value="${order.paymentMethod}" /></c:otherwise>
            </c:choose>
        </p>
    </div>

    <!-- Thông tin người nhận -->
    <div class="card">
        <h3 style="font-size: 16px; margin-bottom: 12px; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
            Thông Tin Người Nhận
        </h3>
        <p style="font-size: 14px; margin-bottom: 8px;">
            <strong>Người nhận:</strong> <c:out value="${order.receiverName}" />
        </p>
        <p style="font-size: 14px; margin-bottom: 8px;">
            <strong>Số điện thoại:</strong> <c:out value="${order.phone}" />
        </p>
        <p style="font-size: 14px; margin-bottom: 8px;">
            <strong>Địa chỉ giao hàng:</strong> <c:out value="${order.address}" />
        </p>
        <c:if test="${not empty order.note}">
            <p style="font-size: 14px;">
                <strong>Ghi chú:</strong> <c:out value="${order.note}" />
            </p>
        </c:if>
    </div>
</div>

<!-- Danh sách sản phẩm đã đặt -->
<div class="card" style="padding: 0; overflow: hidden; margin-bottom: 24px;">
    <div style="padding: 16px 20px; background: #f8fafc; border-bottom: 1px solid var(--border-color); font-weight: 600;">
        Danh sách các video đã đặt
    </div>
    <div class="table-responsive" style="border: none;">
        <table class="table-custom">
            <thead>
                <tr>
                    <th style="width: 100px;">Poster</th>
                    <th>Tên video</th>
                    <th style="width: 140px;">Đơn giá</th>
                    <th style="width: 80px; text-align: center;">SL</th>
                    <th style="width: 150px; text-align: right;">Thành tiền</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${order.details}">
                    <tr>
                        <td>
                            <img src="${ctx}/uploads/${d.poster}"
                                 alt="${d.videoTitle}"
                                 style="width: 80px; height: 50px; object-fit: cover; border-radius: var(--radius-sm);"
                                 onerror="this.onerror=null;this.src='https://placehold.co/80x50?text=No+Image';">
                        </td>
                        <td>
                            <strong><c:out value="${d.videoTitle}" /></strong>
                            <div style="font-size: 12px; color: var(--text-muted);">Mã: ${d.videoId}</div>
                        </td>
                        <td>
                            <fmt:formatNumber value="${d.price}" pattern="#,##0" /> đ
                        </td>
                        <td style="text-align: center;">${d.quantity}</td>
                        <td style="text-align: right; font-weight: 600; color: #e11d48;">
                            <fmt:formatNumber value="${d.subtotal}" pattern="#,##0" /> đ
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div style="padding: 16px 20px; text-align: right; background: #ffffff; border-top: 1px solid var(--border-color); font-size: 16px;">
        Tổng tiền thanh toán:
        <span style="font-size: 22px; font-weight: 700; color: #e11d48; margin-left: 8px;">
            <fmt:formatNumber value="${order.totalAmount}" pattern="#,##0" /> đ
        </span>
    </div>
</div>

<div>
    <a href="${ctx}/user/home" class="btn btn-primary">
        Tiếp tục khám phá video
    </a>
</div>