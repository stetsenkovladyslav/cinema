package com.example.user.service.rate;

import com.example.root.exception.InvalidRatingValueException;

public interface RateService {

    double calculateNewRatingValue(long currentNumberOfVotes, double currentRating, int newRatingValue);

    void validateRating(int rating) throws InvalidRatingValueException;
}
