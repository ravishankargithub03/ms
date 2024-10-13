package com.demo.company_server.common;

public class URLConstants {

	public class Company {
		public static final String BASE_API = "/company";
		public static final String GET_ALL_COMPANY = "/get/all";
		public static final String GET_SINGLE_AND_MULTIPAL_COMPANY = "/get/{companyId}/{status}";
		public static final String SAVE_COMPANY = "/save";
		public static final String GET_SINGLE_AND_MULTIPAL_COMPANY_WITH_USER = "/get/with/user/{companyId}/{status}";
	}
	
	public class User{
		public static final String BASE_API = "/users";
		public static final String GET_ALL = "/get/all/{status}";
		public static final String GET_SINGLE_AND_MULTIPAL_USER = "/get/{userIds}";
		public static final String GET_USER_WITH_COMPANY = "/get/with/company/{userId}";
		public static final String SAVE = "/save";
		public static final String GET_USER_LIST_BY_SINGLE_AND_MULTIPAL_COMPAY_ID = "/get/by/company/id/{companyIds}/{userStatus}";
	}
}
