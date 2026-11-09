package experimentLesson.lesson08;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实验八 · 多线程设计基础 —— 课堂单文件版。
 *
 * <p>本文件自包含：所有线程类都写成内部类，编译运行这一个文件即可。</p>
 *
 * <pre>
 * javac -encoding UTF-8 -d out experimentLesson/lesson08/Lesson08Core.java
 * java  -cp out experimentLesson.lesson08.Lesson08Core
 * </pre>
 *
 * <p><b>五段内容与实验指导书的对应关系：</b></p>
 * <pre>
 * ① 线程创建       继承 Thread / 实现 Runnable / 实现 Callable，start() 与 run() 的区别
 * ② 常用方法       currentThread / getName / sleep / isAlive / setDaemon（后台线程）
 *                  Thread.State 生命周期状态（NEW / RUNNABLE / TIMED_WAITING / TERMINATED）
 * ③ 线程调度       setPriority 优先级、yield() 让步、join() 插队等待
 * ④ 线程安全       共享资源并发修改的问题与 synchronized 同步（卖票经典案例）
 * ⑤ 总结           四句话
 * </pre>
 *
 * <p><b>线程生命周期与状态（Thread.State 枚举）：</b></p>
 * <pre>
 * NEW          新建：start() 被调用之前
 * RUNNABLE     可运行：正在运行或等待 CPU 时间片（Java 把"就绪"与"运行中"合并）
 * BLOCKED      锁阻塞：等待获取 synchronized 锁
 * WAITING      无限等待：wait() / join() 未带超时
 * TIMED_WAITING 限时等待：sleep(ms) / wait(ms) / join(ms)
 * TERMINATED   终止：run() 执行完毕
 * </pre>
 *
 * <p><b>注意：</b>多线程程序的输出顺序受 CPU 调度影响，
 * 每次运行的结果可能不同 —— 这本身就是多线程最重要的观察点。</p>
 *
 * <p><b>JDK 兼容性：</b>只用 Java 8 语法与 API（含 Callable / FutureTask、Lambda），
 * 已在 JDK 1.8.0_442 上实测编译运行通过。</p>
 *
 * @author 面向对象程序设计课程组
 * @version 1.0
 */
public class Lesson08Core {

    /**
     * 程序入口：按顺序跑完五段演示。
     *
     * @param args 未使用
     * @throws InterruptedException 演示 sleep / join 时可能被中断
     */
    public static void main(String[] args) throws InterruptedException {
        section1Create();
        section2MethodsAndDaemon();
        section3Schedule();
        section4Sync();
        section5Summary();
    }

    // ==================== ① 线程创建 ====================

    /**
     * 创建线程的三种方式与 start() / run() 的区别。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>继承 Thread：重写 run()，缺点是 Java 单继承，线程类无法再继承其他类；</li>
     *   <li>实现 Runnable：把"任务"与"线程"分离，还可以继续继承其他类、
     *       便于多个线程共享同一份任务数据（如后面的卖票），是推荐方式；
     *       注意 Runnable 里没有 getName()，要用 Thread.currentThread() 获取当前线程；</li>
     *   <li>实现 Callable：任务有返回值、可以抛异常，必须配合 FutureTask 使用，
     *       futureTask.get() 会阻塞当前线程直到结果算完；</li>
     *   <li>start() 才是启动新线程（JVM 创建新的调用栈）；
     *       直接调用 run() 只是普通的方法调用，代码仍在当前线程执行；</li>
     *   <li>一个线程对象只能 start() 一次，重复调用抛 IllegalThreadStateException。</li>
     * </ol>
     */
    private static void section1Create() throws InterruptedException {
        title("① 线程创建的三种方式");

        // ---- 方式一：继承 Thread 类 ----
        MyThread t1 = new MyThread("Thread子类");
        t1.start();

        // ---- 方式二：实现 Runnable 接口（任务与线程分离）----
        Thread t2 = new Thread(new MyRunnable(), "Runnable实现");
        t2.start();

        // ---- 方式三：实现 Callable 接口（有返回值）----
        // Callable 不能直接交给 Thread，必须先包一层 FutureTask
        FutureTask<Long> task = new FutureTask<>(new MyCallable());
        Thread t3 = new Thread(task, "Callable实现");
        t3.start();

        // 等两个打印型线程结束再取结果，让输出更清晰
        t1.join();
        t2.join();
        // get() 会阻塞，直到 Callable 计算完成并返回结果
        try {
            System.out.println("Callable 计算结果（1~100 求和）= " + task.get());
        } catch (ExecutionException e) {
            // Callable 的 call() 中抛出的异常会被包装成 ExecutionException 传回
            e.printStackTrace();
        }

        // ---- 对照实验：直接调用 run() 不是启动线程 ----
        System.out.println("\n对照：直接调用 run()（看执行它的线程名）");
        MyThread notStarted = new MyThread("没有start的线程");
        notStarted.run();        // 普通方法调用，仍在 main 线程里执行

        System.out.println("→ start() 创建新的调用栈并交给调度器；run() 直接调用只是在当前线程里执行普通方法。");
    }

