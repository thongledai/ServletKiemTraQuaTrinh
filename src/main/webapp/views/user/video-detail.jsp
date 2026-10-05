<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div style="margin-bottom: 16px;">
    <a href="${pageContext.request.contextPath}/user/home" class="btn btn-secondary btn-sm">
        &larr; Quay lại danh sách
    </a>
</div>

<div class="card" style="padding: 32px;">
    <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap: 32px;">
        <!-- Cột hình ảnh Poster -->
        <div>
            <img src="${pageContext.request.contextPath}/uploads/${video.poster}"
                 alt="${video.title}"
                 style="width: 100%; max-height: 380px; object-fit: cover; border-radius: var(--radius-md); box-shadow: var(--shadow-sm);"
                 onerror="this.onerror=null;this.src='https://placehold.co/600x400?text=No+Image';">
        </div>

        <!-- Cột thông tin chi tiết -->
        <div style="display: flex; flex-direction: column;">
            <span class="badge badge-primary" style="align-self: flex-start; margin-bottom: 12px; font-size: 13px;">
                ${video.category.categoryname}
            </span>

            <h1 style="font-size: 26px; color: #0f172a; margin-bottom: 12px; line-height: 1.3;">
                ${video.title}
            </h1>

            <div style="display: flex; gap: 16px; font-size: 14px; color: var(--text-muted); margin-bottom: 16px; border-bottom: 1px solid var(--border-color); padding-bottom: 12px;">
                <span>Mã video: <b>${video.videoId}</b></span>
                <span>Lượt xem: <b>${video.views}</b></span>
            </div>

            <div style="font-size: 28px; font-weight: 700; color: #e11d48; margin-bottom: 20px;">
                <fmt:formatNumber value="${video.price}" pattern="#,##0" /> đ
            </div>

            <div style="margin-bottom: 24px;">
                <h4 style="font-size: 15px; color: #334155; margin-bottom: 6px;">Mô tả nội dung:</h4>
                <p style="color: #475569; font-size: 14px; line-height: 1.6; background: #f8fafc; padding: 12px; border-radius: var(--radius-sm);">
                    ${video.description != null && !video.description.isEmpty() ? video.description : 'Chưa có mô tả chi tiết cho video này.'}
                </p>
            </div>

            <div style="margin-top: auto;">
                <form action="${pageContext.request.contextPath}/user/cart" method="post" style="display: flex; gap: 12px; align-items: center;">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="videoId" value="${video.videoId}">
                    
                    <label style="font-size: 14px; font-weight: 500;">Số lượng:</label>
                    <input type="number" name="quantity" value="1" min="1" max="100" class="form-control" style="width: 80px;">

                    <button type="submit" class="btn btn-primary" style="padding: 10px 24px; font-size: 15px;">
                        🛒 Thêm vào giỏ hàng
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>