package experimentLesson.lesson07;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * 实验七 · Java GUI 编程（布局管理器、事件处理）—— 课堂单文件版。
 *
 * <p>本文件自包含：所有界面、监听器、绘图类都写在内部类中，编译运行这一个文件即可。</p>
 *
 * <pre>
 * javac -encoding UTF-8 -d out experimentLesson/lesson07/Lesson07Core.java
 * java  -cp out experimentLesson.lesson07.Lesson07Core            # 打开全部窗口
 * java  -cp out experimentLesson.lesson07.Lesson07Core 3          # 只看第 3 段的窗口
 * </pre>
 *
 * <p><b>四段内容与实验指导书的对应关系：</b></p>
 * <pre>
 * ① 窗口与常用组件   Frame / Panel / Label / TextField / TextArea / CheckBox / 下拉框
 * ② 布局管理器       FlowLayout / BorderLayout / GridLayout / 空布局
 * ③ 事件处理         自定义监听器类、匿名内部类、Lambda、适配器类；
 *                    动作事件、鼠标事件、键盘事件、窗体事件
 * ④ AWT 绘图         绘图三要素 paintComponent / Graphics / repaint（验证码案例）
 * </pre>
 *
 * <p><b>AWT 与 Swing 的关系：</b></p>
 * <pre>
 * AWT：JDK 1.0，组件直接调用操作系统的原生控件（重量级），
 *      核心类在 java.awt 包：Frame、Panel、Button、Label、TextField……
 * Swing：JDK 1.2，纯 Java 绘制的轻量级组件，类名加 J 前缀（JFrame、JButton），
 *      在 javax.swing 包，事件模型与 AWT 完全一致，本章代码两套通用。
 * </pre>
 *
 * <p><b>事件处理三要素（本章核心模型）：</b></p>
 * <pre>
 * 事件源（Event Source）：发生事件的组件，如按钮、文本框、窗体
 * 事件对象（Event Object）：封装了事件信息，如 ActionEvent、MouseEvent、KeyEvent、WindowEvent
 * 监听器（Listener）：实现 XxxListener 接口的对象，事件源调用 addXxxListener 注册后，
 *                     事件发生时 JVM 自动回调对应方法（如 actionPerformed）
 * </pre>
 *
 * <p><b>JDK 兼容性：</b>只用 Java 8 语法与 API，已在 JDK 1.8.0_442 上实测编译运行通过。</p>
 *
 * @author 面向对象程序设计课程组
 * @version 1.0
 */
public class Lesson07Core {

    /**
     * 程序入口。
     *
     * <p>参数说明：不带参数打开全部窗口；传 1~4 只打开对应段落的窗口；
     * 传 auto 则自动打开全部窗口并在数秒后自动退出（用于自动测试）。</p>
     *
     * <p>Swing 规定所有界面操作都应在事件调度线程（EDT）上进行，
     * 因此创建窗口的代码用 SwingUtilities.invokeLater 包起来。</p>
     *
     * @param args 窗口段号 / auto
     */
    public static void main(String[] args) {
        String mode = args.length > 0 ? args[0] : "";

        if ("auto".equals(mode)) {
            // 自动测试模式：3 秒后自动退出，避免进程一直驻留
            Timer timer = new Timer(3000, e -> System.exit(0));
            timer.setRepeats(false);
            timer.start();
        }

        SwingUtilities.invokeLater(() -> {
            int which = 0;
            try {
                which = Integer.parseInt(mode);
            } catch (NumberFormatException e) {
                which = 0;
            }
            if (which == 0 || which == 1) {
                section1Window();
            }
            if (which == 0 || which == 2) {
                section2Layouts();
            }
            if (which == 0 || which == 3) {
                section3Events();
            }
            if (which == 0 || which == 4) {
                section4Captcha();
            }
        });
    }

    // ==================== ① 窗口与常用组件 ====================

