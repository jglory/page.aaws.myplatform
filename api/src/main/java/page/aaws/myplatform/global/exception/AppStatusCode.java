package page.aaws.myplatform.global.exception;

public interface AppStatusCode {
    int value();

    String message();

    boolean isError();
}
