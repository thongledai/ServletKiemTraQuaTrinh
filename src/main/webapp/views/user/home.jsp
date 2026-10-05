<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="page-header">
    <div>
        <h1 class="page-title">Danh Sách Video</h1>
        <p style="color: var(--text-muted); font-size: 14px; margin-top: 4px;">
            <c:choose>
                <c:when test="${not empty category}">
                    Chủ đề: <b>${category.categoryname}</b>
                </c:when>
                <c:otherwise>
                    Tất cả thể loại video
                </c:otherwise>
            </c:choose>
        </p>
    </div>
</div>

<!-- Thanh danh mục dạng Chip / Tag -->
<div class="category-chips">
    <a href="${pageContext.request.contextPath}/user/home"
       class="chip ${empty param.categoryId ? 'active' : ''}">
        Tất cả
    </a>

    <c:forEach var="c" items="${categories}">
        <a href="${pageContext.request.contextPath}/user/home?categoryId=${c.categoryId}"
           class="chip ${param.categoryId == c.categoryId ? 'active' : ''}">
            <span>${c.categoryname}</span>
            <span class="chip-count">${c.videoCount}</span>
        </a>
    </c:forEach>
</div>

<!-- Danh sách Video dạng lưới (Card Grid) -->
<c:choose>
    <c:when test="${empty videos}">
        <div class="card" style="text-align: center; padding: 40px; color: var(--text-muted);">
            <p style="font-size: 16px;">Hiện chưa có video nào trong danh mục này.</p>
            <a href="${pageContext.request.contextPath}/user/home" class="btn btn-secondary" style="margin-top: 12px;">
                Xem tất cả video
            </a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="video-grid">
            <c:forEach var="video" items="${videos}">
                <div class="video-card">
                    <a href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}">
                        <img src="${pageContext.request.contextPath}/uploads/${video.poster}"
                             alt="${video.title}"
                             class="video-thumb"
                             onerror="this.onerror=null;this.src='https://placehold.co/300x200?text=No+Image';">
                    </a>

                    <div class="video-body">
                        <span class="badge badge-primary" style="margin-bottom: 8px; align-self: flex-start;">
                            ${video.category.categoryname}
                        </span>

                        <a href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}"
                           style="text-decoration: none;">
                            <h3 class="video-title" title="${video.title}">${video.title}</h3>
                        </a>

                        <div class="video-meta">
                            <span>👁️ ${video.views} lượt xem</span>
                            <span>Mã: ${video.videoId}</span>
                        </div>

                        <div class="video-price">
                            <fmt:formatNumber value="${video.price}" pattern="#,##0" /> đ
                        </div>

                        <div class="video-actions">
                            <a href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}"
                               class="btn btn-secondary btn-sm" style="flex: 1;">
                                Chi tiết
                            </a>

                            <form action="${pageContext.request.contextPath}/user/cart" method="post" style="flex: 1;">
                                <input type="hidden" name="action" value="add">
                                <input type="hidden" name="videoId" value="${video.videoId}">
                                <input type="hidden" name="quantity" value="1">
                                <button type="submit" class="btn btn-primary btn-sm" style="width: 100%;">
                                    + Giỏ hàng
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Phân trang -->
        <c:if test="${totalPage > 1}">
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${totalPage}">
                    <c:choose>
                        <c:when test="${i == page}">
                            <span class="page-item active">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/user/home?categoryId=${categoryId}&page=${i}"
                               class="page-item">
                                ${i}
                            </a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </c:if>
    </c:otherwise>
</c:choose>