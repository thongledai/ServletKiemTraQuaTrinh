package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category_24110344;
import vn.iotstar.entity.User_24110344;
import vn.iotstar.entity.Video_24110344;
import vn.iotstar.service.CategoryService_24110344;
import vn.iotstar.service.VideoService_24110344;
import vn.iotstar.service.impl.CategoryServiceImp_24110344;
import vn.iotstar.service.impl.VideoServiceImpl_24110344;

@MultipartConfig
@WebServlet("/admin/videos")
public class VideoController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VideoService_24110344 videoService = new VideoServiceImpl_24110344();

	private CategoryService_24110344 categoryService = new CategoryServiceImp_24110344();

	private boolean isAdmin(HttpServletRequest req) {

		HttpSession session = req.getSession(false);

		if (session == null) {
			return false;
		}

		User_24110344 user = (User_24110344) session.getAttribute("account");

		return user != null && Boolean.TRUE.equals(user.getAdmin());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (!isAdmin(req)) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String action = req.getParameter("action");

		if (action == null) {
			action = "list";
		}

		switch (action) {

		case "add":
			add(req, resp);
			break;

		case "edit":
			edit(req, resp);
			break;

		case "delete":
			delete(req, resp);
			break;

		default:
			list(req, resp);
			break;
		}
	}

	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int page = 1;

		String pageParam = req.getParameter("page");

		if (pageParam != null) {

			try {
				page = Integer.parseInt(pageParam);

			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page < 1) {
			page = 1;
		}

		int size = 6;

		List<Video_24110344> videos = videoService.findAll(page, size);

		long totalVideo = videoService.count();

		int totalPage = (int) Math.ceil((double) totalVideo / size);

		req.setAttribute("videos", videos);
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);

		req.getRequestDispatcher("/views/admin/video/videos.jsp").forward(req, resp);
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Category_24110344> categories = categoryService.findAll();

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/views/admin/video/video-add.jsp").forward(req, resp);
	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		if (id == null || id.isBlank()) {
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
			return;
		}

		Video_24110344 video = videoService.findById(id);

		if (video == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
			return;
		}

		List<Category_24110344> categories = categoryService.findAll();

		req.setAttribute("video", video);

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/views/admin/video/video-edit.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String id = req.getParameter("id");

		if (id != null && !id.isBlank()) {

			Video_24110344 video = videoService.findById(id);

			if (video != null) {
				videoService.delete(id);
			}
		}

		resp.sendRedirect(req.getContextPath() + "/admin/videos");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (!isAdmin(req)) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("insert".equals(action)) {

			insert(req, resp);

		} else if ("update".equals(action)) {

			update(req, resp);
		}
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String videoId = req.getParameter("videoId");

		String title = req.getParameter("title");

		String viewsParam = req.getParameter("views");

		String description = req.getParameter("description");

		String categoryIdParam = req.getParameter("categoryId");

		if (videoId == null || videoId.isBlank()) {

			req.setAttribute("message", "Mã video không được để trống");

			loadCategoriesAndForward(req, resp);

			return;
		}

		Video_24110344 oldVideo = videoService.findById(videoId);

		if (oldVideo != null) {

			req.setAttribute("message", "Mã video " + videoId + " đã tồn tại");

			loadCategoriesAndForward(req, resp);

			return;
		}

		Part posterPart = req.getPart("poster");

		if (posterPart == null || posterPart.getSize() == 0) {

			req.setAttribute("message", "Vui lòng chọn ảnh poster");

			loadCategoriesAndForward(req, resp);

			return;
		}

		String fileName = posterPart.getSubmittedFileName();

		String uploadPath = getServletContext().getRealPath("/uploads");

		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		posterPart.write(uploadPath + File.separator + fileName);

		int views = 0;

		if (viewsParam != null && !viewsParam.isBlank()) {

			try {

				views = Integer.parseInt(viewsParam);

				if (views < 0) {
					throw new NumberFormatException();
				}

			} catch (NumberFormatException e) {

				req.setAttribute("message", "Views phải là số nguyên không âm");

				loadCategoriesAndForward(req, resp);

				return;
			}
		}

		BigDecimal price = parsePrice(req.getParameter("price"));

		if (price == null) {

			req.setAttribute("message", "Giá phải là số không âm");

			loadCategoriesAndForward(req, resp);

			return;
		}

		Integer categoryId;

		try {

			categoryId = Integer.parseInt(categoryIdParam);

		} catch (NumberFormatException e) {

			req.setAttribute("message", "Category không hợp lệ");

			loadCategoriesAndForward(req, resp);

			return;
		}

		Category_24110344 category = categoryService.findById(categoryId);

		if (category == null) {

			req.setAttribute("message", "Category không tồn tại");

			loadCategoriesAndForward(req, resp);

			return;
		}

		Video_24110344 video = new Video_24110344();

		video.setVideoId(videoId);
		video.setTitle(title);
		video.setPoster(fileName);
		video.setViews(views);
		video.setPrice(price);
		video.setDescription(description);
		video.setActive(true);
		video.setCategory(category);

		videoService.insert(video);

		resp.sendRedirect(req.getContextPath() + "/admin/videos");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String videoId = req.getParameter("videoId");

		String title = req.getParameter("title");

		String viewsParam = req.getParameter("views");

		String description = req.getParameter("description");

		String categoryIdParam = req.getParameter("categoryId");

		Video_24110344 video = videoService.findById(videoId);

		if (video == null) {

			resp.sendRedirect(req.getContextPath() + "/admin/videos");

			return;
		}

		int views = 0;

		if (viewsParam != null && !viewsParam.isBlank()) {

			try {

				views = Integer.parseInt(viewsParam);

				if (views < 0) {
					throw new NumberFormatException();
				}

			} catch (NumberFormatException e) {

				req.setAttribute("message", "Views phải là số nguyên không âm");

				loadCategoriesAndForwardEdit(req, resp, video);

				return;
			}
		}

		BigDecimal price = parsePrice(req.getParameter("price"));

		if (price == null) {

			req.setAttribute("message", "Giá phải là số không âm");

			loadCategoriesAndForwardEdit(req, resp, video);

			return;
		}

		Integer categoryId;

		try {

			categoryId = Integer.parseInt(categoryIdParam);

		} catch (NumberFormatException e) {

			req.setAttribute("message", "Category không hợp lệ");

			loadCategoriesAndForwardEdit(req, resp, video);

			return;
		}

		Category_24110344 category = categoryService.findById(categoryId);

		if (category == null) {

			req.setAttribute("message", "Category không tồn tại");

			loadCategoriesAndForwardEdit(req, resp, video);

			return;
		}

		Part posterPart = req.getPart("poster");

		String fileName = video.getPoster();

		if (posterPart != null && posterPart.getSize() > 0) {

			fileName = posterPart.getSubmittedFileName();

			String uploadPath = getServletContext().getRealPath("/uploads");

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			posterPart.write(uploadPath + File.separator + fileName);
		}

		video.setTitle(title);
		video.setPoster(fileName);
		video.setViews(views);
		video.setPrice(price);
		video.setDescription(description);
		video.setCategory(category);

		videoService.update(video);

		resp.sendRedirect(req.getContextPath() + "/admin/videos");
	}

	/** Tra ve null neu gia khong hop le (khong phai so hoac am). De trong = 0. */
	private BigDecimal parsePrice(String value) {

		if (value == null || value.isBlank()) {
			return BigDecimal.ZERO;
		}

		try {

			BigDecimal price = new BigDecimal(value.trim());

			return price.signum() < 0 ? null : price;

		} catch (NumberFormatException e) {

			return null;
		}
	}

	private void loadCategoriesAndForward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<Category_24110344> categories = categoryService.findAll();

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/views/admin/video/video-add.jsp").forward(req, resp);
	}

	private void loadCategoriesAndForwardEdit(HttpServletRequest req, HttpServletResponse resp, Video_24110344 video)
			throws ServletException, IOException {

		List<Category_24110344> categories = categoryService.findAll();

		req.setAttribute("categories", categories);

		req.setAttribute("video", video);

		req.getRequestDispatcher("/views/admin/video/video-edit.jsp").forward(req, resp);
	}
}