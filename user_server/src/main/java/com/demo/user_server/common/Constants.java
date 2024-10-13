package com.demo.user_server.common;

public class Constants {

	public static final int OK = 200;
	public static final int NO_CONTENT = 204; // No Content
	public static final int UNAUTHORIZED_ACCESS = 401;
	public static final int INVALID_INPUT = 422; // Unprocessable Entity
	public static final int VALIDATION_ERROR = 10021;
	public static final int DATA_SAVING_ERROR = 10025; // Data Saving Error
	public static final int INTERNAL_SERVER_ERROR = 500;
	
	public static final Integer ACTIVE = 1;
	public static final Integer INACTIVE = 2;
	public static final Integer DRAFT = 3;
	public static final String ACTIVE_TAG = "Active";
	public static final String INACTIVE_TAG = "Inactive";
	public static final String DRAFT_TAG = "Draft";

	public static final Integer ALL = 20;//use this status when retrival any type is all
	
	public static final String[] statuses = {"","Active", "Inactive", "Draft"};
	
	public static final String DATE_FORMAT = "MM-dd-yyyy";
	public static final String DATE_FORMAT_DB = "yyyy-MM-dd";
	public static final String DATE_FORMAT_UI_MM_DD_YYYY_HH_MM = "MM-dd-yyyy HH:mm";
	public static final String UI_FORMATTED_DATE= "dd-MM-yyyy HH:mm"; // currently using
	public static final String DATE_FORMAT_UI = "MM-dd-yyyy hh:mm";
	public static final String DAY_FORMAT = "EEE";
	public static final String UI_DATE_FORMAT = "dd-MM-yyyy";
	public static final String DB_DATE_TIME="yyyy-MM-dd HH:mm:ss.SSSSSS";
	
	public class CompanyCategory {
		public static final String PARENT_COMPANY = "Parent Company";
		public static final String BUSINESS_UNIT = "Business Unit";
		public static final String PROJECT_OWNER = "Project Owner";
		public static final String SUPPLIER_SUB_CONTRACTOR = "Supplier/Sub_Contractor";
	}	
	public class ResponseMessages{
		public static final String SERVER_ERROR_MESSAGE = "Internal Server Error";
		public static final String FETCHED_MESSAGE = "fetched successfully";
		public static final String SAVE_MESSAGE = "Saved successfully";
		public static final String UNABLE_SAVE_MESSAGE = "Unable to save";
		public static final String DELETED_MESSAGE = "Deleted successfully";
		public static final String UPDATED_MESSAGE = "Updated successfully";
		public static final String ACTIVATED_MESSAGE = "Activated successfully";
		public static final String DEACTIVATED_MESSAGE = "Deactivated successfully";
		public static final String ARCHIVED_MESSAGE = "Archived successfully";
		public static final String UNABLE_TO_ARCHIVE_MESSAGE = "Unable To Archive";
		public static final String SEND_BACK_TO_REQUESTER_MESSAGE = "The request has been sent back to the requester";
		public static final String HOLD_MESSAGE = "Record is now on hold";
		public static final String REJECTED_MESSAGE = "Record rejected";
		public static final String APPROVED_MESSAGE = "Approved successfully";
		public static final String DOWNLOADED_MESSAGE = "Downloaded successfully";
		public static final String SUBMIT_MESSAGE = "Record submitted successfully";
		public static final String ITEM_ADD_MESSAGE = "Item added successfully";
		public static final String DATA_RETRIVAL_MESSAGE = "Data Retrieveed";
		public static final String UPLOAD_MESSAGE = "Uploaded successfully";
		public static final String TRANSMIT_MESSAGE = "Transmitted successfully";
		public static final String RE_SEND_MESSAGE = "Re-send successfully";
		public static final String GENERATION_OF_PO_MESSAGE = "PO Generated successfully";
		public static final String UPLOADING_SIGNATURE_MESSAGE = "Signature uploaded successfully";
		public static final String EMAIL_SENT_MESSAGE = "Email sent successfully";
		public static final String GENERATION_OF_REPEAT_PO_MESSAGE = "Repeat PO Generated successfully";
		public static final String MANDATORY_FIELD_REQUIRED_MESSAGE = "Please complete all required fields before saving";
		public static final String USED_RECORD_MESSAGE = "Record is already in use";
		public static final String DATA_NOT_AVAILABLE_MESSAGE = "No data found";
		public static final String INPUT_REQUIRED_MESSAGE = "Provide search input";
		public static final String ONE_SCHEDULE_ITEM_REQUIRED_MESSAGE = "Select at least one schedule item";
		public static final String WITHOUT_SAVING_SUBMIT_MESSAGE = "Please save before submitting";
		public static final String INVALID_DATA_MESSAGE = "Invalid data";
		public static final String INVALID_INPUT_MESSAGE = "Invalid Input";
		public static final String ALPHANUMERIC_INPUT_MESSAGE = "Input must be alphanumeric";
	}
}
