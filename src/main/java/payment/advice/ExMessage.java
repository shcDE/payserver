package payment.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExMessage {

    UNDEFINED_ERROR("미정의된 에러입니다.")
    , DB_ERROR_SAVE("객체 저장에 실패했습니다.")
    , MEMBER_ERROR_DUPLICATE("회원이 중복되었습니다.")
    , MEMBER_ERROR_NAME_FORMAT("이름 형식이 잘못 되었습니다.")
    , MEMBER_ERROR_NOT_FOUND("해당 회원이 존재하지 않습니다.")
    , MEMBER_ERROR_DB_SAVE("회원을 DB 저장하는 중에 문제가 발생했습니다.")
    , MEMBER_ERROR_NON_VERIFIED_EMAIL("인증되지 않은 이메일입니다. 인증된 이메일로 가입해주세요.")
    , MEMBER_ERROR_NOT_FOUND_ENG("notFound")
    , EMAIL_ERROR_CODE("이메일 인증코드가 잘못 되었습니다.")
    , EMAIL_ERROR_FORMAT("이메일 형식이 잘못 되었습니다.")
    , EMAIL_ERROR_SEND("회원가입 이메일 인증을 위한 메일 전송에 실패하였습니다.")
    , MEMBER_MYPAGE_ERROR_REGIST("회원 마이페이지 등록에 실패하였습니다.")
    , MEMBER_MYPAGE_ERROR_UPDATE("회원의 마이페이지를 업데이트하는데 실패했습니다.")
    , MEMBER_MYPAGE_ERROR_DELETE("회원의 마이페이지 삭제에 실패했습니다.")
    , MEMBER_ERROR_USER_ID_FORMAT("회원의 아이디 형식이 잘못되었습니다.")
    , MEMBER_ERROR_PASSWORD("비밀번호가 일치하지 않습니다.")
    , MEMBER_MYPAGE_ERROR_NOT_FOUND("해당하는 마이페이지가 없습니다.")
    , MEMBER_MYPAGE_ERROR_INTRO_LENGTH("자기소개란의 입력 가능한 범위 500자를 초과하였습니다.")
    , MEMBER_MYPAGE_ERROR_EXIST("마이페이지가 이미 등록되어 있습니다.")
    , MEMBER_MYPAGE_ERROR_DB("DB에 마이페이지를 저장하는데 실패했습니다.")
    , MEMBER_MYPAGE_IMG_FORMAT("마이페이지 이미지 등록 양식이 잘못되었습니다.")
    , CHAT_ERROR_CREATE("채팅방 생성에 실패했습니다.")
    , CHAT_ERROR_MEMBER_ADD("채팅방 입장에 실패했습니다.")
    , CHAT_ERROR_NOT_FOUND("채팅방 조회에 실패했습니다.")
    , CHAT_ERROR_SEND_CHAT("채팅 전송에 실패했습니다.")
    , RESERVATION_ERROR("코디네이터 예약 실패")
    , RESERVATION_ERROR_HIDE("예약내역을 감추는데 실패했습니다.")
    , RESERVATION_ERROR_FORMAT("예약 양식이 잘못되었습니다.")
    , RESERVATION_ERROR_CONFIRM("예약 확정에 실패했습니다.")
    , RESERVATION_ERROR_PAY("예약 구매 완료에 실패했습니다.")
    , RESERVATION_ERROR_NOT_FOUND("예약을 찾지 못했습니다.")
    , RESERVATION_ERROR_NOT_FOUND_CLIENT("구매자에게서 해당 예약을 찾지 못했습니다.")
    , RESERVATION_ERROR_NOT_FOUND_CRDI("코디네이터에게서 해당 예약을 찾지 못했습니다.")
    , RESERVATION_ERROR_CANCEL_CASE_CLIENT("고객의 예약 취소에 실패했습니다.")
    , RESERVATION_ERROR_CANCEL_CASE_CRDI("고객의 예약 취소에 실패했습니다.")
    , RESERVATION_ERROR_ALREADY_PAY("이미 결제된 예약건입니다.")
    , RESERVATION_ERROR_ALREADY_CANCEL("이미 취소된 예약건입니다. 다시 예약을 진행해주세요.")
    , RESERVATION_ERROR_VIRTUAL_PAY_TYPE("가상계좌로 결제된 건은 코디네이터가 취소할 수 없습니다.")
    , RESERVATION_ERROR_NOT_PAY("고객이 아직 결제를 완료하지 않았습니다.")
    , REVIEW_ERROR_FORMAT("리뷰 요청 폼이 잘못되었습니다.")
    , REVIEW_ERROR_ALREADY_REVIEWED("이미 리뷰를 작성하였습니다.")
    , REVIEW_ERROR_NOT_FOUND("리뷰가 존재하지 않습니다.")
    , REPLY_ERROR_NOT_FOUND("답글이 존재하지 않습니다.")
    , MULTIPART_ERROR_SIZE("사진의 크기가 10MB를 초과합니다.")
    , PAYMENT_ERROR_ORDER_NAME("주문하신 상품 이름이 잘못되었습니다.")
    , PAYMENT_ERROR_ORDER_PRICE("주문하신 상품 금액이 잘못되었습니다.")
    , PAYMENT_ERROR_ORDER_PAY_TYPE("결제수단 선택이 잘못되었습니다.")
    , PAYMENT_ERROR_ORDER_AMOUNT("결제 금액이 잘못되었습니다.")
    , PAYMENT_ERROR_ORDER("결제 관련 오류가 발생했습니다.")
    , PAYMENT_ERROR_ORDER_NOTFOUND("해당 결제 내역을 조회할 수 없습니다.")
    , PAYMENT_ERROR_NOT_PAY("고객이 아직 결제를 완료하지 않았습니다.")
    , PAYMENT_CANCEL_ERROR_NOT_MATCH_AMOUNT("취소 금액과 결제 금액이 다릅니다.")
    , PAYMENT_CANCEL_ERROR_FAIL("알 수 없는 이유로 결제 취소에 실패했습니다.")
    , ACCOUNT_ERROR_WRONG_BANK("요청한 은행과 계설기관이 다릅니다.")
    , ACCOUNT_ERROR_WRONG_NAME("요청한 성함과 예금주명이 일치하지 않습니다.")
    , ACCOUNT_ERROR_WRONG_BIRTHDAY("요청하신 생년월일과 예금주의 생년월일이 일치하지 않습니다.")
    , ACCESS_TOKEN_ERROR_NOT_FOUND("Access Token 을 찾을 수 없습니다.")
    , IMAGE_ERROR_NOT_FOUND("요청하신 이미지파일을 찾을 수 없습니다.")
    , SUBMALL_ERROR_NONE("회원님 앞으로 등록된 서브몰이 없습니다.")
    , SUBMALL_ERROR_WRONG_BUSINESS_NUMBER("잘못된 사업자 유형 입니다.")
    , SUBMALL_ERROR_ALREADY_REGIST("고객님 앞으로 서브몰이 이미 등록되어있습니다.")
    ;

    private final String message;
}