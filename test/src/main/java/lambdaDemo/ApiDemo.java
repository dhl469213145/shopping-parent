package lambdaDemo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ApiDemo {

    public static List<String> initStrList() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        return list;
    }

    public static List<Integer> initIntList() {
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(333);
        list.add(231);
        list.add(435);
        list.add(656);
        return list;
    }

    /**
     * 生成stream
     * @param type
     */

    public void generateStream(String type) {
        List<String> strList = initStrList();

        // list
        if("list".equals(type)) {
            Stream<String> listStream = strList.stream();
            Stream<String> listStrStream = strList.parallelStream();
        }

        // array
        if("array".equals(type)) {
            Stream<String> arrayStream = Arrays.stream(new String[10]);
        }

        // streamof
        if("streamof".equals(type)) {
            Stream<String> stream = Stream.of("s1", "s2", "s3");
        }

        // 无限流
        if("iterate".equals(type)) {
            Stream<Integer> iterateStream = Stream.iterate(0, (x) -> x + 2);
            iterateStream.limit(10).forEach(System.out::println);
        }

        // generate
        if("generate".equals(type)) {
            Stream<Double> generateStream = Stream.generate(() -> Math.random());
            generateStream.limit(10).forEach(System.out::println);
        }

    }

    /**
     * 中间操作
     */
    public void showIntermediateAcitonn() {
//        String[] words = new String[]{"Hello","World"};
//        List<String> a = Arrays.stream(words)
//                .map(word -> word.split(""))
//                .flatMap(Arrays::stream)
//                .distinct()
//                .collect(toList());
//        a.forEach(System.out::print);

        String[] words = new String[]{"Hello","World"};

        List<String> strList = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
//                .skip(1)                      // 跳过
//                .sorted()                     // 自然排序
//                .sorted(Integer::compareTo)   // 自定义排序
                .collect(toList());

        strList.forEach(System.out::println);
//        strList.stream().map()
    }

    /**
     * 终止操作
     */
    public void doStopAction() {
        Integer[] ints = new Integer[]{123, 232, 4242, 231, 43};

        Stream<Integer> intStream = Arrays.stream(ints);

        System.out.println(intStream.allMatch((x) -> x == 123));        // 检查是否匹配所有元素
        intStream = Arrays.stream(ints);
        System.out.println(intStream.anyMatch((x) -> x == 123));        // 检查是否至少匹配一个元素
        intStream = Arrays.stream(ints);
        System.out.println(intStream.noneMatch((x) -> x == 222));       // 检查是否没有匹配所有元素
        intStream = Arrays.stream(ints);
        System.out.println(intStream.findFirst());
        intStream = Arrays.stream(ints);
        System.out.println(intStream.findAny());                        // 返回当前流中的任意一个元素
        intStream = Arrays.stream(ints);
        System.out.println(intStream.count());                          // 返回流中元素的总个数
        intStream = Arrays.stream(ints);
        System.out.println(intStream.max(Integer::compareTo));          // 返回流中最大值
        intStream = Arrays.stream(ints);
        System.out.println(intStream.min(Integer::compareTo));          // 返回流中最小值


        // 归约
        intStream = Arrays.stream(ints);
        Integer reduce = intStream.map(s -> (s + 1)).reduce(0, (x, y) -> x + y);//归约：0为第一个参数x的默认值，x是计算后的返回值，y为每一项的值。
        System.out.println(reduce);
        intStream = Arrays.stream(ints);
        Optional<Integer> reduce1 = intStream.map(s -> (s + 1)).reduce((x, y) -> x + y);       // x是计算后的返回值，默认为第一项的值，y为其后每一项的值。
        System.out.println(reduce1);


        //转集合
        List<String> list = initStrList();
        Set<String> collect = list.stream()
                .collect(Collectors.toSet());

        List<String> collect2 = list.stream()
                .collect(Collectors.toList());

        HashSet<String> collect1 = list.stream()
                .collect(Collectors.toCollection(HashSet::new));


        //分组 {group=[444, 555, 666, 777, 555]}
        List<Integer> intList = initIntList();
        Map<String, List<Integer>> collect3 = intList.stream()
                .collect(Collectors.groupingBy((x) -> "group"));//将返回值相同的进行分组
        System.out.println(collect3);

        //多级分组 {group={777=[777], 666=[666], 555=[555, 555], 444=[444]}}
        Map<String, Map<Integer, List<Integer>>> collect4 = intList.stream()
                .collect(Collectors.groupingBy((x) -> "group", Collectors.groupingBy((x) -> x)));
        System.out.println(collect4);

        //分区 {false=[444], true=[555, 666, 777, 555]}
        Map<Boolean, List<Integer>> collect5 = intList.stream()
                .collect(Collectors.partitioningBy((x) -> x > 500));
        System.out.println(collect5);

        //汇总
        DoubleSummaryStatistics collect6 = intList.stream()
                .collect(Collectors.summarizingDouble((x) -> x));
        System.out.println(collect6.getMax());
        System.out.println(collect6.getCount());

        //拼接 444,555,666,777,555
        String collect7 = intList.stream()
                .map(s -> s.toString())
                .collect(Collectors.joining(","));
        System.out.println(collect7);
    }

    public static void main(String[] args) {
        ApiDemo apiDemo = new ApiDemo();
        apiDemo.generateStream("iterate");
        System.out.println("------------------------------");
        apiDemo.generateStream("generate");
        System.out.println("------------------------------");
        apiDemo.showIntermediateAcitonn();
        System.out.println("------------------------------");
        apiDemo.doStopAction();
    }





