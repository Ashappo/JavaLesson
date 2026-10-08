package ExperimentLesson.lesson03;

/**
 * 父类：员工（Employee）
 *
 * 实验要点：
 *  1. 父类拥有“私有”的域和方法（name、id、baseSalary、isValidId()），
 *     子类不能直接访问，必须通过 super 调用父类的受保护/公共访问å器方法。
 *  2. 父类拥有“protected”的域（department）和方法（getBonus()、getName()等），
 *     子类可以直接访问和覆盖（较高要求）。
 *  3. 提供“有参”和“无参”两种构造器，用来观察构造器调用次序。
 *  4. 父类自己带有 main 方法，可在其中单独测试父类（实验要求 3）。
 */
public class Employee {

    // ---------- 私有域：子类不能直接访问 ----------
    private String name;          // 姓名
    private int id;               // 工号
    private double baseSalary;    // 基本工资

    // ---------- protected 域：子类可直接访问（较高要求） ----------
    protected String department; // 所属部门

    // ---------- 构造器 ----------

    /**
     * 无参构造器：通过 this(...) 调用本类的有参构造器，传入默认值。
     * 子类无参构造器默认调用 super() 到达这里，从而观察调用次序。
     */
    public Employee() {
        this("未知名员工", 0, 0.0, "未分配");
        System.out.println("  [Employee 无参构造器] 执行完毕");
    }

    /**
     * 有参构造器：初始化父类的私有域和 protected 域。
     */
    public Employee(String name, int id, double baseSalary, String department) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
        this.department = department;
        System.out.println("  [Employee 有参构造器] 初始化：" + name);
    }

    // ---------- 私有方法：仅在父类内部调用，子类看不到 ----------
    private boolean isValidId() {
        return id > 0;
    }

    // ---------- protected 方法：子类可访问、可覆盖（较高要求） ----------

    /**
     * 计算奖金（受保护方法）。各子类会覆盖它，体现出奖金计算上的差异。
     */
    protected double getBonus() {
        return baseSalary * 0.10;   // 普通员工：基本工资的 10%
    }

    // 供子类通过 super 间接访问父类的私有域的“访问器”（protected）
    protected String getName() {
        return name;
    }

    protected int getId() {
        return id;
    }

    protected double getBaseSalary() {
        return baseSalary;
    }

    // ---------- 公共方法：子类覆盖，体现差异性 ----------

    /** 工作方法：不同子类覆盖后输出不同内容。 */
    public void work() {
        System.out.println(getName() + " 正在处理日常事务");
    }

    /** 计算总工资 = 基本工资 + 奖金。奖金按动态绑定调用子类覆盖后的 getBonus()。 */
    public double getSalary() {
        return baseSalary + getBonus();
    }

    /** 打印员工信息：子类通过 super.showInfo() 复用父类实现，再补充自身信息。 */
    public void showInfo() {
        System.out.println("【员工】姓名=" + name
                + "，工号=" + id
                + "（" + (isValidId() ? "有效" : "无效") + "）"
                + "，部门=" + department
                + "，基本工资=" + baseSalary
                + "，总工资=" + getSalary());
    }

    // ---------- 父类自己的 main：单独测试父类（实验要求 3） ----------
    public static void main(String[] args) {
        System.out.println("===== 父类 Employee 自身测试 =====");
        Employee e = new Employee("张三", 1001, 5000.0, "行政部");
        e.work();
        e.showInfo();
        System.out.println("父类自身测试结束。\n");
    }
}
