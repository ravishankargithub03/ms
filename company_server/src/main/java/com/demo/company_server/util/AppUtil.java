package com.demo.company_server.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.Table;

public class AppUtil {

	// =========================================================================

//		public static String getStartMethodMessage(String logTag) {
//			return logTag + "START of the method ";
//		}
	//
//		// =========================================================================
	//
//		public static String getEndMethodMessage(String logTag) {
//			return logTag + "END of the method ";
//		}

		// =========================================================================

		public static String getMethodName() {
			return Thread.currentThread().getStackTrace()[2].getMethodName().concat("():");
		}

		// =========================================================================

		public static String[] getStringArray(List<String> stringsList) {
			String[] stringArray = new String[stringsList.size()];
			for (int i = 0; i < stringsList.size(); i++) {
				stringArray[i] = stringsList.get(i);
			}
			return stringArray;
		}

		// =========================================================================

		public static String removeTrailingComma(String str) {
			if (str != null) {
				str = str.trim();
				str = str.replaceAll(",$", "");
			}
			return str;
		}

		// =========================================================================

		public static boolean isNotNull(String str) {
			if (str != null && str.trim().length() > 0) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(Long id) {
			if (id != null && id.longValue() > 0) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(Integer id) {
			if (id != null && id.intValue() > 0) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(BigDecimal value) {
			if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(Double id) {
			if (id != null && id.doubleValue() > 0) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(List<?> list) {
			if (list != null && !list.isEmpty()) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(Set<?> set) {
			if (set != null && !set.isEmpty()) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(Map<?, ?> map) {
			if (map != null && !map.isEmpty()) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(String[] strArray) {
			if (strArray != null && strArray.length > 0) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(Long[] longArray) {
			if (longArray != null && longArray.length > 0) {
				return true;
			}
			return false;
		}

		// =========================================================================

		public static boolean isNotNull(Map<String, Object> map, boolean isTDMap) {

			if (map != null && !map.isEmpty()) {
				if (isTDMap) {
					Set<String> keySet = map.keySet();
					for (String key : keySet) {
						if (key.contains("component")) {
							return true;
						} else if (key.contains("additionalProp")) {
							return false;
						}
					}
				} else {
					return true;
				}
			}
			return false;
		}

		// =========================================================================

		// https://www.htmlquick.com/reference/mime-types.html
		public static String getContentType(String fileName) {
			String extension = getFileExtension(fileName);

			if (extension.equalsIgnoreCase("pdf")) {
				return "application/pdf";
			} else if (extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jpg")) {
				return "image/jpeg";
			} else if (extension.equalsIgnoreCase("png")) {
				return "image/png";
			} else if (extension.equalsIgnoreCase("gif")) {
				return "image/gif";
			} else if (extension.equalsIgnoreCase("bmp")) {
				return "image/bmp";
			}
			return "application/octet-stream";
		}

		// =========================================================================

		public static String getFileExtension(String fileName) {
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
			return extension;
		}

		// =========================================================================

		public static String getTemplateId() {
			// String templateId = UUID.randomUUID().toString();
			// templateId = templateId.substring(0, 14);
			String templateId = "DT";
			templateId = templateId + System.currentTimeMillis();
			return templateId;
		}

		// =========================================================================

		public static String doCamelCase(String originalString) {
			String[] parts = originalString.split("_");
			StringBuilder convertedString = new StringBuilder();
			for (int i = 0; i < parts.length; i++) {
				if (i == 0) {
					convertedString.append(parts[i].toLowerCase());
				} else {
					convertedString.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1).toLowerCase());
				}
			}
			return convertedString.toString();
		}

		// =========================================================================

		public static LocalDate toLocalDate(Date date) {
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		// =========================================================================

		public static LocalDateTime toLocalDateTime(Date date) {
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		}

		// =========================================================================

		public static String getTableName(Class<?> className) {
			if (className.isAnnotationPresent(Table.class)) {
				Table tableAnnotation = className.getAnnotation(Table.class);
				return tableAnnotation.name();
			} else {
				return null;
			}
		}


		// =========================================================================
		public static Date getFirstDateOfMonth(String monthYear) throws ParseException {
			SimpleDateFormat inputFormat = new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH);
			
			Date parsedDate = inputFormat.parse(monthYear);
			
			SimpleDateFormat outputFormat = new SimpleDateFormat("01-MMMM-yyyy", Locale.ENGLISH);
			
			String formattedDate = outputFormat.format(parsedDate);
			
			return outputFormat.parse(formattedDate);
		}
		
}
