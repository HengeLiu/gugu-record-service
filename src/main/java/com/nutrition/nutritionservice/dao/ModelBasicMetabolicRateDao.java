package com.nutrition.nutritionservice.dao;

public interface ModelBasicMetabolicRateDao {

    double selectBmrByAgeGender(int age, int gender);
}