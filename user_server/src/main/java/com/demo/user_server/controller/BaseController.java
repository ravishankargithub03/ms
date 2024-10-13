package com.demo.user_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.user_server.common.Constants;
import com.demo.user_server.response.Response;

public class BaseController {

	public ResponseEntity<Response> getOKResponseEntity(Response response) {
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
// =========================================================================

	public ResponseEntity<Response> getInvalidDataResponseEntity(String validationResult) {
		return new ResponseEntity<>(new Response(Constants.INVALID_INPUT, validationResult, null),
				HttpStatus.BAD_REQUEST);
	}

// =========================================================================

	public ResponseEntity<Response> getInvalidAuthCodeResponseEntity(String authCode) {
		return new ResponseEntity<>(new Response(Constants.INVALID_INPUT, "Invalid AuthCode [" + authCode + "]", null),
				HttpStatus.OK);
	}

// =========================================================================

	public ResponseEntity<Response> getUnauthorizedAccessResponseEntity() {
		return new ResponseEntity<>(new Response(Constants.UNAUTHORIZED_ACCESS, "Unauthorized Access", null),
				HttpStatus.UNAUTHORIZED);
	}

// =========================================================================

	public ResponseEntity<Response> getUnauthorizedAccessResponseEntity(String message) {
		return new ResponseEntity<>(new Response(Constants.UNAUTHORIZED_ACCESS, "Unauthorized Access", message),
				HttpStatus.UNAUTHORIZED);
	}

// =========================================================================

	public ResponseEntity<Response> getOKResponseEntity(String message) {
		return new ResponseEntity<>(new Response(Constants.OK, message, null), HttpStatus.OK);
	}

// =========================================================================

	public ResponseEntity<Response> getInvalidInputResponseEntity(String message) {
		return new ResponseEntity<>(new Response(Constants.INVALID_INPUT, message, null), HttpStatus.OK);
	}

// =========================================================================
}
