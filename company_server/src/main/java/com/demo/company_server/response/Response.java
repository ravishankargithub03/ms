package com.demo.company_server.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Response {

	private int statusCode;
	private String message;
	private Object responsePayload;
}
