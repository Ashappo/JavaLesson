package ExperimentLesson.lesson02;

public class Clazz {
    private String className;
    //聚合关系：班级包含多个学生，Student作为另外一个类，作为成员变量
    private Student[] students;
    private int count; //记录班级实际学生数量

    public Clazz(String className, int maxSize){
        this.className = className;
        students = new Student[maxSize];
        count = 0;
    }

    //添加学生到班级
    public void addStudent(Student s){
        if(count < students.length){
            students[count++] = s;
        }else{
            System.out.println("班级已满");
        }
    }

    //打印班级全部学生
    public void showClassInfo(){
        System.out.println("班级名称："+className);
        for(int i=0; i<count; i++){
            students[i].printStudent();
        }
    }
}

