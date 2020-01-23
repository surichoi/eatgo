package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTests {

    //객체생성 테스트
    @Test
    public void creation() {
        //Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
        assertThat(restaurant.getId(), is(1004L));
    }

    //Info
    @Test
    public void information() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        assertThat(restaurant.getInformation(), is("Bob zip in Seoul")) ;
    }
}