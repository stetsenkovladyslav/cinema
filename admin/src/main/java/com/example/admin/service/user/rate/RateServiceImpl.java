package com.example.admin.service.user.rate;

import com.example.admin.exception.InvalidRatingValueException;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {
    @Override
    public double calculateNewRatingValue(long currentNumberOfVotes, double currentRating, int newRatingValue) {
        return (currentRating * currentNumberOfVotes + newRatingValue) / (currentNumberOfVotes + 1);
    }

    @Override
    public void validateRating(int rating) throws InvalidRatingValueException {
        if (rating < 1 || rating > 10) {
            throw new InvalidRatingValueException();
        }
    }
}
