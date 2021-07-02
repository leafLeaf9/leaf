package com.gousade.java8.lambda;

import com.gousade.entity.Gift;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

/*
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
 * 						    箭头操作符将 Lambda 表达式拆分成两部分：
 *
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 * 语法格式一：无参数，无返回值
 * 		() -> System.out.println("Hello Lambda!");
 *
 * 语法格式二：有一个参数，并且无返回值
 * 		(x) -> System.out.println(x)
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * 		x -> System.out.println(x)
 *
 * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *		Comparator<Integer> com = (x, y) -> {
 *			System.out.println("函数式接口");
 *			return Integer.compare(x, y);
 *		};
 *
 * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
 * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 * 		(Integer x, Integer y) -> Integer.compare(x, y);
 *
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 *
 * 二、Lambda 表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 * 			 可以检查是否是函数式接口
 */
/*
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
@Slf4j
public class LambdaTest {

    @Test
    public void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("normal runable");
            }
        };
        runnable.run();
        log.info("--------------");
        Runnable runnable2 = () -> log.info("lambda runable for void run()");
        runnable2.run();
    }

    @Test
    public void test2() {
        Consumer<Gift> consumer = (e) -> {
            e.setId(e.getId() + 10);
            log.info(e.toString());
        };
        consumer.accept(new Gift(1, "gift1", 0.1));
        Consumer<String> consumer2 = (e) -> log.info(e)/*log::info*/;
        consumer2.accept("lambda for void accept(T t)");
    }

    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    // Function<T, R> 函数型接口：
    @Test
    public void test21() {
        String newStr = strHandler("\t\t\t 测试字符串测试字符串   ", (str) -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("测试字符串测试字符串", (str) -> str.substring(2, 5));
        System.out.println(subStr);
        Function<String, Integer> function = (str) -> str.length();
        System.out.println(function.apply("测试字符串测试字符串"));
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            log.info("x={}, y={}", x, y);
            return x.compareTo(y);
        };
        log.info("result = {}", comparator.compare(9, 20));
        Comparator<Integer> comparator2 = (x, y) -> x.compareTo(y);
        log.info("result = {}", comparator2.compare(20, 9));
        Comparator<Integer> comparator3 = Integer::compare;
        log.info("result = {}", comparator3.compare(20, 9));
        Comparator<Integer> comparator4 = Integer::compareTo;
        log.info("result = {}", comparator4.compare(20, 9));
    }

    public Integer operation(Integer o1, Integer o2, Comparator<Integer> comparator) {
        return comparator.compare(o1, o2);
    }

    @Test
    public void test4() {
        log.info("operation * result = {}", operation(100, 200, (o1, o2) -> o1 * o2));
        log.info("operation + result = {}", operation(100, 200, (o1, o2) -> o1 + o2));
    }

}
