package hello.hellospring.practice;

import org.junit.jupiter.api.Test;

import java.util.function.*;

public class lambdaPractice
{
    int innerfield = 20;

    @Test
    public void 매개변수X리턴값X()
    {
        interface1 i1 = () -> System.out.println(this.innerfield);
        i1.method();
    }

    @Test
    public void 매개변수O리턴값X()
    {
        interface2 i2 = (a) -> System.out.println(a);

        i2.method(56);
    }

    @Test
    public void 매개변수O리턴값O()
    {
        interface3 i3 = (a, b) -> a + b;

        int result = i3.method(56, 76);
        System.out.println(result);
    }

    public interface interface1
    {
        void method();
    }

    public interface interface2
    {
        void method(int x);
    }

    public interface interface3
    {
        int method(int x, int y);
    }

    @Test
    public void 함수적인터페이스()
    {
        Consumer<String> cons = (s) -> System.out.println(s);
        cons.accept("test");

        BiConsumer<String, Integer> bicons = (s, i) -> System.out.println(i+s);
        bicons.accept("테스트",  1);

        IntBinaryOperator operator = Math::max;
        int res = operator.applyAsInt(1, 3);
        System.out.println(res);

        Supplier<String> supp = () -> "supp테스트";
        String s = supp.get();
        System.out.println(s);

        testDTO dto = new testDTO();
        dto.setName("김성욱");

        Function<testDTO, String> func1 = t -> t.getName();

        String result = func1.apply(dto);
        System.out.println(result);

        Predicate<testDTO> predicate = t -> t.getName().equals("김성욱");
        boolean result2 = predicate.test(dto);

        Predicate<String> predicate1 = Predicate.isEqual(null);
        System.out.println(predicate1.test(null));
        System.out.println(result2);

        Calc calc = new Calc();
        IntBinaryOperator operator1;
        operator1 = calc::sum;
        System.out.println(operator1.applyAsInt(3,30));
    }

    static class testDTO
    {
        private String name;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
    }

    static class Calc
    {
        public int sum(int x, int y)
        {
            return x + y;
        }
    }
}
