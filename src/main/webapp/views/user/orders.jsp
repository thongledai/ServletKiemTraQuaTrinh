<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="page-header">
    <h1 class="page-title">📦 Lịch Sử Đơn Hàng</h1>
</div>

<c:choose>
    <c:when test="${empty orders}">
        <div class="card" style="text-align: center; padding: 48px 24px;">
            <p style="font-size: 16px; color: var(--text-muted); margin-bottom: 16px;">
                Bạn chưa có đơn hàng nào trong tài khoản.
            </p>
            <a href="${ctx}/user/home" class="btn btn-primary">
                🎬 Khám phá sản phẩm ngay
            </a>
        </div>
    </c:when>

    <c:otherwise>
        <div class="card" style="padding: 0; overflow: hidden;">
            <div class="table-responsive" style="border: none;">
                <table class="table-custom">
                    <thead>
                        <tr>
                            <th style="width: 100px;">Mã đơn</th>
                            <th>Ngày đặt</th>
                            <th>Người nhận</th>
                            <th>Tổng tiền</th>
                            <th>Thanh toán</th>
                            <th>Trạng thái</th>
                            <th style="width: 100px; text-align: center;">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="o" items="${orders}">
                            <tr>
                                <td><strong>#${o.orderId}</strong></td>
                                <td>
                                    <fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm" />
                                </td>
                                <td><c:out value="${o.receiverName}" /></td>
                                <td>
                                    <strong style="color: #e11d48;">
                                        <fmt:formatNumber value="${o.totalAmount}" pattern="#,##0" /> đ
                                    </strong>
                                </td>
                                <td>
                                    <span class="badge badge-primary">
                                        <c:choose>
                                            <c:when test="${o.paymentMethod == 'COD'}">COD (Tiền mặt)</c:when>
                                            <c:otherwise><c:out value="${o.paymentMethod}" /></c:otherwise>
                                        </c:choose>
                                    </span>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${o.status == 'PENDING'}">
                                            <span class="badge badge-warning">Chờ xác nhận</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-success"><c:out value="${o.status}" /></span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td style="text-align: center;">
                                    <a href="${ctx}/user/orders?id=${o.orderId}" class="btn btn-secondary btn-sm">
                                        Chi tiết
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>
