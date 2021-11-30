package com.gousade.java8;

import cn.hutool.core.thread.NamedThreadFactory;
import com.gousade.pojo.User;
import com.gousade.test.MyInterface;
import com.gousade.utils.BigDecimalCalculator;
import com.gousade.utils.Java8DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public class Tests {

    /*private static final long testVarOffset;

    static {
        try {
            testVarOffset = getUnsafe().objectFieldOffset
                    (Tests.class.getDeclaredField("state"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private volatile int state;*/

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
        list.add("a");
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

        String command = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<reply method=\"device\" error=\"success\"></reply><break/>";
        byte[] bytes = command.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));
        String receiveData = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(receiveData);

        TestInteger testInteger = new TestInteger();
        TestInt testInt = new TestInt();
        testInteger.setDeathAmount(null);
//        testInt.setDeathAmount(testInteger.getDeathAmount());
//        BeanUtils.copyProperties(testInteger, testInt);
        System.out.println("int" + testInt);

        TestInteger testInteger2 = new TestInteger();
        TestInt testInt2 = new TestInt();
        testInt2.setDeathAmount(4);
        BeanUtils.copyProperties(testInt2, testInteger2);
        System.out.println("integer" + testInteger2);
        ServiceLoader<MyInterface> loads = ServiceLoader.load(MyInterface.class);
        String url = "http://192.168.12.64:7005/SubCenterMonitor/";
        URL uri = new URL(url);
        System.out.println(uri.getHost());
    }

    @Test
    public void testDate() {
        LocalDate localDate = LocalDate.now().minusYears(1);
        System.out.println(localDate);
        LocalDate firstDayOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());
        System.out.println(firstDayOfYear);
        ZonedDateTime zonedStartTime = Java8DateUtil.dateTimeStrToZonedDateTime("2021-03-06 13:05:20")
                .withHour(0).withMinute(0).withSecond(0);
        System.out.println(zonedStartTime.with(TemporalAdjusters.firstDayOfYear()));
        System.out.println(zonedStartTime.with(TemporalAdjusters.firstDayOfMonth()));
        ZonedDateTime zonedEndTime = zonedStartTime.with(TemporalAdjusters.lastDayOfYear());
        System.out.println(zonedEndTime);
    }

    @Test
    public void testChronoUnit() {
        System.out.println(ChronoUnit.MINUTES.between(ZonedDateTime.now().minusDays(1), ZonedDateTime.now()));
        System.out.println(ChronoUnit.MINUTES.between(ZonedDateTime.now().plusDays(1), ZonedDateTime.now()));
    }

    @Test
    public void testBigDecimalScale() {
        double stakeNum = 1172.384615;
        System.out.println(BigDecimal.valueOf(stakeNum));
        System.out.println(BigDecimal.valueOf(stakeNum).doubleValue());
        System.out.println(BigDecimal.valueOf(stakeNum).setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    /*public static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException("获取unsafe出错。");
        }
    }

    @Test
    public void unsafeTest() throws InterruptedException {

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = getUnsafe().compareAndSwapInt(this, testVarOffset, 0, 1);
            System.out.println(b);
            System.out.println("第一个线程 结果为 " + state);
        }).start();
        new Thread(() -> {
            boolean b = getUnsafe().compareAndSwapInt(this, testVarOffset, 0, 2);
            System.out.println(b);
            System.out.println("第二个线程 结果为 " + state);
        }).start();
        Thread.sleep(3000);
    }*/

    public static Child generateChild() {
        return new Child();
    }

    /**
     * 获取某行的冒号之后的数字
     */
    static void readLineVarFile(String fileName, int lineNumber) throws IOException {
        //使用缓冲区的方法将数据读入到缓冲区中
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
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
