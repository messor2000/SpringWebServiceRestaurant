package epam.project.spring.entity.order;

/**
 * @author Aleksandr Ovcharenko
 */
public class Status {
    public static final String EMPTY = "empty";
    public static final String WAITING_FOR_PAY = "waiting for pay";
    public static final String PAYED = "payed";
    public static final String COOKING = "cooking";
    public static final String COMPLETE = "complete";

    public Status(){}
}
