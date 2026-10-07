package ExperimentLesson.lesson03;

/**
 * 子类：销售员（Salesman）
 *
 * 实验要点：
 *  1. 覆盖父类的 work()、getBonus()，与父类及其他子类实现有差异。
 *  2. 拥有子类独有域 salesAmount 和独有方法 sell()。
 *  3. 展示多个子类对同一父类方法的不同覆盖实现（多态测试素材）。
 */
public class Salesman extends Employee {

    private double salesAmount;   // 子类独有域：本月销售额（万元）

    public Salesman() {
        System.out.println("  [Salesman 无参构造器] 执行完毕");
    }

    public Salesman(String name, int id, double baseSalary, String department, double salesAmount) {
        super(name, id, baseSalary, department);   // 显式调用父类有参构造器
        this.salesAmount = salesAmount;
        System.out.println("  [Salesman 有参构造器] 本月销售额=" + salesAmount + " 万元");
    }

    // ---------- 子类独有方法 ----------
    public void sell() {
        System.out.println(getName() + " 本月成功签下 " + salesAmount + " 万元订单");
    }

    // ---------- 覆盖父类方法 ----------

    @Override
    public void work() {
        System.out.println(getName() + " 正在拜访客户、洽谈并促成签约");
    }

    /**
     * 覆盖受保护方法：按销售额提成 3%，体现与经理、工程师不同的奖金算法。
     */
    @Override
    protected double getBonus() {
        return super.getBonus() + salesAmount * 10000 * 0.03;
    }
}
//（注：内容由AI生成）
