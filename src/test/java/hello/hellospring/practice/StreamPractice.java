package hello.hellospring.practice;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice
{
    @Test
    void test()
    {
        List<String> list = Arrays.asList("홍길동", "신용권", "감자바");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
    }

    @Test
    void test1()
    {
        List<Student> list = Arrays.asList(new Student("홍길동", 90), new Student("신용권", 92));

        Stream<Student> stream = list.stream();
        stream.forEach(s -> {
            System.out.println(s.getName() + "-" + s.getScore());
        });
    }

    @Test
    void test2()
    {
        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 10),
                new Student("신용권", 20),
                new Student("유미선", 30)
        );

        double avg = studentList.stream().mapToInt(Student::getScore).average().getAsDouble();
        System.out.println(avg);
    }

    @Test
    void test3()
    {
        List<Member> list = Arrays.asList(
                new Member("홍길동", Member.MALE, 30),
                new Member("김나리", Member.FEMALE, 20),
                new Member("신용권", Member.MALE, 45),
                new Member("박수미", Member.FEMALE, 27)
        );

        double ageAvg = list.stream().filter(m -> m.getSex() == Member.MALE).mapToInt(Member::getAge).average().getAsDouble();

        System.out.println(ageAvg);
    }

    @Test
    void test4()
    {
        int[] intArray = {1,2,3,4,5};
        IntStream intStream = Arrays.stream(intArray);
        intStream.boxed().forEach(System.out::println);
    }

    @Test
    void test5()
    {
        IntStream intStream = Arrays.stream(new int[]{5, 3, 2, 1, 4});
        intStream.sorted().forEach(System.out::println);

        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 10),
                new Student("신용권", 20),
                new Student("유미선", 30)
        );

        studentList.stream().mapToInt(Student::getScore).sorted().forEach(System.out::println);
        System.out.println();
        studentList.stream().sorted(Comparator.reverseOrder()).forEach(s -> System.out.println(s.getScore()));
    }

    @Test
    void test6()
    {
        List<Double> list = Arrays.asList(1.0,2.0,3.0,4.0,5.0);
        OptionalDouble optional = list.stream().mapToDouble(Double::doubleValue).average();
        if(optional.isPresent())
        {
            System.out.println(optional.getAsDouble());
        }
        else
        {
            System.out.println("0");
        }

        System.out.println(optional.orElse(0.0));

        optional.ifPresent(System.out::println);
    }

    @Test
    void test7()
    {
        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 10),
                new Student("신용권", 20),
                new Student("유미선", 30)
        );

        int sum = studentList.stream().map(Student::getScore).reduce(Integer::sum).get();
        System.out.println(sum);
    }

    @Test
    void test8()
    {
        List<Member> list = Arrays.asList(
                new Member("홍길동", Member.MALE, 30),
                new Member("김나리", Member.FEMALE, 20),
                new Member("신용권", Member.MALE, 45),
                new Member("박수미", Member.FEMALE, 27)
        );

        List<Member> list2 = list.stream().filter(s -> s.getSex() == Member.MALE).collect(Collectors.toList());
        list2.forEach(s -> System.out.println(s.getName()));

        Set<Member> femaleSet = list.stream().filter(s -> s.getSex() == Member.FEMALE).collect(Collectors.toSet());
        femaleSet.forEach(s -> System.out.println(s.getName()));
    }
}
class Student implements Comparable<Student>
{
    private String name;
    private int score;

    public Student(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    @Override
    public int compareTo(Student o)
    {
        return Integer.compare(score, o.score);
    }
}

class Member
{
    public static int MALE = 0;
    public static int FEMALE = 1;

    private String name;
    private int sex;
    private int age;

    public Member(String name, int sex, int age)
    {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public int getSex()
    {
        return sex;
    }

    public int getAge()
    {
        return age;
    }
}