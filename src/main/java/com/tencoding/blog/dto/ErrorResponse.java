package com.tencoding.blog.dto;

import java.util.List;

import lombok.Data;


@Data
public class ErrorResponse {
	private String statusCode;
	private String requestUri;
	private String code;
	private String message;
	private String resultCode;
	private List<CustomError> errorList;

}
