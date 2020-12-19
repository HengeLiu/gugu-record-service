package com.nutrition.nutritionservice.mapper;

public interface ModelBasicMetabolicRateMapper {

    double selectBmrByAgeGender(int age, int gender);
}