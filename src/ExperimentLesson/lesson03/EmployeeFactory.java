package ExperimentLesson.lesson03;

/**
 * 工厂模式：EmployeeFactory（较高要求）
 *
 * 作用：把“创建对象”的职责从调用方集中到工厂类中，
 *       调用方只需传入类型与参数，工厂负责决定并实例化具体的子类对象，
 *       返回类型统一为父类型 Employee，从而隐藏对象创建细节。
 */
public class EmployeeFactory {

    /** 员工类型常量 */
    public static final int MANAGER   = 1;
    public static final int ENGINEER  = 2;
    public static final int SALESMAN  = 3;

    /**
     * 静态工厂方法：根据 type 创建对应的子类对象，统一以父类型 Employee 返回。
     */
    public static Employee createEmployee(int type, String name, int id,
                                          double baseSalary, String department,
                                          double extra) {
        switch (type) {
            case MANAGER:
                // extra 在这里代表团队人数
                return new Manager(name, id, baseSalary, department, (int) extra);
            case ENGINEER:
                // extra 在这里暂未使用，技能等级单独传入，可简化：
                return new Engineer(name, id, baseSalary, department, "初级");
            case SALESMAN:
                // extra 在这里代表本月销售额（万元）
                return new Salesman(name, id, baseSalary, department, extra);
            default:
                throw new IllegalArgumentException("未知的员工类型：" + type);
        }
    }

    // 更直观的分组工厂方法（推荐使用）：每个方法创建一类对象
    public static Manager createManager(String name, int id, double baseSalary,
                                        String department, int teamSize) {
        return new Manager(name, id, baseSalary, department, teamSize);
    }

    public static Engineer createEngineer(String name, int id, double baseSalary,
                                          String department, String skillLevel) {
        return new Engineer(name, id, baseSalary, department, skillLevel);
    }

    public static Salesman createSalesman(String name, int id, double baseSalary,
                                          String department, double salesAmount) {
        return new Salesman(name, id, baseSalary, department, salesAmount);
    }
}