//    /**
//     * 多条件去重
//     * @param list
//     */
//    public static void order() {
//        list.stream().collect(Collectors.collectingAndThen(
//                Collectors.toCollection(() -> new TreeSet<>(
//                        Comparator.comparing(o -> o.getAge() + ";" + o.getName()))), ArrayList::new)).forEach(u -> println(u));
//    }
//
//    public static void group() {
//        Map<Integer, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getAge));
//        System.out.println(collect);
//    }
//
//    /**
//     * filter过滤
//     * @param list
//     */
//    public static void filterAge() {
//        list.stream().filter(u -> u.getAge() == 10).forEach(u -> println(u));
//    }
//
//    /**
//     * sorted排序
//     */
//    public static void stord() {
//        list.stream().sorted(Comparator.comparing(u-> u.getAge())).forEach(u -> println(u));
//
//    }
//    /**
//     * limit方法限制最多返回多少元素
//     */
//    public static void limit() {
//        list.stream().limit(2).forEach(u -> println(u));
//    }
//    /**
//     * 不要前多n个元素，n大于满足条件的元素个数就返回空的流
//     */
//    public static void skip() {
//        list.stream().skip(2).forEach(u -> println(u));
//    }
//    // 最大值 最小值
//    public static void statistics() {
//        Optional<User> min = list.stream().min(Comparator.comparing(User::getUserId));
//        println(min);
//        Optional<User> max = list.stream().max(Comparator.comparing(User::getUserId));
//        println(max);
//    }
//
//    // 统计
//    public static void summarizingInt(){
//        IntSummaryStatistics statistics = list.stream().collect(Collectors.summarizingInt(User::getAge));
//        double average = statistics.getAverage();
//        long count = statistics.getCount();
//        int max = statistics.getMax();
//        int min = statistics.getMin();
//        long sum = statistics.getSum();
//        println(average);
//        println(count);
//        println(min);
//        println(sum);
//        println(max);
//
//    }
//    /**
//     * 转set
//     */
//    public static void toSet() {
//        Set<User> collect = list.stream().collect(Collectors.toSet());
//        Iterator<User> iterator = collect.iterator();
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next().getUserId());
//        }
//    }
//
//    /**
//     * 转map
//     */
//    public static void toMap() {
//        Map<Integer, User> collect = list.stream().collect(Collectors.toMap(User::getUserId, u -> u));
//        for (Integer in : collect.keySet()) {
//            User u = collect.get(in);//得到每个key多对用value的值
//            println(u);
//        }
//    }
//    /**
//     *map
//     */
//    public static void map() {
//        list.stream().map(User::getUserId).forEach(userId -> println(userId));
//        list.stream().mapToInt(User::getAge).forEach(userId -> println(userId));
//        list.stream().mapToDouble(User::getUserId).forEach(userId -> println(userId));
//        list.stream().mapToLong(User::getUserId).forEach(userId -> println(userId));
//    }
//
//    /**
//     * 查找与匹配
//     * allMatch方法与anyMatch差不多，表示所有的元素都满足才返回true。noneMatch方法表示没有元素满足
//     */
//    public static void anyMatch() {
//        boolean anyMatch = list.stream().anyMatch(u -> u.getAge() == 100);
//        boolean allMatch = list.stream().allMatch(u -> u.getUserId() == 10);
//        boolean noneMatch = list.stream().noneMatch(u -> u.getUserId() == 10);
//        println(anyMatch);
//        println(allMatch);
//        println(noneMatch);
//    }
//
//    /**
//     * reduce操作
//     */
//    public static void reduce() {
//        Optional<Integer> sum = list.stream().map(User::getAge).reduce(Integer::sum);
//        Optional<Integer> max = list.stream().map(User::getAge).reduce(Integer::max);
//        Optional<Integer> min = list.stream().map(User::getAge).reduce(Integer::min);
//        println(sum);
//        println(max);
//        println(min);
//    }
    /**
     * 公共输出
     * @param c
     */
    public static void println(Object c) {
        System.out.println(c.toString());
    }


    static class User {
        private Integer userId;

        private String name;

        private Integer age;

        User(Integer userId, String name, Integer age) {
            super();
            this.userId = userId;
            this.name = name;
            this.age = age;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "[userId=" + userId + ", name=" + name + ", age=" + age + "]";
        }

    }
}
