package kr.co.fastcampus.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
//    private String regionName;
//    private String categoryName;
//    private String tagName;
    //이렇게 인스턴스가 많아져도 빌더패턴을 쓰면 많이 안고쳐도됨(생성자 추가 필요없음)
    //순서 신경 안써도 됨

    @Transient
    private List<MenuItem> menuItems;


    public String getInformation() {
        return name + " in " + address;
    }


    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);


    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
