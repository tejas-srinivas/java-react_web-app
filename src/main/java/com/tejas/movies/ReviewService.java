package com.tejas.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    // Template is used to make a dynamic query
    // First we need to create a review
    // We have to perform Update Query to insert the review body into the reviewId array
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Review> allReviews(){
        return reviewRepository.findAll();
    }

    public Optional<Review> singleReview(ObjectId id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(String reviewBody, String imdbID){
        //This query will be inserted in reviews collections
        Review review = reviewRepository.insert(new Review(reviewBody));

        //This query will insert the object_id of the reviews collection into movies collection
        //Matching method is used to compare the database imdbId with the above imdbId
        //If match is successful then perform update operation by pushing the reviewBody
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbID").is(imdbID))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }

}
