package vn.iotstar.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category_24110344;
import vn.iotstar.entity.Video_24110344;
import vn.iotstar.service.CategoryService_24110344;
import vn.iotstar.service.VideoService_24110344;
import vn.iotstar.service.impl.CategoryServiceImp_24110344;
import vn.iotstar.service.impl.VideoServiceImpl_24110344;

@WebServlet("/user/home")
public class UserHomeController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CategoryService_24110344 categoryService = new CategoryServiceImp_24110344();

	private VideoService_24110344 videoService = new VideoServiceImpl_24110344();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Category_24110344> categories = categoryService.findAll();
		for (Category_24110344 category : categories) {

			long videoCount = videoService.countByCategory(category.getCategoryId());

			category.setVideoCount(videoCount);
		}
		if (categories.isEmpty()) {

			req.setAttribute("categories", categories);

			req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);

			return;
		}

		String categoryIdParam = req.getParameter("categoryId");

		Integer categoryId;

		if (categoryIdParam == null || categoryIdParam.isBlank()) {

			categoryId = categories.get(0).getCategoryId();

		} else {

			try {
				categoryId = Integer.parseInt(categoryIdParam);

			} catch (NumberFormatException e) {

				categoryId = categories.get(0).getCategoryId();
			}
		}

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

		int size = 3;

		List<Video_24110344> videos = videoService.findByCategory(categoryId, page, size);

		long totalVideo = videoService.countByCategory(categoryId);

		int totalPage = (int) Math.ceil((double) totalVideo / size);

		Category_24110344 category = categoryService.findById(categoryId);

		req.setAttribute("categories", categories);
		req.setAttribute("category", category);
		req.setAttribute("videos", videos);

		req.setAttribute("categoryId", categoryId);
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);

		req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
	}
}