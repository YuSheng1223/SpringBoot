package spring.boot.crud.exception;

/**
 * @program: sprng-boot-crud
 * @description: 自定义异常
 * @author: Mr.L
 * @create: 2019-01-23 23:08
 **/
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("当前登录用户不存在~");
    }
}
