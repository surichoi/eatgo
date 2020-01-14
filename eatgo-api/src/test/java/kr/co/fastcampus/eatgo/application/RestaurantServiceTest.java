package kr.co.fastcampus.eatgo.application;

import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantServiceTest {

    @Test
    public void getRestaurant() {
        restaurantService.getRestaurant(1004L);
    }

}