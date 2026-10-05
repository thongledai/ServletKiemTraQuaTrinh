<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="page-header">
    <h1 class="page-title">💳 Thanh Toán Đơn Hàng</h1>
</div>

<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}" />
    </div>
</c:if>

<div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(340px, 1fr)); gap: 24px; align-items: start;">
    <!-- Cột thông tin nhận hàng -->
    <div class="card">
        <h2 style="font-size: 18px; margin-bottom: 20px; border-bottom: 1px solid var(--border-color); padding-bottom: 10px;">
            📍 Thông Tin Nhận Hàng
        </h2>

        <form action="${ctx}/user/checkout" method="post">
            <div class="form-group">
                <label class="form-label">Họ và tên người nhận <span style="color:red">*</span></label>
                <input type="text" name="receiverName" class="form-control" maxlength="100"
                       placeholder="Ví dụ: Lê Đại Thông"
                       value="<c:out value='${receiverName}'/>" required>
            </div>

            <div class="form-group">
                <label class="form-label">Số điện thoại liên hệ <span style="color:red">*</span></label>
                <input type="text" name="phone" class="form-control" maxlength="15"
                       placeholder="Ví dụ: 0901234567"
                       value="<c:out value='${phone}'/>" required>
            </div>

            <div class="form-group">
                <label class="form-label">Địa chỉ giao hàng <span style="color:red">*</span></label>
                <textarea name="address" rows="3" class="form-control" maxlength="300"
                          placeholder="Số nhà, đường, phường/xã, quận/huyện, tỉnh/thành..."
                          required><c:out value="${address}" /></textarea>
            </div>

            <div class="form-group">
                <label class="form-label">Ghi chú đơn hàng (nếu có)</label>
                <textarea name="note" rows="2" class="form-control" maxlength="500"
                          placeholder="Ghi chú thêm cho người giao..."><c:out value="${note}" /></textarea>
            </div>

            <div style="background: #f8fafc; border: 1px dashed var(--border-color); padding: 12px; border-radius: var(--radius-sm); margin-bottom: 20px;">
                <label class="form-label" style="margin-bottom: 2px;">Phương thức thanh toán:</label>
                <strong style="color: var(--primary);">💵 Thanh toán khi nhận hàng (COD)</strong>
            </div>

            <div style="display: flex; gap: 12px; align-items: center;">
                <button type="submit" class="btn btn-primary" style="flex: 1; padding: 12px; font-size: 16px;">
                    ✓ Xác nhận đặt hàng
                </button>
                <a href="${ctx}/user/cart" class="btn btn-secondary">
                    Quay lại
                </a>
            </div>
        </form>
    </div>

    <!-- Cột tóm tắt đơn hàng -->
    <div class="card">
        <h2 style="font-size: 18px; margin-bottom: 20px; border-bottom: 1px solid var(--border-color); padding-bottom: 10px;">
            📋 Tóm Tắt Đơn Hàng
        </h2>

        <div class="table-responsive" style="border: none; margin-bottom: 16px;">
            <table class="table-custom">
                <thead>
                    <tr>
                        <th>Video</th>
                        <th style="text-align: center; width: 60px;">SL</th>
                        <th style="text-align: right; width: 110px;">Thành tiền</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td>
                                <div style="font-weight: 600; color: #0f172a;"><c:out value="${item.video.title}" /></div>
                                <div style="font-size: 12px; color: var(--text-muted);">
                                    <fmt:formatNumber value="${item.video.price}" pattern="#,##0" /> đ
                                </div>
                            </td>
                            <td style="text-align: center;">${item.quantity}</td>
                            <td style="text-align: right; font-weight: 600;">
                                <fmt:formatNumber value="${item.subtotal}" pattern="#,##0" /> đ
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div style="display: flex; justify-content: space-between; align-items: center; padding-top: 16px; border-top: 2px solid var(--border-color); font-size: 16px;">
            <strong>Tổng số tiền:</strong>
            <span style="font-size: 22px; font-weight: 700; color: #e11d48;">
                <fmt:formatNumber value="${total}" pattern="#,##0" /> đ
            </span>
        </div>
    </div>
</div>
