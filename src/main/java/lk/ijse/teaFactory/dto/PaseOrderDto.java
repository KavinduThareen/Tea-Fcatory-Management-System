package lk.ijse.teaFactory.dto;

import lk.ijse.teaFactory.dto.tm.CusOrderTm;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter

public class PaseOrderDto {
    private static String id;
    private String cId;
    private String catagary;
    private String weigth;
    private String date;
    private String descreption;
    private double payment;
    private String isCompleted;
    private List<CusOrderTm> cartTmList = new ArrayList<>();

    public PaseOrderDto(String id, String cId, String catagary, String weigth, String  date, String descreption, Double payment,String isCompleted, List<CusOrderTm> cartTmList) {

        this.id = id;
        this.cId = cId;
        this.catagary = catagary;
        this.weigth = weigth;
        this.date = date;
        this.descreption = descreption;
        this.payment = payment;
        this.isCompleted=isCompleted;
        this.cartTmList = cartTmList;
    }


    public String getid() {
        return id;
    }
/*
    public List<CusOrderTm> setCartTmList() {
        return cartTmList;
    }

 */
}
