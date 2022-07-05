package com.yc.javabasic.collection;

import com.yc.javabasic.bean.Student;
import com.yc.javabasic.bean.Student2;
import com.yc.javabasic.bean.StudentScore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: josan_tang
 * @create_date: 2022/3/24 13:22
 * @desc: java 8集合的相关操作
 * @version:
 */
public class StreamJava8 {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student();
        student1.setId("2");
        studentList.add(student1);
        Student student2 = new Student();
        student2.setId("1");
        studentList.add(student2);
        //把属性筛选出来形成List<String>
        System.out.println(filterBeanAttr(studentList));

        //把List<Student>转成List<Student2>
        System.out.println(listBeanToOtherListBean(studentList));

        //筛选特定条件的bean,形成List<Bean>
        System.out.println(filterBean(studentList));

        List<String> list1 = new ArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");

        List<String> list2 = new ArrayList();
        list2.add("2");
        list2.add("4");

        //得到list1、2的差值
        System.out.println(getDifferenceStringCollection(list1, list2));

        List<StudentScore> scoreList = new ArrayList<>();
        StudentScore studentScore = new StudentScore();
        studentScore.setStudentId("1");
        scoreList.add(studentScore);
        StudentScore studentScore2 = new StudentScore();
        studentScore2.setStudentId("3");
        scoreList.add(studentScore2);
        //得到list1、list2的差值，两个list为两个bean
        System.out.println(getDifferenceBeanCollection(studentList, scoreList));

        List<Student> studentList2 = new ArrayList<>();
        Student student21 = new Student();
        student21.setId("2");
        studentList2.add(student21);
        Student student22 = new Student();
        student22.setId("1");
        studentList2.add(student22);
        System.out.println(updateBeanList(studentList2));


        List<String> joinList = new ArrayList<>();
        joinList.add("josan1");
        joinList.add("josan2");
        joinList.add("josan3");

        System.out.println("joining str:" + joining(joinList));
    }

    /**
     * 通过$拼接List中的数据
     * @param strList
     * @return
     */
    public static String joining(List<String> strList) {
        return String.join("$", strList);
        //return strList.stream().collect(Collectors.joining("$"));
    }

    /**
     * 修改list<bean>属性
     * @param studentList
     * @return
     */
    public static List<Student> updateBeanList(List<Student> studentList) {
        studentList.forEach( student -> {
            student.setName("新值");
        });
        return studentList;
    }

    /**
     * 真对String, 判断list1中有，但是list2中没有的
     * @param studentList
     * @param scoreList
     * @return
     */
    public static List<Student> getDifferenceBeanCollection(List<Student> studentList, List<StudentScore> scoreList) {
        List<String> scoreSutentIdList = scoreList.stream().map(StudentScore::getStudentId).collect(Collectors.toList());
        return studentList.stream().filter(s -> !scoreSutentIdList.contains(s.getId())).collect(Collectors.toList());
    }


    /**
     * 真对String, 判断list1中有，但是list2中没有的
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getDifferenceStringCollection(List<String> list1, List<String> list2) {
        return list1.stream().filter(s -> !list2.contains(s) ).collect(Collectors.toList());
    }

    /**
     * 过滤出List<Bean>中的某个属性，返回特定属性满足条件，形成List<Bean>
     * @param studentList
     * @return
     */
    public static List<Student> filterBean(List<Student> studentList) {
        List<Student> idNotNullStudentList =  studentList.stream().filter( student -> student.getId() != null).collect(Collectors.toList());
        return idNotNullStudentList;
    }

    /**
     * 过滤出List<Bean>中的某个属性，形成List<String>
     * @param studentList
     * @return
     */
    public static List<String> filterBeanAttr(List<Student> studentList) {
        List<String> idList =  studentList.stream().map(Student::getId).collect(Collectors.toList());
        return idList;
    }

    /**
     * 从List<Student>转到List<Student2>
     * @param studentList
     * @return
     */
    public static List<Student2> listBeanToOtherListBean(List<Student> studentList) {
        List<Student2> student2List =  studentList.stream().map(student -> {
            Student2 student2 = new Student2();
            student2.setId(student.getId());
            student2.setId(student.getId());
            return student2;
        }).collect(Collectors.toList());
        return student2List;
    }


}
