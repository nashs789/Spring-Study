package hellojpa.jpabook.jpashop.domain;

import hellojpa.jpabook.jpashop.domain.enums.DiliveryStatus;
import jakarta.persistence.*;

import static jakarta.persistence.FetchType.*;

@Entity
public class Delivery extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    private String city;
    private String street;
    private String zipcode;
    private DiliveryStatus stats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public DiliveryStatus getStats() {
        return stats;
    }

    public void setStats(DiliveryStatus stats) {
        this.stats = stats;
    }
}
