package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    //null을 처리하지 않고 Restaurant가 있냐 없냐로 처리
    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);
}
