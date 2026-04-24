package com.toystore.review.model;

public class Review {

    // Encapsulation
    private String reviewId;
    private String userId;
    private String toyId;
    private int rating;
    private String comment;
    private String date;

    // Replaced String with controlled values
    private ReviewStatus status;

    // Enum added for better type safety (instead of raw String)
    public enum ReviewStatus {
        PENDING, APPROVED, REJECTED
    }

    public Review(String reviewId, String userId, String toyId,
                  int rating, String comment, String date, ReviewStatus status) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.toyId = toyId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.status = status;
    }

    // Getters
    public String getReviewId() { return reviewId; }
    public String getUserId() { return userId; }
    public String getToyId() { return toyId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public String getDate() { return date; }
    public ReviewStatus getStatus() { return status; }

    // Setters (consistent with enum now)
    public void setRating(int rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
    public void setStatus(ReviewStatus status) { this.status = status; }

    // Added abstraction point (base method for polymorphism)
    public String display() {
        return "Review: " + comment;
    }

    // File format method
    public String toFileString() {
        return reviewId + "|" + userId + "|" + toyId + "|" +
                rating + "|" + comment + "|" + date + "|" + status;
    }

    // Added validation to prevent crashes
    public static Review fromFileString(String line) {
        String[] parts = line.split("\\|");

        if (parts.length < 7) {
            throw new IllegalArgumentException("Invalid review data format");
        }

        return new Review(
                parts[0],
                parts[1],
                parts[2],
                Integer.parseInt(parts[3]),
                parts[4],
                parts[5],
                ReviewStatus.valueOf(parts[6]) //convert String → enum
        );
    }
}