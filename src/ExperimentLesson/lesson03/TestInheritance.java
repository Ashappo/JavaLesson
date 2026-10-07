package ExperimentLesson.lesson03;

/**
 * 测试类：TestInheritance
 *
 * 针对实验要求逐项测试：
 *  1. 构造器调用次序（有参/无参）
 *  2. super 关键字：在子类中调用父类的构造器、方法与域
 *  3. 多态：引用类型为父类型，用不同子类实例化对象
 *  4. 子类独有方法
 *  5. protected 域和方法在子类中的访问
 *  6. 工厂模式创建对象（较高要求）
 *
 * 运行：TestInheritance 中的 main；父类自身测试见 Employee.main。
 */
public class TestInheritance {

    public static void main(String[] args) {

        System.out.println("========== 1. 构造器调用次序测试（有参） ==========");
        System.out.println("创建 Manager 对象，观察 父类构造器 → 子类构造器 的先后：");
        Employee m = new Manager("李经理", 2001, 8000.0, "技术部", 6);
        System.out.println();

        System.out.println("========== 2. 构造器调用次序测试（无参） ==========");
        System.out.println("创建 Salesman 无参对象，观察调用次序：");
        Salesman s0 = new Salesman();
        System.out.println();

        System.out.println("========== 3. super 关键字：在子类中调用父类方法 ==========");
        // 子类 showInfo() 内部通过 super.showInfo() 复用父类打印，再补自身信息
        m.showInfo();
        System.out.println();

        System.out.println("========== 4. 多态：父类型引用，不同子类实例化 ==========");
        // 引用类型统一为父类型 Employee，实际对象分别是三个子类
        Employee e1 = new Manager("王经理", 3001, 9000.0, "运营部", 4);
        Employee e2 = new Engineer("张工", 4001, 7000.0, "研发部", "高级");
        Employee e3 = new Salesman("刘销", 5001, 5000.0, "市场部", 120.0);

        // 同一行代码 employee.work()，因动态绑定而输出各自实现
        System.out.println("--- 对父类型数组依次调用 work()，观察覆盖后的差异 ---");
        Employee[] staff = { e1, e2, e3 };
        for (Employee em : staff) {
            em.work();
        }
        System.out.println();

        System.out.println("--- 对父类型数组依次调用 getSalary()（内部动态绑定各自 getBonus）---");
        for (Employee em : staff) {
            System.out.println(em.getName() + " 的工资 = " + em.getSalary());
        }
        System.out.println();

        System.out.println("========== 5. 子类独有方法（需向下转型到具体子类） ==========");
        // 通过强转调用子类独有方法
        ((Manager) e1).assignTask();
        ((Manager) e1).teamMeeting();
        ((Engineer) e2).code();
        ((Engineer) e2).debug();
        ((Salesman) e3).sell();
        System.out.println();

        System.out.println("========== 6. protected 域与方法在子类中的访问 ==========");
        // department 是父类的 protected 域，Manager 的 showInfo 内部可访问；
        // 这里再演示子类构造后直接读取父类 protected 域
        System.out.println("通过子类对象直接访问父类 protected 域 department = " + m.department);
        System.out.println("通过子类对象直接访问父类 protected 域 department(e2) = " + e2.department);
        System.out.println();

        System.out.println("========== 7. 工厂模式创建对象（较高要求） ==========");
        Employee fm = EmployeeFactory.createManager("赵总", 6001, 10000.0, "总经办", 10);
        Employee fe = EmployeeFactory.createEngineer("钱工", 6002, 7500.0, "研发部", "中级");
        Employee fs = EmployeeFactory.createSalesman("孙销", 6003, 5200.0, "市场部", 200.0);
        fm.work();
        fe.work();
        fs.work();
        System.out.println("工厂创建的对象类型：" + fm.getClass().getSimpleName()
                + "、" + fe.getClass().getSimpleName()
                + "、" + fs.getClass().getSimpleName());
        System.out.println();

        System.out.println("========== 8. 汇总展示：调用各对象 showInfo() ==========");
        for (Employee em : new Employee[]{ e1, e2, e3, fm, fe, fs }) {
            em.showInfo();
        }
        System.out.println("\n全部测试完成。");
    }
}
//（注：内容由AI生成）
