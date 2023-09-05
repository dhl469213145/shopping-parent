package demo;

import lombok.Data;

@Data
public class Son extends Father {

    private String name;

    private Long id;


    public static void main(String[] args) {
        Father a = new Son();
        a.setVersion("ffff");
        a.addBaseInfo(a);
        System.out.println(a.toString());
    }
}
