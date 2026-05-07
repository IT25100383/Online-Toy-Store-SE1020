package com.toystore.review.service;

import com.toystore.review.model.Review;
import com.toystore.review.model.Review.ReviewStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {

    // Path to your txt file - this is your "database"
    private static final String FILE_PATH = "data/reviews.txt";

    // ───────────────────────────────────────────
    // CREATE - Save a new review to reviews.txt
    // ───────────────────────────────────────────
    public void addReview(Review review) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(review.toFileString());
        bw.newLine();
        bw.close();
    }

    // ───────────────────────────────────────────
    // READ - Load all reviews from reviews.txt
    // ───────────────────────────────────────────
    public List<Review> getAllReviews() throws IOException {
        List<Review> reviews = new ArrayList<>();
        File file = new File(FILE_PATH);

        // If file doesn't exist yet, return empty list
        if (!file.exists()) return reviews;

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                try {
                    reviews.add(Review.fromFileString(line));
                } catch (IllegalArgumentException e) {
                    // Skip corrupted lines
                    System.out.println("Skipping bad line: " + line);
                }
            }
        }
        br.close();
        return reviews;
    }

    // READ - Get one specific review by its ID
    public Review getReviewById(String reviewId) throws IOException {
        for (Review r : getAllReviews()) {
            if (r.getReviewId().equals(reviewId)) {
                return r;
            }
        }
        return null; // not found
    }

    // READ - Get all reviews for a specific toy
    public List<Review> getReviewsByToyId(String toyId) throws IOException {
        List<Review> result = new ArrayList<>();
        for (Review r : getAllReviews()) {
            if (r.getToyId().equals(toyId)) {
                result.add(r);
            }
        }
        return result;
    }

    // ───────────────────────────────────────────
    // UPDATE - Change rating and comment of a review
    // ───────────────────────────────────────────
    public boolean updateReview(String reviewId, int newRating,
                                String newComment) throws IOException {
        List<Review> reviews = getAllReviews();
        boolean found = false;

        for (Review r : reviews) {
            if (r.getReviewId().equals(reviewId)) {
                r.setRating(newRating);
                r.setComment(newComment);
                found = true;
            }
        }

        if (found) saveAll(reviews); // rewrite the whole file
        return found;
    }

    // UPDATE - Change status of a review (PENDING/APPROVED/REJECTED)
    public boolean updateStatus(String reviewId,
                                ReviewStatus newStatus) throws IOException {
        List<Review> reviews = getAllReviews();
        boolean found = false;

        for (Review r : reviews) {
            if (r.getReviewId().equals(reviewId)) {
                r.setStatus(newStatus);
                found = true;
            }
        }

        if (found) saveAll(reviews);
        return found;
    }

    // ───────────────────────────────────────────
    // DELETE - Remove a review by ID
    // ───────────────────────────────────────────
    public boolean deleteReview(String reviewId) throws IOException {
        List<Review> reviews = getAllReviews();
        boolean removed = reviews.removeIf(
                r -> r.getReviewId().equals(reviewId)
        );

        if (removed) saveAll(reviews); // rewrite file without deleted review
        return removed;
    }

    // ───────────────────────────────────────────
    // HELPER - Rewrite entire reviews.txt file
    // ───────────────────────────────────────────
    private void saveAll(List<Review> reviews) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE_PATH, false) // false = overwrite mode
        );
        for (Review r : reviews) {
            bw.write(r.toFileString());
            bw.newLine();
        }
        bw.close();
    }

    // ───────────────────────────────────────────
    // HELPER - Generate a unique ID for new reviews
    // ───────────────────────────────────────────
    public String generateId() throws IOException {
        int count = getAllReviews().size() + 1;
        return "REV" + String.format("%03d", count); // REV001, REV002 etc.
    }
}