    /**
     * 窗口与常用组件：一个 FlowLayout 布局的简单界面。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>JFrame 是顶级容器（窗口），其余组件必须放进容器；</li>
     *   <li>ContentPane（内容面板）：组件不直接加到窗口，而是加到内容面板
     *       frame.add(c) 默认就是加到 ContentPane；</li>
     *   <li>setDefaultCloseOperation 决定点关闭按钮的行为：
     *       EXIT_ON_CLOSE 结束整个程序、DISPOSE_ON_CLOSE 只销毁当前窗口；</li>
     *   <li>setVisible(true) 必须最后调用，否则组件加入不会立即刷新；</li>
     *   <li>常用组件：JLabel 标签、JTextField 单行文本、JPasswordField 密码、
     *       JCheckBox 复选框、JComboBox 下拉框、JTextArea 多行文本、JButton 按钮。</li>
     * </ol>
     */
    private static void section1Window() {
        JFrame frame = new JFrame("① 窗口与常用组件");
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 12));   // 流式布局：从左到右排

        frame.add(new JLabel("用户名:"));
        frame.add(new JTextField(12));                               // 宽度按列数指定
        frame.add(new JLabel("密  码:"));
        frame.add(new JPasswordField(12));
        frame.add(new JCheckBox("记住密码"));
        frame.add(new JLabel("学院:"));

        JComboBox<String> box = new JComboBox<>(new String[]{"计算机学院", "软件学院", "大数据学院"});
        frame.add(box);                                              // 泛型下拉框

        JTextArea area = new JTextArea("TextArea：多行文本区域\n可设置行数与列数", 3, 30);
        frame.add(area);
        frame.add(new JButton("提 交"));
        frame.add(new JButton("重 置"));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(80, 80, 420, 280);                           // 位置 + 大小一步设置
        frame.setVisible(true);
    }

    // ==================== ② 布局管理器 ====================

    /**
     * 布局管理器：FlowLayout / BorderLayout / GridLayout / 空布局同屏对比。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>Flow 流式：按添加顺序从左到右排，放不下换行；默认居中；</li>
     *   <li>Border 边界：把容器分为东、南、西、北、中五个区域，
     *       每个区域最多放一个组件；CENTER 会占满剩余空间（本例中它套了一个 GridLayout 面板）；</li>
     *   <li>Grid 网格：按 rows × cols 均分容器，每个格子大小相同，
     *       添加顺序为从左到右、从上到下；</li>
     *   <li>空布局（null）：不使用布局管理器，用 setBounds(x, y, w, h) 手动定位，
     *       灵活但窗口缩放后不会自适应，适合精确定位的局部界面；</li>
     *   <li>复合布局：容器相互嵌套（区域里放面板，面板再用自己的布局），
     *       这是实际界面设计的标准做法；JFrame / JPanel 的默认布局分别是
     *       BorderLayout 和 FlowLayout。</li>
     * </ol>
     */
    private static void section2Layouts() {
        JFrame frame = new JFrame("② 布局管理器对比");
        frame.setLayout(new BorderLayout(8, 8));

        // 北区：FlowLayout —— 组件排成一行
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        north.add(new JButton("流式 1"));
        north.add(new JButton("流式 2"));
        north.add(new JButton("流式 3"));
        frame.add(north, BorderLayout.NORTH);

        // 中区：GridLayout —— 3 行 3 列均分
        JPanel center = new JPanel(new GridLayout(3, 3, 4, 4));
        for (int i = 1; i <= 9; i++) {
            center.add(new JButton("网格 " + i));
        }
        frame.add(center, BorderLayout.CENTER);

        // 南区：空布局 —— setBounds 手动定位
        JPanel south = new JPanel(null);
        south.setPreferredSize(new Dimension(0, 90));
        JButton b1 = new JButton("空布局 1");
        JButton b2 = new JButton("空布局 2");
        b1.setBounds(30, 25, 110, 32);
        b2.setBounds(170, 25, 110, 32);
        south.add(b1);
        south.add(b2);
        frame.add(south, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(420, 100, 430, 360);
        frame.setVisible(true);
    }

    // ==================== ③ 事件处理 ====================

    /**
     * 事件处理：动作、鼠标、键盘、窗体四类事件，多种监听器写法。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>自定义监听器类（本例的 LoginListener）：可实现 ActionListener 接口，
     *       写成独立类便于复用，组件引用通过构造方法传入；</li>
     *   <li>匿名内部类：只在一处使用时最简洁，是 GUI 开发最常见的写法；</li>
     *   <li>Lambda 写法：ActionListener 是函数式接口（只有一个抽象方法），
     *       可以用 Lambda 代替匿名内部类；</li>
     *   <li>适配器类：MouseListener、KeyListener、WindowListener 接口方法太多，
     *       全部实现很啰嗦；XxxAdapter 已提供空实现，继承后只重写需要的方法；</li>
     *   <li>四类事件对应的监听接口：动作事件 ActionListener、鼠标事件 MouseListener、
     *       键盘事件 KeyListener、窗体事件 WindowListener。</li>
     * </ol>
     *
     * <p>操作提示：账号 admin 密码 123456；鼠标点击、键盘输入、点窗口右上角关闭，
     * 观察控制台的事件日志。</p>
     */
    private static void section3Events() {
        JFrame frame = new JFrame("③ 事件处理（看控制台日志）");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        final JTextField userField = new JTextField(10);
        final JPasswordField passField = new JPasswordField(10);
        final JLabel msgLabel = new JLabel("……");

        panel.add(new JLabel("账号:"));
        panel.add(userField);
        panel.add(new JLabel("密码:"));
        panel.add(passField);

        // 写法一：自定义监听器类 + 对象（逻辑复杂、需要复用时推荐）
        JButton loginButton = new JButton("登录(自定义监听器类)");
        loginButton.addActionListener(new LoginListener(userField, passField, msgLabel));
        panel.add(loginButton);

        // 写法二：匿名内部类（一次性逻辑最常用）
        JButton clearButton = new JButton("清空(匿名内部类)");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userField.setText("");
                passField.setText("");
                msgLabel.setText("已清空");
            }
        });
        panel.add(clearButton);

        // 写法三：Lambda（ActionListener 是函数式接口，可一行完成注册）
        JButton aboutButton = new JButton("关于(Lambda)");
        aboutButton.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "实验七：GUI 编程\n账号 admin，密码 123456"));
        panel.add(aboutButton);
        panel.add(msgLabel);

        // 键盘事件 + 鼠标事件都注册在下面的事件日志区，用适配器类只重写需要的方法
        final JTextArea logArea = new JTextArea(6, 42);
        logArea.append("把鼠标移进来、点击、输入字符，观察这里的变化。\n");

        logArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logArea.append("鼠标进入 (" + e.getX() + "," + e.getY() + ")\n");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                logArea.append("鼠标点击 " + e.getClickCount() + " 次 ("
                        + e.getX() + "," + e.getY() + ")\n");
            }
        });

        logArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                logArea.append("键入字符: " + e.getKeyChar() + "\n");
            }
        });

        frame.add(panel, BorderLayout.NORTH);
        frame.add(logArea, BorderLayout.CENTER);

        // 窗体事件：WindowListener 有 7 个方法，用 WindowAdapter 只重写关心的那个
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("[窗体事件] 窗口正在关闭（windowClosing 被触发）");
            }
        });

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(760, 100, 500, 300);
        frame.setVisible(true);
    }

    /**
     * 自定义监听器类：实现 ActionListener 接口处理动作事件。
     *
     * <p>组件引用通过构造方法传入，说明监听器是普通对象，
     * 可以持有任意状态，事件源与处理逻辑完全解耦。</p>
     */
    static class LoginListener implements ActionListener {

        private final JTextField userField;
        private final JPasswordField passField;
        private final JLabel msgLabel;

        LoginListener(JTextField userField, JPasswordField passField, JLabel msgLabel) {
            this.userField = userField;
            this.passField = passField;
            this.msgLabel = msgLabel;
        }

        /** 按钮被点击（或文本框中按回车）时由 JVM 自动回调 */
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword());
            if ("admin".equals(user) && "123456".equals(pass)) {
                msgLabel.setText("登录成功，欢迎 " + user);
            } else {
                msgLabel.setText("登录失败（账号 admin，密码 123456）");
            }
        }
    }

    // ==================== ④ AWT 绘图：验证码 ====================

    /**
     * AWT 绘图：绘制并校验随机验证码。
     *
     * <p>知识点：</p>
     * <ol>
     *   <li>绘图三步：继承 JPanel 重写 paintComponent(Graphics g)；
     *       用 Graphics 对象画图（drawString / drawLine / fillRect / setColor / setFont）；
     *       需要刷新时调用 repaint()，JVM 会重新回调 paintComponent；</li>
     *   <li>重写 paintComponent 必须先调用 super.paintComponent(g) 清除旧画面，
     *       否则图形会叠加残留；</li>
     *   <li>不要把"生成随机数"写在 paintComponent 里：绘图会被频繁触发，
     *       状态型数据应存成员变量，需要变化时改数据再 repaint；</li>
     *   <li>验证码字符集去掉 0/O、1/I 等易混淆字符；比对时可用
     *       equalsIgnoreCase 忽略大小写；</li>
     *   <li>本例同时练习 BorderLayout（验证码图片在中区、输入区在南区）。</li>
     * </ol>
     */
    private static void section4Captcha() {
        JFrame frame = new JFrame("④ AWT 绘图：验证码");
        frame.setLayout(new BorderLayout());

        final CaptchaPanel captcha = new CaptchaPanel();
        frame.add(captcha, BorderLayout.CENTER);

        final JTextField input = new JTextField(10);
        final JLabel msgLabel = new JLabel("请输入图中的验证码");
        JButton checkButton = new JButton("验 证");

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (captcha.getCode().equalsIgnoreCase(input.getText().trim())) {
                    JOptionPane.showMessageDialog(frame, "验证通过！");
                } else {
                    JOptionPane.showMessageDialog(frame, "验证码错误，已刷新");
                }
                input.setText("");
                captcha.regenerate();                    // 换一张验证码
            }
        });

        JPanel south = new JPanel(new FlowLayout());
        south.add(input);
        south.add(checkButton);
        south.add(msgLabel);
        frame.add(south, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(1100, 140, 260, 200);
        frame.setVisible(true);
    }

    /**
     * 验证码图片面板：重写 paintComponent 自行绘图。
     */
    static class CaptchaPanel extends JPanel {

        /** 去掉 0/O、1/I 等易混淆字符后的字符集 */
        private static final String CHARS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

        private final Random random = new Random();

        /** 当前验证码内容：状态存字段，绘图时只读取不生成 */
        private String code;

        CaptchaPanel() {
            setPreferredSize(new Dimension(200, 80));
            code = genCode();
            // 点击图片即刷新验证码（适配器类写法）
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    regenerate();
                }
            });
        }

        String getCode() {
            return code;
        }

        /** 更新验证码内容并请求重绘 */
        void regenerate() {
            code = genCode();
            repaint();
        }

        private String genCode() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
            }
            return sb.toString();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);                       // 必须先清除旧画面
            int w = getWidth();
            int h = getHeight();

            g.setColor(new Color(238, 242, 250));
            g.fillRect(0, 0, w, h);                        // 背景

            for (int i = 0; i < 12; i++) {                 // 干扰线
                g.setColor(randomColor());
                g.drawLine(random.nextInt(w), random.nextInt(h),
                        random.nextInt(w), random.nextInt(h));
            }

            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
            for (int i = 0; i < code.length(); i++) {      // 逐个绘制验证码字符
                g.setColor(randomColor());
                g.drawString(String.valueOf(code.charAt(i)),
                        i * w / 4 + 10, h / 2 + 12);
            }
        }

        private Color randomColor() {
            return new Color(random.nextInt(200), random.nextInt(200), random.nextInt(200));
        }
    }
}
