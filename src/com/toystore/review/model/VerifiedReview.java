package com.toystore.review.model;

// Inheritance
public class VerifiedReview extends Review {

    private String purchaseId;

    public VerifiedReview(String reviewId, String userId, String toyId,
                          int rating, String comment, String date,
                          ReviewStatus status, String purchaseId) {

        super(reviewId, userId, toyId, rating, comment, date, status);
        this.purchaseId = purchaseId;
    }

    public String getPurchaseId() { return purchaseId; }
    public void setPurchaseId(String purchaseId) { this.purchaseId = purchaseId; }

    // REAL polymorphism
    @Override
    public String display() {
        return "[VERIFIED PURCHASE] " + getComment();
    }

    // Kept for clarity but now secondary
    public String getReviewType() {
        return "Verified Purchase";
    }
}