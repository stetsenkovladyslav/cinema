package com.example.admin.service.user.rate;

import com.example.admin.exception.InvalidRatingValueException;

public interface RateService {

    double calculateNewRatingValue(long currentNumberOfVotes, double currentRating, int newRatingValue);

    void validateRating(int rating) throws InvalidRatingValueException;
}
