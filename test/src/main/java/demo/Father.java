package demo;

import lombok.Data;

@Data
public class Father {

    private String aa;

    private String version;

    public void addBaseInfo(Father father) {
        father.setAa("aaaa");
        father.setVersion("32323");
    }
}
