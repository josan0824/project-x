package com.yc.javabasic.collection;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: josan_tang
 * @create_date: 2022/3/24 13:22
 * @desc: java 8集合的相关操作
 * @version:
 */
public class StreamTest {
    public static void main(String[] args) {
        int nums = 100;
        long end1 = System.currentTimeMillis();
        for (int i = 0 ;i < 10; i++) {
            Map<String, List<User>> result2 = stream(mockUserList(i+1));
            long end3 = System.currentTimeMillis();
            System.out.println("stream串行耗时:" + i +":" + (end3 - end1));
        }
        long end2 = System.currentTimeMillis();
        System.out.println("stream串行耗时:" + (end2 - end1));
    }
    private static List<User> mockUserList(int nums) {

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            User user = new User();
            user.setHeight(i);
            if (i % 2 == 0) {
                user.setSex("男");
            } else {
                user.setSex("女");
            }
            userList.add(user);
        }
        return userList;
    }

    /**
     * 数量较少
     */
    private static void test1000() {
        int nums = 100;
        // 构造数据
        List<User> userList = mockUserList(nums);
        // 2.测试时间
        long start = System.currentTimeMillis();
        Map<String, List<User>> result1 = common(userList);
        long end1 = System.currentTimeMillis();
        System.out.println(nums + "个，普通方式耗时:" + (end1 - start));
        Map<String, List<User>> result2 = stream(userList);
        long end2 = System.currentTimeMillis();
        System.out.println(nums + "个，stream串行耗时:" + (end2 - end1));
        Map<String, List<User>> result3 = parallelStream(userList);
        long end3 = System.currentTimeMillis();
        System.out.println(nums + "个，stream并行耗时:" + (end3 - end2));
    }

    /**
     * 常用的
     * @param userList
     */
    private static Map<String, List<User>> common(List<User> userList) {
        Map<String, List<User>> stuMap = new HashMap<String, List<User>>();
        for (User user: userList) {
            if (user.getHeight() > 160) { //如果身高大于160
                if (stuMap.get(user.getSex()) == null) { //该性别还没分类
                    List<User> list = new ArrayList<User>(); //新建列表
                    list.add(user);//放进去列表
                    stuMap.put(user.getSex(), list);//将列表放到map中
                } else { //该性别分类已存在
                    stuMap.get(user.getSex()).add(user);//该性别分类已存在，则直接放进去即可
                }
            }
        }
        return stuMap;
    }
    /**
     * stream串行
     * @param userList
     */
    private static Map<String, List<User>> stream(List<User> userList) {
        return userList.stream().filter(o -> o.getHeight() > 160).collect(Collectors.groupingBy(User::getSex));
    }
    /**
     * stream并行
     * @param userList
     */
    private static Map<String, List<User>> parallelStream(List<User> userList) {
        return userList.parallelStream().filter(o -> o.getHeight() > 160).collect(Collectors.groupingBy(User::getSex));
    }
}
