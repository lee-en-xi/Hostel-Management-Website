package classFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Review{
    private static final FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
    private String StoreID;
    private UUID reviewID;
    private String review;
    private String customerID;
    private String itemID;
    private String rating;
    private String filePath = fileHandlerImp.createTextFile("review.txt");

    public Review()
    {

    }
    
    public List<Review> readReviews(String storeID) 
    {
        List<String[]> data = fileHandlerImp.readWholeData(filePath);
        List<Review> reviews = new ArrayList<>(); 

        for (String[] row : data) {
            String storeIDFromFile = row[0];
            if (storeID.equals(storeIDFromFile)) {
                // Extract review details
                UUID reviewID = UUID.fromString(row[1]); // Convert the string to UUID
                String itemID = row[2];
                String customerID = row[3];
                String reviewDes = row[4];
                String rating = row[5];

                // Create and populate a Review object
                Review review = new Review();
                review.setStoreID(storeID);
                review.setReviewID(reviewID);
                review.setItemID(itemID);
                review.setCustomerID(customerID);
                review.setReviewDes(reviewDes);
                review.setRating(rating);

                // Add the review to your list of reviews
                reviews.add(review);
            }
        }
        return reviews;
    }

    public static List<String[]> readItemReview(String itemId, String storeId) {
        String filePath = "src/textFile/review.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        List<String[]> storeReviewList = fileHandlerImp.readKeyLinesData(filePath, storeId);
        List<String[]> itemReviewList = new ArrayList<>();

        String[] reviewData = {"CustomerID", "Review", "Rating"};
        itemReviewList.add(0, reviewData);

        for(String[] dataArr : storeReviewList) {
            if (dataArr[2].equals(itemId)) {
                String[] data = {dataArr[3], dataArr[4], dataArr[5]};
                itemReviewList.add(data);
            }
        }
        //System.out.println(Arrays.toString(itemReviewList.get(1)));
        return itemReviewList;
    }
    
    public boolean writeReviews(String storeID, String itemID, String customerID, String reviewDes, String rating)
    {
        UUID newReviewID = generateUUID();
        String reviewData = String.join(":::", storeID, newReviewID.toString(), itemID, customerID, reviewDes, rating);
        boolean Status = fileHandlerImp.writeFileNewLine(filePath, reviewData);
        if (Status) {
            return true;
        }
        return false;
    }
    
    public UUID generateUUID() {
    Set<UUID> existingIDs = new HashSet<>();
    List<String> lines = fileHandlerImp.readWholeFile(filePath);
    for (String line : lines) {
      try {
        UUID uuid = UUID.fromString(line.trim());
        existingIDs.add(uuid);
      } catch (IllegalArgumentException e) {
         
      }
    }
    UUID newUUID;
    do {newUUID = UUID.randomUUID();} 
    while (existingIDs.contains(newUUID));
    setReviewID(newUUID);
    return getReviewID();
  }
   
    public void setReviewID(UUID reviewID) {this.reviewID = reviewID;}

    public void setReviewDes(String reviewDes) {this.review = reviewDes;}

    public void setCustomerID(String customerID) {this.customerID = customerID;}

    public void setItemID(String itemID) {this.itemID = itemID;}

    public void setRating(String rating) {this.rating = rating;}
    
    public void setStoreID(String StoreID) {this.StoreID = StoreID;}

    public UUID getReviewID() {return reviewID;}

    public String getReviewDes() {return review;}

    public String getCustomerID() {return customerID;}

    public String getItemID() {return itemID;}

    public String getRating() {return rating;}

    public String getStoreID() {return StoreID;}
}