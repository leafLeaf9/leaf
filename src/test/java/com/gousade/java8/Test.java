package com.gousade.java8;

import cn.hutool.core.thread.NamedThreadFactory;
import com.gousade.pojo.User;
import com.gousade.utils.BigDecimalCalculator;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


/**
 * 从每个文件的第二行中提取:之后的数字
 */
@Slf4j
public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println(EmergencyOrderStatus.valueOf("ARRIVED").getClass());
        System.out.println(EmergencyOrderStatus.ARRIVED.getStatus());
        System.out.println(EmergencyOrderStatus.ARRIVED);
        System.out.println(EmergencyOrderStatus.ARRIVED.getDescription());
        System.out.println(EmergencyOrderStatus.ARRIVED.toString());
        System.out.println("-----------------------");
        Map<String, Object> map = new HashMap<>(10);
        System.out.println(map.size());
        System.out.println(Math.round(BigDecimalCalculator.divide(7, 4, 1)));
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        System.out.println(129 & 128);
        System.out.println("--------------");
        String str1 = null;
        String str2 = "assa" + str1;
        System.out.println(str1);
        System.out.println(str2);
        List<String> list = new ArrayList<>();
        list.add(new String("a"));
        List<String> list2 = list.stream().collect(Collectors.toList());
        System.out.println(list == list2);
        System.out.println(list.get(0) == list2.get(0));
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime now2 = now.plusMinutes(5);
        System.out.println(now.isBefore(now2));
        System.out.println(Thread.currentThread().getName());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(3), new NamedThreadFactory("事件状态检测线程", false));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        threadPool.execute(() -> {
            System.out.println("就这" + Thread.currentThread().getName());
            System.out.println("是是是");
        });
        threadPool.execute(new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("测试");
        }, "测试线程"));
        System.out.println(1);
        threadPool.shutdown();
		/*threadPool.execute(new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
			System.out.println("测试1");
		},"测试线程1"));*/
        ThreadLocal<ZonedDateTime> sThreadLocal = new ThreadLocal<>();
        ZonedDateTime zonedDateTime = sThreadLocal.get();

        User user1 = User.builder().id("user1").build();
        List<User> list1 = new ArrayList<>();
        list1.add(user1);
        List<User> list3 = new ArrayList<>();
        list3.add(user1);
        System.out.println(list1);
        System.out.println(list3);
        user1.setId("newuser1");
        System.out.println(list1);
        System.out.println(list3);
        List<User> collect = list1.stream().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        System.out.println(collect);
        List<String> list4 = list1.stream().map(User::getId).collect(Collectors.toList());
        String collect1 = list1.stream().map(User::getId).collect(Collectors.joining(","));
        String collect2 = String.join(",", list4);
        String collect3 = list4.stream().collect(Collectors.joining(","));
        System.out.println("-----------------------------");
        System.out.println("完成测试");
    }

    /**
     * 获取某行的冒号之后的数字
     */
    static void readLineVarFile(String fileName, int lineNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //使用缓冲区的方法将数据读入到缓冲区中
        String line = reader.readLine(); //定义行数
        int num = 0;
        while (line != null)    //当行数不为空时，输出该行内容及字符数
        {
            if (lineNumber == ++num) {
                int index = line.indexOf(":");
//				System.out.println("第" + lineNumber + "行数字内容为->" + line.substring(index+1).trim());
                System.out.println(line.substring(index + 1).trim());
            }
            line = reader.readLine();
        }
        reader.close();
    }
}
