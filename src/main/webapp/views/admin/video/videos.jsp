<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="page-header">
    <div>
        <h1 class="page-title">📹 Quản Lý Video</h1>
        <p style="color: var(--text-muted); font-size: 14px; margin-top: 4px;">Danh sách tất cả các video trong cơ sở dữ liệu</p>
    </div>
    <a href="${pageContext.request.contextPath}/admin/videos?action=add" class="btn btn-primary" style="background: #7c3aed; border-color: #7c3aed;">
        ➕ Thêm Video Mới
    </a>
</div>

<div class="card" style="padding: 0; overflow: hidden; margin-bottom: 24px;">
    <div class="table-responsive" style="border: none;">
        <table class="table-custom">
            <thead>
                <tr>
                    <th style="width: 100px;">Mã Video</th>
                    <th style="width: 120px;">Poster</th>
                    <th>Tiêu đề</th>
                    <th style="width: 130px;">Thể loại</th>
                    <th style="width: 100px;">Lượt xem</th>
                    <th style="width: 120px;">Giá</th>
                    <th style="width: 140px; text-align: center;">Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="video" items="${videos}">
                    <tr>
                        <td><strong>${video.videoId}</strong></td>
                        <td>
                            <img src="${pageContext.request.contextPath}/uploads/${video.poster}"
                                 alt="${video.title}"
                                 style="width: 100px; height: 60px; object-fit: cover; border-radius: var(--radius-sm);"
                                 onerror="this.onerror=null;this.src='https://placehold.co/100x60?text=No+Image';">
                        </td>
                        <td>
                            <strong style="color: #0f172a;"><c:out value="${video.title}" /></strong>
                            <c:if test="${not empty video.description}">
                                <div style="font-size: 12px; color: var(--text-muted); margin-top: 4px; display: -webkit-box; -webkit-line-clamp: 1; -webkit-box-orient: vertical; overflow: hidden;">
                                    <c:out value="${video.description}" />
                                </div>
                            </c:if>
                        </td>
                        <td>
                            <span class="badge badge-primary">
                                ${video.category.categoryname}
                            </span>
                        </td>
                        <td>${video.views}</td>
                        <td>
                            <strong style="color: #e11d48;">
                                <fmt:formatNumber value="${video.price}" pattern="#,##0" /> đ
                            </strong>
                        </td>
                        <td style="text-align: center;">
                            <div style="display: flex; gap: 6px; justify-content: center;">
                                <a href="${pageContext.request.contextPath}/admin/videos?action=edit&id=${video.videoId}"
                                   class="btn btn-secondary btn-sm" title="Chỉnh sửa">
                                    ✏️ Sửa
                                </a>
                                <a href="${pageContext.request.contextPath}/admin/videos?action=delete&id=${video.videoId}"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa video [${video.title}]?');"
                                   title="Xóa video">
                                    🗑️ Xóa
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Phân trang -->
<c:if test="${totalPage > 1}">
    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPage}">
            <c:choose>
                <c:when test="${i == page}">
                    <span class="page-item active" style="background: #7c3aed; border-color: #7c3aed;">${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/admin/videos?page=${i}" class="page-item">
                        ${i}
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</c:if>