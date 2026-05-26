package com.toystore.review.service;

import com.toystore.review.model.Review;
import com.toystore.review.model.Review.ReviewStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {

    private static final String FILE_PATH = "C:/Users/USER/Documents/EDU/Y1/Y1S2/OOP/Project/Toy Store Sandbox/data/reviews.txt";

    public void addReview(Review review) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(review.toFileString());
            bw.newLine();
        }
    }

    public static List<Review> getAllReviews() throws IOException {
        List<Review> reviews = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return reviews;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    try {
                        reviews.add(Review.fromFileString(line));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping bad line: " + line);
                    }
                }
            }
        }
        return reviews;
    }

    public Review getReviewById(String reviewId) throws IOException {
        for (Review r : getAllReviews()) {
            if (r.getReviewId().equals(reviewId)) return r;
        }
        return null;
    }

    public List<Review> getReviewsByToyId(String toyId) throws IOException {
        List<Review> result = new ArrayList<>();
        for (Review r : getAllReviews()) {
            if (r.getToyId().equals(toyId)) result.add(r);
        }
        return result;
    }

    public boolean updateReview(String reviewId, int newRating, String newComment) throws IOException {
        List<Review> reviews = getAllReviews();
        boolean found = false;
        for (Review r : reviews) {
            if (r.getReviewId().equals(reviewId)) {
                r.setRating(newRating);
                r.setComment(newComment);
                found = true;
            }
        }
        if (found) saveAll(reviews);
        return found;
    }

    public boolean updateStatus(String reviewId, ReviewStatus newStatus) throws IOException {
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

    public boolean deleteReview(String reviewId) throws IOException {
        List<Review> reviews = getAllReviews();
        boolean removed = reviews.removeIf(r -> r.getReviewId().equals(reviewId));
        if (removed) saveAll(reviews);
        return removed;
    }

    private void saveAll(List<Review> reviews) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Review r : reviews) {
                bw.write(r.toFileString());
                bw.newLine();
            }
        }
    }

    public String generateId() throws IOException {
        int count = getAllReviews().size() + 1;
        return "REV" + String.format("%03d", count);
    }
}