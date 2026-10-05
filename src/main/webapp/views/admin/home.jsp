<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-header">
    <h1 class="page-title">⚙️ Bảng Điều Khiển Quản Trị (Admin Dashboard)</h1>
</div>

<div class="card" style="padding: 32px; background: linear-gradient(135deg, #f3e8ff 0%, #ede9fe 100%); border: none;">
    <h2 style="font-size: 22px; color: #581c87; margin-bottom: 8px;">Xin chào, ${sessionScope.account.fullname != null ? sessionScope.account.fullname : sessionScope.account.username}!</h2>
    <p style="color: #6b21a8; font-size: 15px; margin-bottom: 20px;">
        Chào mừng bạn đến với khu vực quản trị. Bạn có thể quản lý danh sách video, thêm mới video, chỉnh sửa hoặc xóa video.
    </p>
    <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-primary" style="background: #7c3aed; border-color: #7c3aed;">
        🎬 Quản lý danh sách Video
    </a>
</div>

<div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 20px; margin-top: 24px;">
    <div class="card">
        <h3 style="margin-bottom: 8px; color: #7c3aed;">📹 Danh sách Video</h3>
        <p style="color: #64748b; font-size: 14px; margin-bottom: 16px;">Xem toàn bộ danh sách video, tìm kiếm và phân trang.</p>
        <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary btn-sm">Xem chi tiết &rarr;</a>
    </div>

    <div class="card">
        <h3 style="margin-bottom: 8px; color: #2563eb;">➕ Thêm mới Video</h3>
        <p style="color: #64748b; font-size: 14px; margin-bottom: 16px;">Thêm mới một video vào hệ thống kèm tải ảnh poster.</p>
        <a href="${pageContext.request.contextPath}/admin/videos?action=add" class="btn btn-secondary btn-sm">Thêm video &rarr;</a>
    </div>

    <div class="card">
        <h3 style="margin-bottom: 8px; color: #10b981;">🌐 Giao diện Người dùng</h3>
        <p style="color: #64748b; font-size: 14px; margin-bottom: 16px;">Chuyển nhanh sang trang chủ người dùng để xem hiển thị thực tế.</p>
        <a href="${pageContext.request.contextPath}/user/home" class="btn btn-secondary btn-sm">Vào trang chủ &rarr;</a>
    </div>
</div>
