package com.nutrition.nutritionservice.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ModelBasicMetabolicRateDao {

    double selectBmrByAgeGender(int age, int gender);
}