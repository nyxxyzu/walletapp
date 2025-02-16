package walletapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleAutomaticValidationException(final MethodArgumentNotValidException e) {
		return new ApiError("BAD_REQUEST", e.getMessage(), "Incorrectly made request", LocalDateTime.now());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handleNotFoundException(final NotFoundException e) {
		return new ApiError("NOT_FOUND", e.getMessage(), "The required object was not found", LocalDateTime.now());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException(final ValidationException e) {
		return new ApiError("BAD_REQUEST", e.getMessage(), "Incorrectly made request", LocalDateTime.now());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
		return new ApiError("BAD_REQUEST", e.getMessage(), "Invalid JSON input", LocalDateTime.now());
	}
}
