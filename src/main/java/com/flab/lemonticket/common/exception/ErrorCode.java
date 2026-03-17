package com.flab.lemonticket.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C002", "서버 내부 오류가 발생했습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C003", "허용되지 않은 HTTP 메서드입니다."),

    // Auth
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "A001", "인증이 필요합니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "A002", "접근 권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A003", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "A004", "만료된 토큰입니다."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "회원을 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "M002", "이미 사용 중인 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "M003", "비밀번호가 일치하지 않습니다."),

    // Event
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "E001", "공연을 찾을 수 없습니다."),
    EVENT_ALREADY_CANCELLED(HttpStatus.BAD_REQUEST, "E002", "이미 취소된 공연입니다."),

    // Seat
    SEAT_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", "좌석을 찾을 수 없습니다."),
    SEAT_ALREADY_BOOKED(HttpStatus.CONFLICT, "S002", "이미 예매된 좌석입니다."),
    SEAT_ALREADY_LOCKED(HttpStatus.CONFLICT, "S003", "다른 사용자가 선점 중인 좌석입니다."),

    // Booking
    BOOKING_NOT_FOUND(HttpStatus.NOT_FOUND, "B001", "예매 정보를 찾을 수 없습니다."),
    BOOKING_ALREADY_CANCELLED(HttpStatus.BAD_REQUEST, "B002", "이미 취소된 예매입니다."),
    BOOKING_EXPIRED(HttpStatus.BAD_REQUEST, "B003", "만료된 예매입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
