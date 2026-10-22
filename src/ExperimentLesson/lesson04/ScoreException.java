package ExperimentLesson.lesson04;

/**
 * 自定义异常类：继承自 Exception（受检异常）
 */
public class ScoreException extends Exception {
    public ScoreException() {
        super();
    }

    public ScoreException(String message) {
        super(message);
    }

    // 较高要求：带原因构造，支持异常链
    public ScoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