    // ==================== ② 常用方法与后台线程 ====================

    /**
     * Thread 常用方法、后台线程与线程状态。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>currentThread()：静态方法，获取"正在执行这段代码的"线程；
     *       getName() / setName()：线程默认名 Thread-0、Thread-1……，可以自定义；</li>
     *   <li>sleep(ms)：静态方法，让当前线程暂停指定毫秒并让出 CPU；
     *       持有的锁不会释放； InterruptedException 必须处理；</li>
     *   <li>isAlive()：线程已启动且尚未终止返回 true；</li>
     *   <li>后台（守护）线程：为其他线程提供服务的线程（如 GC）。
     *       setDaemon(true) 必须在 start() 之前调用，否则抛 IllegalThreadStateException；
     *       当所有前台线程结束时，后台线程无论跑到哪里都会被 JVM 直接终止；</li>
     *   <li>getState()：返回 Thread.State 枚举，是观察线程生命周期最直观的方式。</li>
     * </ol>
     */
    private static void section2MethodsAndDaemon() throws InterruptedException {
        title("② 常用方法、后台线程与线程状态");

        // ---- 1. 线程名与 isAlive ----
        Thread named = new Thread(() -> {
            System.out.println("  线程内部看自己: " + Thread.currentThread().getName());
        }, "手动命名的线程");
        System.out.println("isAlive（启动前） = " + named.isAlive());
        named.start();
        named.join();
        System.out.println("isAlive（结束后） = " + named.isAlive());

        // ---- 2. 线程状态：观察一次完整的生命周期 ----
        Thread stateThread = new Thread(() -> {
            sleepQuietly(300);       // sleep 期间处于 TIMED_WAITING
        }, "state-观察员");

        System.out.println("状态 start 之前   = " + stateThread.getState());     // NEW
        stateThread.start();
        System.out.println("状态 start 之后   = " + stateThread.getState());     // RUNNABLE
        Thread.sleep(50);
        System.out.println("状态 sleep 期间   = " + stateThread.getState());     // TIMED_WAITING
        stateThread.join();
        System.out.println("状态 run 结束后   = " + stateThread.getState());     // TERMINATED

        // ---- 3. 后台（守护）线程：前台线程全部结束，后台线程随之销毁 ----
        Thread daemon = new Thread(() -> {
            while (daemonOn) {
                System.out.println("  后台线程仍在工作……");
                sleepQuietly(300);
            }
            System.out.println("  后台线程检测到标志位变化，自然结束");
        }, "daemon-worker");
        daemon.setDaemon(true);              // 必须在 start() 之前设置
        daemon.start();

        Thread.sleep(1000);                  // 主线程休息 1 秒，能看到后台线程打印几次
        System.out.println("isDaemon() = " + daemon.isDaemon()
                + "（若不手动停止，所有前台线程结束后 JVM 会直接终止后台线程）");
        daemonOn = false;                    // 让后台线程自然结束，避免影响后续演示
        daemon.join();
    }

    /** 后台线程的停止标志：volatile 保证其他线程能立即看到修改 */
    private static volatile boolean daemonOn = true;

