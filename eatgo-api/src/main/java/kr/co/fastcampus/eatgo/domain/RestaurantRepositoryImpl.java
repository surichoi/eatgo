package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();


    public RestaurantRepositoryImpl() {
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber food", "Seoul"));

    }

    @Override
    public List<Restaurant> findAll() {

        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {

        /*for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(id)) {
                return restaurants.get(i);
            } else {
                return null;
            }
        }*/

        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        return restaurant;
    }
}
