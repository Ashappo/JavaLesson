package ExperimentLesson.lesson03;

/**
 * 子类：经理（Manager）
 *
 * 实验要点：
 *  1. 覆盖父类的 work()、getBonus()、showInfo()，代码与父类有差异。
 *  2. 通过 super 调用父类的构造器、方法和域（super(...)、super.work()、super.getBonus()、super.showInfo()）。
 *  3. 拥有子类独有的域 teamSize 和独有方法 assignTask()/teamMeeting()。
 */
public class Manager extends Employee {

    private int teamSize;   // 子类独有域：管理团队人数

    /**
     * 无参构造器：super() 被隐式调用（可省略），会先执行父类无参构造器，
     * 再执行本构造器，以此观察调用次序。
     */
    public Manager() {
        System.out.println("  [Manager 无参构造器] 执行完毕");
    }

    /**
     * 有参构造器：super(参数) 显式调用父类有参构造器，必须放在第一行。
     */
    public Manager(String name, int id, double baseSalary, String department, int teamSize) {
        super(name, id, baseSalary, department);   // 调用父类有参构造器
        this.teamSize = teamSize;
        System.out.println("  [Manager 有参构造器] 管理 " + teamSize + " 人团队");
    }

    // ---------- 子类独有方法 ----------
    public void assignTask() {
        System.out.println(getName() + " 给 " + teamSize + " 名成员分配任务");
    }

    public void teamMeeting() {
        System.out.println(getName() + " 召开团队例会");
    }

    // ---------- 覆盖父类方法 ----------

    @Override
    public void work() {
        System.out.println(getName() + " 正在做计划、组织、协调等管理工作");
    }

    /**
     * 覆盖父类的受保护方法：用 super 取父类奖金，再加管理津贴。
     */
    @Override
    protected double getBonus() {
        return super.getBonus() + 1000.0;   // super 调用父类 getBonus()
    }

    /**
     * 覆盖父类 showInfo()：先复用父类实现（super.showInfo()），再补经理特有信息。
     */
    @Override
    public void showInfo() {
        super.showInfo();                    // 复用父类打印通用信息
        System.out.println("      [经理补充] 团队规模=" + teamSize + " 人");
    }
}
