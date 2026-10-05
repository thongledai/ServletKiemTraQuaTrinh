package vn.iotstar.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Video_24110344;
import vn.iotstar.service.VideoService_24110344;
import vn.iotstar.service.impl.VideoServiceImpl_24110344;

@WebServlet("/video/detail")
public class VideoDetailController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VideoService_24110344 videoService = new VideoServiceImpl_24110344();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		if (id == null || id.isBlank()) {
			resp.sendRedirect(req.getContextPath() + "/user/home");
			return;
		}

		Video_24110344 video = videoService.findById(id);

		if (video == null) {
			resp.sendRedirect(req.getContextPath() + "/user/home");
			return;
		}

		req.setAttribute("video", video);

		req.getRequestDispatcher("/views/user/video-detail.jsp").forward(req, resp);
	}
}