    // ==================== ③ 线程调度 ====================

    /**
     * 线程调度：优先级、让步与插队。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>setPriority(int)：优先级范围 1~10，常量 MIN_PRIORITY / NORM_PRIORITY /
     *       MAX_PRIORITY（默认 5）。优先级高只是"更容易"被调度到，
     *       不能保证执行顺序，结果依赖操作系统；</li>
     *   <li>yield()：静态方法，建议当前线程让出 CPU 给同优先级线程，
     *       只是"提示"，调度器可以忽略；</li>
     *   <li>join()：在当前线程中调用 other.join() 表示"等 other 执行完我再继续"，
     *       常用于主线程等待子线程的收尾工作。</li>
     * </ol>
     */
    private static void section3Schedule() throws InterruptedException {
        title("③ 线程调度：优先级、yield 与 join");

        // ---- 1. 优先级：三个线程不同优先级做同样的事 ----
        Runnable worker = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 5; i++) {
                System.out.println("  " + name + " 第 " + i + " 次");
                sleepQuietly(20);
            }
        };
        Thread max = new Thread(worker, "优先级10");
        Thread norm = new Thread(worker, "优先级05");
        Thread min = new Thread(worker, "优先级01");
        max.setPriority(Thread.MAX_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);
        min.setPriority(Thread.MIN_PRIORITY);
        max.start();
        norm.start();
        min.start();
        max.join();
        norm.join();
        min.join();
        System.out.println("→ 输出顺序每次可能不同：优先级只是概率上的倾向，不是保证。");

        // ---- 2. yield 让步 ----
        Thread yieldA = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("  线程A 让步前 i=" + i);
                Thread.yield();               // 主动让出 CPU，给其他线程机会
            }
        }, "A");
        Thread yieldB = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("  线程B 让步前 i=" + i);
                Thread.yield();
            }
        }, "B");
        yieldA.start();
        yieldB.start();
        yieldA.join();
        yieldB.join();
        System.out.println("→ yield 后 A、B 的输出更容易交替，但依然是概率行为。");

        // ---- 3. join 插队等待 ----
        Thread longJob = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("  耗时任务进行中 " + i + "/3");
                sleepQuietly(200);
            }
        }, "耗时任务");
        longJob.start();
        longJob.join();                        // 主线程在此阻塞，直到 longJob 结束
        System.out.println("join 之后：主线程确认耗时任务已全部完成");
    }

    // ==================== ④ 线程安全 ====================

    /**
     * 线程安全：多个线程并发修改共享资源的问题与 synchronized 解决方案。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>卖票案例：两个窗口线程共享同一个静态变量 tickets；
     *       "判断 &gt; 0"与"递减"两步之间可能被其他线程打断，
     *       造成同一张票被卖出多次、甚至卖成负数 —— 这就是竞态条件；</li>
     *   <li>synchronized 同步代码块：同一时刻只允许一个线程持有锁并执行代码块；
     *       锁对象必须唯一：本例两个窗口是不同对象，所以必须用类锁
     *       TicketSeller.class（或把方法声明为 static synchronized）；</li>
     *   <li>synchronized 修饰实例方法的锁是 this，修饰静态方法的锁是 Class 对象；</li>
     *   <li>同步能保证正确性，但降低了并发度：锁的范围（粒度）要尽量小。</li>
     * </ol>
     */
    private static void section4Sync() throws InterruptedException {
        title("④ 线程安全：不加同步 vs synchronized");

        // ---- 1. 不加同步：两窗口并发卖票，观察票号重复 ----
        TicketSeller.reset(20);
        Thread unsafeA = new Thread(new TicketSeller("窗口A(无同步)", false), "A");
        Thread unsafeB = new Thread(new TicketSeller("窗口B(无同步)", false), "B");
        unsafeA.start();
        unsafeB.start();
        unsafeA.join();
        unsafeB.join();
        System.out.println("→ 上面出现了重复票号：判断与递减之间被另一个线程打断。");

        // ---- 2. 加 synchronized：同一批票，结果正确 ----
        TicketSeller.reset(20);
        Thread safeA = new Thread(new TicketSeller("窗口A(同步)", true), "A");
        Thread safeB = new Thread(new TicketSeller("窗口B(同步)", true), "B");
        safeA.start();
        safeB.start();
        safeA.join();
        safeB.join();
        System.out.println("→ 同步后每张票只出现一次，从 20 递减到 1，无重复、无遗漏。");
    }

    // ==================== ⑤ 总结 ====================

    /** 四句话小结。 */
    private static void section5Summary() {
        title("⑤ 记住四句话");

        System.out.println("1. 三种创建方式：继承 Thread、实现 Runnable（推荐）、实现 Callable（有返回值，配 FutureTask）；启动必须调 start()。");
        System.out.println("2. setDaemon(true) 要在 start() 之前调；前台线程全部结束，后台线程随之销毁。");
        System.out.println("3. setPriority / yield 只影响概率不保证顺序；join() 让当前线程等待目标线程结束。");
        System.out.println("4. 并发修改共享资源必须加 synchronized，锁对象必须唯一（多个实例时用类锁）。");
    }

    // ==================== 辅助方法 ====================

    /** 打印分段标题 */
    private static void title(String text) {
        System.out.println();
        System.out.println("==== " + text + " ====");
    }

    /** 演示代码里的 sleep：中断直接恢复中断标记，不让异常打断演示流程 */
    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ==================== 演示用内部类（线程类） ====================

    /**
     * 方式一：继承 Thread 类。
     *
     * <p>run() 里用 currentThread().getName() 取得真正执行的线程名，
     * 用于区分 start() 与直接调 run() 的差别。</p>
     */
    static class MyThread extends Thread {

        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("  [" + Thread.currentThread().getName() + "] 执行第 " + i + " 次");
                sleepQuietly(30);
            }
        }
    }

    /**
     * 方式二：实现 Runnable 接口。
     *
     * <p>Runnable 中没有 getName() 方法，只能通过 currentThread() 获取当前线程。</p>
     */
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("  [" + Thread.currentThread().getName() + "] 执行第 " + i + " 次");
                sleepQuietly(30);
            }
        }
    }

    /**
     * 方式三：实现 Callable 接口。
     *
     * <p>call() 有返回值且可以抛出受检异常；
     * 泛型参数 Long 描述返回值类型，通过 FutureTask.get() 取回。</p>
     */
    static class MyCallable implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            Thread.sleep(200);            // 模拟耗时计算
            return sum;
        }
    }

    /**
     * 卖票线程：两个窗口并发卖出同一批共享的票。
     *
     * <p>safe = false 时使用普通方法（演示竞态条件），
     * safe = true 时用 synchronized 类锁保证原子性。</p>
     */
    static class TicketSeller implements Runnable {

        /** 共享资源：static 字段被所有窗口线程共同访问 */
        private static int tickets = 20;

        private final String window;
        private final boolean safe;

        TicketSeller(String window, boolean safe) {
            this.window = window;
            this.safe = safe;
        }

        /** 每轮演示前重置票数 */
        static void reset(int n) {
            tickets = n;
        }

        @Override
        public void run() {
            while (tickets > 0) {
                if (safe) {
                    sellSafe();
                } else {
                    sellUnsafe();
                }
            }
        }

        /** 不加同步：判断与递减之间存在时间窗口，会产生重复票号 */
        private void sellUnsafe() {
            if (tickets > 0) {
                sleepQuietly(5);          // 制造线程切换机会，放大并发问题
                System.out.println("  " + window + " 卖出第 " + tickets + " 张票");
                tickets--;
            }
        }

        /**
         * 加同步：synchronized(TicketSeller.class) 是类锁。
         *
         * <p>两个窗口是两个不同的对象，用 this 做锁互不相干，
         * 必须用所有实例共享的同一把锁（Class 对象）才能互斥。</p>
         */
        private void sellSafe() {
            synchronized (TicketSeller.class) {
                if (tickets > 0) {
                    sleepQuietly(5);
                    System.out.println("  " + window + " 卖出第 " + tickets + " 张票");
                    tickets--;
                }
            }
        }
    }
}
