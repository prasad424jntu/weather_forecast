package com.mycompany.weather.forecast.exception;

import java.nio.file.AccessDeniedException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResponseFormatter extends ResponseEntityExceptionHandler {

	public ExceptionResponseFormatter() {
		super();
	}

	@Override
	protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return handleExceptionInternal(ex, "Bad Request", headers, HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(final Exception ex, final WebRequest request) {
		System.out.println("request" + request.getUserPrincipal());
		return new ResponseEntity<Object>("Access denied", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final ExceptionRes excRes = new ExceptionRes(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		return new ResponseEntity<Object>(excRes, new HttpHeaders(), excRes.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		final ExceptionRes excRes = new ExceptionRes(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		return new ResponseEntity<Object>(excRes, new HttpHeaders(), excRes.getStatus());
	}

	// 405
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {

		final ExceptionRes excRes = new ExceptionRes(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage());
		return new ResponseEntity<Object>(excRes, new HttpHeaders(), excRes.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		final ExceptionRes excRes = new ExceptionRes(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage());
		return new ResponseEntity<Object>(excRes, new HttpHeaders(), excRes.getStatus());
	}

	// 500
	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
		return handleExceptionInternal(ex, "Server side error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		final ExceptionRes excRes = new ExceptionRes(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
		return new ResponseEntity<Object>(excRes, new HttpHeaders(), excRes.getStatus());
	}

	@ExceptionHandler({ CityDataNotFound.class })
	public ResponseEntity<Object> handle(final Exception ex, final WebRequest request) {
		final ExceptionRes excRes = new ExceptionRes(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		return new ResponseEntity<Object>(excRes, new HttpHeaders(), excRes.getStatus());
	}

}
