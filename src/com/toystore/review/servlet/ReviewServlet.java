package com.toystore.review.servlet;

import com.toystore.review.model.GuestReview;
import com.toystore.review.model.Review;
import com.toystore.review.model.Review.ReviewStatus;
import com.toystore.review.model.VerifiedReview;
import com.toystore.review.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    private final ReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "view":
                request.setAttribute("reviewList", ReviewService.getAllReviews());
                request.getRequestDispatcher("/WEB-INF/views/review/view-reviews.jsp").forward(request, response);
                break;

            case "addPage":
                request.getRequestDispatcher("/WEB-INF/views/review/add-review.jsp").forward(request, response);
                break;

            case "editPage":
                request.getRequestDispatcher("/WEB-INF/views/review/edit-review.jsp").forward(request, response);
                break;

            case "delete":
                reviewService.deleteReview(request.getParameter("id"));
                response.sendRedirect(request.getContextPath() + "/review?action=view");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/review?action=view");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // CREATE REVIEW
        if ("add".equals(action)) {
            String reviewType = request.getParameter("reviewType");
            String reviewId = reviewService.generateId();
            String userId = request.getParameter("userId");
            String toyId = request.getParameter("toyId");
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("comment");
            String date = LocalDate.now().toString();

            Review review;
            if ("verified".equals(reviewType)) {
                String purchaseId = request.getParameter("purchaseId");
                review = new VerifiedReview(reviewId, userId, toyId, rating, comment, date, ReviewStatus.APPROVED, purchaseId);
            } else {
                String guestName = request.getParameter("guestName");
                review = new GuestReview(reviewId, userId, toyId, rating, comment, date, ReviewStatus.PENDING, guestName);
            }
            reviewService.addReview(review);

            // UPDATE REVIEW
        } else if ("update".equals(action)) {
            reviewService.updateReview(
                    request.getParameter("reviewId"),
                    Integer.parseInt(request.getParameter("rating")),
                    request.getParameter("comment")
            );

            // UPDATE STATUS
        } else if ("updateStatus".equals(action)) {
            reviewService.updateStatus(
                    request.getParameter("reviewId"),
                    ReviewStatus.valueOf(request.getParameter("status"))
            );
        }

        response.sendRedirect(request.getContextPath() + "/review?action=view");
    }
}