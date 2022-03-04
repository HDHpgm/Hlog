package com.dev.hlog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode{
    POST_TITLE_NULL("false","POST-TITLE-NULL", 202, "포스트 제목을 입력해주세요"),
    POST_CONTENT_NULL("false", "POST-CONTENT-NULL", 202, "포스트 내용을 작성해주세요"),
    SERVER_ERROR("false", "SERVER-ERROR", 500, "서버 처리 에러"),
    USER_NULL("false", "USER-NULL", 202, "로그인되어 있지 않습니다. 잘못된 접근입니다."),
    POST_IS_NULL("false", "POST-IS-NULL", 404, "포스트가 존재하지 않습니다."),
    POST_DELETE_FAIL("false", "POST-DELETE-FAIL", 202, "포스트 삭제에 실패했습니다."),
    POST_UPDATE_FAIL("false", "POST-UPDATE-FAIL", 500, "포스트 수정에 실패했습니다.");

    private final String success;
    private final String errorCode;
    private final int httpStatus;
    private final String errorMessage;

}
