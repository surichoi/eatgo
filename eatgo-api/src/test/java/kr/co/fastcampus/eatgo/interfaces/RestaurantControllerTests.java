package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;


    //Mock 사용하여 가짜객채 만들어주기
    @MockBean
    private RestaurantService restaurantService;

    //repository 의존성 주입
//    @SpyBean(RestaurantService.class)
//    private RestaurantService restaurantService;
//
//    @SpyBean(RestaurantRepositoryImpl.class)
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .name("Bob zip")
                .address("Seoul")
                .id(1004L)
                .build()
        );


        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform( get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ));
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = Restaurant.builder()
                .name("Bob zip")
                .address("Seoul")
                .id(1004L)
                .build();

        MenuItem menuItem = MenuItem.builder()
                                .name("Kimchi")
                                .build();
        restaurant1.setMenuItems(Arrays.asList(menuItem));

        Restaurant restaurant2 = Restaurant.builder()
                .name("Cyber food")
                .address("Seoul")
                .id(2020L)
                .build();

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform( get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")));

        mvc.perform( get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\"")
                ))
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ));
    }


    @Test
    public void create() throws Exception {

        //Restaurant restaurant = new Restaurant(1234L, "BeRyong", "Busan");
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);

            return Restaurant.builder()
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .id(1234L)
                    .build();
        });
        //갑자기 어디서 왜 튀어나온건지 모르겟음

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\", \"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}")); //Empty 구현

        verify(restaurantService).addRestaurant(any());
    }


    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"JOKER Bar\", \"address\":\"Busan\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "JOKER Bar", "Busan");

    }

}