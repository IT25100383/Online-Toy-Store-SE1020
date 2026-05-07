package com.toystore.review.model;

// Inheritance
public class GuestReview extends Review {

    private String guestName;

    public GuestReview(String reviewId, String userId, String toyId,
                       int rating, String comment, String date,
                       ReviewStatus status, String guestName) {

        super(reviewId, userId, toyId, rating, comment, date, status);
        this.guestName = guestName;
    }

    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }

    // REAL polymorphism (different behavior)
    @Override
    public String display() {
        return "[GUEST: " + guestName + "] " + getComment();
    }

    public String getReviewType() {
        return "Guest Review";
    }
}
