package com.zlm.project.connect.response;

import com.zlm.project.connect.exception.ApiException;
import com.zlm.project.connect.exception.CustomException;
import com.zlm.project.other.AppConstants;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * @author Milla
 * @create 2019/3/27
 */
public class ResponseTransformer {

    private static final int SUCCESS = 1;

    // -------------------------------------------
    public static <T> ObservableTransformer<Response<T>, T> handleResult() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }

    // -------------------------------------------
    public static <T> ObservableTransformer<Response<T>, T> handleLoginResult() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseLoginFunction<>());
    }

    // -------------------------------------------

    /**
     * Local exception use.(Network,JSON error...)
     *
     * @param <T>
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends Response<T>>> {

        @Override
        public ObservableSource<? extends Response<T>> apply(Throwable throwable) {
            return Observable.error(CustomException.handleException(throwable));
        }
    }

    // -------------------------------------------

    /**
     * Server api response.
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<Response<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(Response<T> response) {
            int code = response.getErrorCode();
            String message = response.getErrorMsg();
            if (code == SUCCESS) {
                return Observable.just(response.getData());
            } else {
                return Observable.error(new ApiException(code, message));
            }
        }
    }

    // -------------------------------------------

    /**
     * Server api response for login.
     *
     * @param <T>
     */
    private static class ResponseLoginFunction<T> implements Function<Response<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(Response<T> response) {
            int code = response.getErrorCode();
            String message = response.getErrorMsg();
            if (code == SUCCESS || code == AppConstants.NOT_CERTIFIED) {
                return Observable.just(response.getData());
            } else {
                return Observable.error(new ApiException(code, message));
            }
        }
    }

    // -------------------------------------------
}
