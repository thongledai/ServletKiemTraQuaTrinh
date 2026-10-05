<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-header">
    <h1 class="page-title">➕ Thêm Video Mới</h1>
</div>

<c:if test="${not empty message}">
    <div class="alert alert-danger">
        ${message}
    </div>
</c:if>

<div class="card" style="max-width: 650px; margin: 0 auto; padding: 28px;">
    <form action="${pageContext.request.contextPath}/admin/videos"
          method="post"
          enctype="multipart/form-data">

        <input type="hidden" name="action" value="insert">

        <div class="form-group">
            <label class="form-label">Mã Video <span style="color:red">*</span></label>
            <input type="text" name="videoId" class="form-control" placeholder="Ví dụ: VID021" required>
        </div>

        <div class="form-group">
            <label class="form-label">Tiêu đề Video <span style="color:red">*</span></label>
            <input type="text" name="title" class="form-control" placeholder="Nhập tiêu đề video" required>
        </div>

        <div class="form-group">
            <label class="form-label">Thể loại / Danh mục <span style="color:red">*</span></label>
            <select name="categoryId" class="form-control" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.categoryId}">
                        ${category.categoryname}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label class="form-label">Ảnh Poster <span style="color:red">*</span></label>
            <input type="file" name="poster" class="form-control" accept="image/*" required>
        </div>

        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 16px;">
            <div class="form-group">
                <label class="form-label">Lượt xem ban đầu</label>
                <input type="number" name="views" class="form-control" value="0" min="0">
            </div>

            <div class="form-group">
                <label class="form-label">Giá bán (VNĐ)</label>
                <input type="number" name="price" class="form-control" value="0" min="0" step="1000">
            </div>
        </div>

        <div class="form-group">
            <label class="form-label">Mô tả nội dung</label>
            <textarea name="description" rows="4" class="form-control" placeholder="Nhập mô tả chi tiết video..."></textarea>
        </div>

        <div style="display: flex; gap: 12px; margin-top: 24px;">
            <button type="submit" class="btn btn-primary" style="background: #7c3aed; border-color: #7c3aed; padding: 10px 24px;">
                ✓ Thêm Video
            </button>
            <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary">
                Quay lại danh sách
            </a>
        </div>
    </form>
</div>