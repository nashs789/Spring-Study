//package hellojpa;
//
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Product {
//
//    @Id @GeneratedValue
//    private Long id;
//
//    private String name;
//
//    @OneToMany(mappedBy = "product")
//    private List<MemberProduct> memberProducts = new ArrayList<>();
//}