package demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TDemo<T> {

    public T  showOne(List<T> list) {
        System.out.println(list.toString());
        return list.get(0);
    }

    private <T> T getListFirst(List<T> data){
        if(data == null || data.size() == 0){
            return null;
        }
        return data.get(0);
    }

    public static void main(String[] args) {
//        List<String> strl = new ArrayList<>();
//        strl.add("dd");
//        strl.add("dssd");
//
//        List<Integer> str2 = new ArrayList<>();
//        str2.add(11);
//        str2.add(22);
//
//        TDemo<String> utils = new TDemo<>();
//
//        utils.getListFirst(strl);
//        utils.showOne(strl);
//        TDemo<Integer> utilss = new TDemo<>();
//        utilss.showOne(str2);

        String a = "aa";
        Map map = new HashMap<>();
        map.put(a, "dfds");
        map.put(new String(a), "ddd");
        System.out.println(map.get(a));
        map.entrySet().stream().forEach(e->{
            System.out.println(e);
        });
        System.out.println(a);

    }

    private AtomicInteger sourceNum = new AtomicInteger();
    private int incrementAndGet(int modulo) {
        for(;;) {
            int current = sourceNum.get();
            int next = (current + 1) % modulo;
            if(sourceNum.compareAndSet(current, next) && current < modulo)  return current;

        }
    }
}
