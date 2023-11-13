package jpabook.jpashop.exception;

public class NotEnoughtStockException extends RuntimeException {

    public NotEnoughtStockException() {
        super();
    }

    public NotEnoughtStockException(String message) {
        super(message);
    }
    //에외 메시지를 포함하는 생성자로, 예외가 발생한 원인을 설명하는 문자열 전달

    public NotEnoughtStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughtStockException(Throwable cause) {
        super(cause);
    }
}
// 사용자 정의 예외 클래스를 만들어 사용하면 프로그램이 특정 조건에 예외를 던지고,
// 그 예외를 적절히 처리하는 코드를 작성할 수 있다.
