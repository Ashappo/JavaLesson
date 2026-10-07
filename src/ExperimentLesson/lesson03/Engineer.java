package ExperimentLesson.lesson03;

/**
 * 子类：工程师（Engineer）
 *
 * 实验要点：
 *  1. 覆盖父类的 work()、getBonus()，与父类及其他子类实现有差异。
 *  2. work() 中先用 super.work() 复用父类行为，再追加本类行为，展示 super 调用父类方法。
 *  3. 拥有子类独有域 skillLevel 和独有方法 code()/debug()。
 */
public class Engineer extends Employee {

    private String skillLevel;   // 子类独有域：技能等级（初级/中级/高级）

    public Engineer() {
        System.out.println("  [Engineer 无参构造器] 执行完毕");
    }

    public Engineer(String name, int id, double baseSalary, String department, String skillLevel) {
        super(name, id, baseSalary, department);   // 显式调用父类有参构造器
        this.skillLevel = skillLevel;
        System.out.println("  [Engineer 有参构造器] 技能等级=" + skillLevel);
    }

    // ---------- 子类独有方法 ----------
    public void code() {
        System.out.println(getName() + " 正在编写业务代码");
    }

    public void debug() {
        System.out.println(getName() + " 正在排查并修复 Bug");
    }

    // ---------- 覆盖父类方法 ----------

    @Override
    public void work() {
        super.work();               // 先执行父类的通用工作内容
        System.out.println(getName() + " 随后投入研发与技术攻关");
    }

    /**
     * 覆盖受保护方法：技能越高，奖金系数越高。
     */
    @Override
    protected double getBonus() {
        double ratio = switch (skillLevel) {
            case "高级" -> 0.30;
            case "中级" -> 0.20;
            default    -> 0.12;     // 初级
        };
        return super.getBonus() + getBaseSalary() * (ratio - 0.10);
    }
}
//（注：内容由AI生成）
