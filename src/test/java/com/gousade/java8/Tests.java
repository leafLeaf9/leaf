package com.gousade.java8;

import cn.hutool.core.thread.NamedThreadFactory;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.gousade.annotation.OperationRecord;
import com.gousade.pojo.SubUserImpl;
import com.gousade.pojo.User;
import com.gousade.test.MyInterface;
import com.gousade.util.BigDecimalCalculator;
import com.gousade.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
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
        ZonedDateTime zonedStartTime = DateUtils.dateTimeStrToZonedDateTime("2021-03-06 13:05:20")
                .withHour(0).withMinute(0).withSecond(0);
        System.out.println(zonedStartTime.with(TemporalAdjusters.firstDayOfYear()));
        System.out.println(zonedStartTime.with(TemporalAdjusters.firstDayOfMonth()));
        ZonedDateTime zonedEndTime = zonedStartTime.with(TemporalAdjusters.lastDayOfYear());
        System.out.println(zonedEndTime);
    }

    @OperationRecord(operationNum = 9999, operationDescription = "'短信验证码发送'")
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

    @Test
    public void testSet() {
        Set<User> set = new TreeSet<>(Comparator.comparing(User::getId));
        set.add(User.builder().id("user211").build());
        set.add(User.builder().id("user211").build());
        set.add(User.builder().id("user211").userId("userID211").build());
        set.add(User.builder().id("user2").build());
        System.out.println(set.size());
        System.out.println(set);
    }

    @Test
    public void testSplit() {
        String str = "";
        String[] split = str.split(",");
        System.out.println(Arrays.toString(split));
        ArrayList<String> list = new ArrayList<>(Arrays.asList(str.split(",")));
        System.out.println(list);
    }

    @Test
    public void testSubClassToString() {
        SubUserImpl subUser = new SubUserImpl();
        subUser.setSub1("zez");
        System.out.println(subUser);
    }

    @Test
    public void testKey() {
        String str = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAotQDQzCJlx8xhe9Se3YcjioWPXGF68n89P+htxvfrOBm9jJ11KefIBDzN6oGUK3wk/xkAAfHxSLPIPapDH02tnQv8thz7SrnJyLhMGKJ12gLwDLt105hyxRTi3DpOd9mNq5JFF8is+xPdNhgVFSH4luOXRlBl5aVzFG5nux1gt90mrlz+q6yJTjD05jxxZsorbN46CVdfBYVCptyBWUH+jwRZNG/Ujykrp19XKuqzpO7NFDdRZ1WoK7kFYMeMCH66X45abG2+rA3vw1s8R0P+KzQ4Zlt53VIZ4gy8XwQCwNGMIG5ueOPctTjeyq4qtN1SXvvldLvCehijstw3qaSKwIDAQAB";
        System.out.println(new String(Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    public void testEmptyListGetMax() {
        List<User> list = new ArrayList<>();
        Optional<String> max = list.stream().map(User::getId).max(String::compareTo);
        System.out.println(max);
        String str = "00";
        Integer integer = Integer.valueOf(str);
        System.out.println(integer);
        System.out.println(integer % 2);
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("k", "v");
        System.out.println(map.size());
    }

    @Test
    public void testEBoxingTypeCopy() {
        TestInteger testInteger = new TestInteger();
        TestInt testInt = new TestInt();
        testInteger.setDeathAmount(3);
        Integer deathAmount = testInteger.getDeathAmount();
//        testInt.setDeathAmount(deathAmount);
        BeanUtils.copyProperties(testInteger, testInt);
        System.out.println("int" + testInt);

        TestInteger testInteger2 = new TestInteger();
        TestInt testInt2 = new TestInt();
        testInt2.setDeathAmount(4);
        BeanUtils.copyProperties(testInt2, testInteger2);
        System.out.println("integer" + testInteger2);

        System.out.println(9.845778423E7);
        System.out.println(98457784.23);
    }

    @Test
    public void testValue() {
        User user1 = User.builder().id("1").build();
        User user2 = user1;
        System.out.println(user1);
        System.out.println(user2);
        user1.setId("new 1");
        System.out.println(user1);
        System.out.println(user2);

        AtomicReference<ZonedDateTime> atomicTime1 = new AtomicReference<>(ZonedDateTime.now());
        ZonedDateTime zonedDateTime2 = atomicTime1.get();
        System.out.println(atomicTime1);
        System.out.println(zonedDateTime2);
        atomicTime1.compareAndSet(zonedDateTime2, zonedDateTime2.plusMonths(1));
        System.out.println(atomicTime1);
        System.out.println(zonedDateTime2);

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(null);
        System.out.println(list.stream().reduce(Integer::sum));
    }

    @Test
    public void testStringValue() {
        String startTime = "00:05";
        System.out.println(startTime);
        changeStringValue(startTime);
        System.out.println(startTime);
    }

    @Test
    public void changeStringValue(String str) {
        str = "00:00";
        int x = 5;
        System.out.println(-x);
    }

    @Test
    public void testCopy() {
        Source source = new Source();
        source.setBeginPosition(9.8);
        Target target = new Target();
        BeanUtils.copyProperties(source, target);
        System.out.println(target);
    }

    @Test
    public void urlDecode() throws UnsupportedEncodingException {
        String str = "/open/gateway?app_id=90001&method=control_publish_simple&version=2.0&api_request_id=001&charset=UTF-8&sign_type=RSA&sign=er&content=%7B%22deviceCode%22%3A%223ceb6fe650b04b50a59852233e8b2420%22%2C%20%22list%22%3A%5B%7B%22textList%22%3A%5B%7B%22fontSize%22%3A%2232%22%2C%20%22fontColor%22%3A%22%23ffff00%22%2C%20%22backColor%22%3A%22000000%22%2C%20%22content%22%3A%22%E7%AC%AC%E4%B8%80%E8%A1%8C%E6%96%87%E5%AD%97%E7%AC%AC%E4%BA%8C%E8%A1%8C%E6%96%87%E5%AD%97%22%2C%20%22horizonAlign%22%3A2%2C%20%22verticalAlign%22%3A2%7D%5D%2C%20%22img%22%3A%5B%5D%2C%20%22videos%22%3A%5B%5D%2C%20%22templateId%22%3A%22t%22%2C%20%22stayTime%22%3A3%7D%5D%7D";
        String decode = URLDecoder.decode(str, "UTF-8");
        System.out.println(decode);
    }

    @Test
    public void changeAtomic() {
        String str = "test";
        AtomicReference<String> result = new AtomicReference<>(str);
        System.out.println(result);
        str = "test1";
        System.out.println(result);

        User user = User.builder().id("id").build();
        AtomicReference<User> result1 = new AtomicReference<>(user);
        System.out.println(result1);
        user.setId("id1");
        System.out.println(result1);
    }

    @Test
    public void testExceptionInSubThread() throws InterruptedException {
        System.out.println(1);
        try {
            new Thread(() -> {
                System.out.println("sub thread start.");
                throw new RuntimeException("子线程抛出异常");
//            System.out.println("sub thread end.");
            }).start();
        } catch (Exception e) {
            log.error("主线程捕获到子线程异常。", e);
        }
        Thread.sleep(20000);
    }

    @Test
    public void testExceptionInSubThread2() throws InterruptedException {
        System.out.println(1);
        new Thread(() -> {
            try {
                System.out.println("sub thread start.");
                throw new RuntimeException("子线程抛出异常");
//            System.out.println("sub thread end.");
            } catch (Exception e) {
                log.error("子线程捕获到自身异常。", e);
            }
        }).start();

        Thread.sleep(20000);
    }

    @Test
    public void testExceptionInSubThread3() throws InterruptedException, ExecutionException {
        System.out.println(1);
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future<String> submit = threadPool.submit(() -> {
            System.out.println("sub thread start.");
            throw new RuntimeException("子线程抛出异常");
//            return "success";
        });
        String result = submit.get();
        System.out.println(result);


        Thread.sleep(20000);
    }

    @Test
    public void testUserDir() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(ChronoUnit.MINUTES.between(ZonedDateTime.now(), ZonedDateTime.now().minusMinutes(30)));
    }

    @Test
    public void testNullArray() {
        String[] array = null;
        List<String> collect = Arrays.stream(array).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void testSortComparable() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(5);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void testEmptyArrayArgs() {
        printArray();
        printArray(1, 2, "3");
        Integer str = null;
        System.out.println(str == 0);
    }

    private void printArray(Object... values) {
        List<Object> list = new ArrayList<>(Arrays.asList(values));
        System.out.println(list);
    }

    @Test
    public void testRemoveStream() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().id("1").build());
        list.add(User.builder().id("2").build());
        list.add(User.builder().id("3").build());
        list.add(User.builder().id("4").build());
        list.add(User.builder().id("5").build());
        System.out.println(list.stream().map(User::getId).collect(Collectors.joining(",", "(", ")")));
        Assertions.assertEquals(5, list.size());
        list.stream().filter(e -> e.getId().equals("1")).findFirst().ifPresent(list::remove);
        Assertions.assertEquals(4, list.size());
        Long aLong = new DefaultIdentifierGenerator().nextId(list.get(0));
        System.out.println(aLong);
    }

    @Test
    public void testNullBooleanCondition() {
        System.out.println(LocalDate.now().toString());
        Boolean unfriendly = null;
        List<Boolean> list = new ArrayList<>();
        list.add(unfriendly);
        System.out.println(list.stream().filter(e -> e != null && e).count());
        Map<String, Object> map = new HashMap<>();
        Object o = map.get(null);
        System.out.println(o);
    }

    @Test
    public void testTimeConvert() throws ParseException {
        ZonedDateTime zonedDateTime = DateUtils.dateTimeStrToZonedDateTime("0001-01-01 00:00:00");
        System.out.println(DateUtils.formatTime(zonedDateTime));
        System.out.println(zonedDateTime.toInstant());
        Date date = Date.from(zonedDateTime.toInstant());
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse("0001-01-01 00:00:00");
        System.out.println(parse);
        ZonedDateTime zonedDateTime1 = DateUtils.dateToZonedDateTime(parse);
        String formatDate = DateUtils.formatTime(parse);
        System.out.println(formatDate);
    }

    @Test
    public void testMath() {
        String msg = "涩图搜索宵宫";
        String keyword = msg.replaceAll("#*(涩图|色图)搜索", "");
        String str = "测试斜杠/////";
        String replace = str.replace("/", "");
        System.out.println(replace);
        Integer jamCount = 0;
        System.out.println(jamCount);
        porcessJamCount(jamCount);
        System.out.println(jamCount);
    }

    private void porcessJamCount(Integer jamCount) {
        jamCount = jamCount + 100;
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
