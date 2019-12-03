package com.inacsys.util;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Robert Arjun
 * @date 16-10-2015 CommonValidate Class is used set the error message to UI
 *
 */
public class CommonValidate {

	public static UIComponent findComponentInRoot(String id) {
		UIComponent ret = null;

		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			UIComponent root = context.getViewRoot();
			ret = findComponent(root, id);
		}

		return ret;
	}

	public static UIComponent findComponent(UIComponent base, String id) {

		UIComponent kid = null;
		UIComponent result = null;
		// Is the "base" component itself the match we are looking for?
		if (id.equals(base.getId())) {
			result = base;
		} else {
			// Search through our facets and children
			Iterator kids = base.getFacetsAndChildren();
			while (kids.hasNext() && (result == null)) {
				kid = (UIComponent) kids.next();
				if (id.equals(kid.getId())) {
					result = kid;
					break;
				}
				result = findComponent(kid, id);
				if (result != null) {
					break;
				}
			}
		}

		return result;

	}

	/*
	 * namePattern allows atleast one alphabet and optional number, optional
	 * special characters ('.-)
	 */
	public static final String namePattern = "^(?=.*[A-Za-z])([a-zA-Z0-9,\\'\\-\\.\\&\\/(\\s)+]*)$";

	public static final String numberPattern = "^(?=.*[0-9])([a-zA-Z0-9,\\'\\-\\.\\,(\\s)+]*)$";

	public static boolean validateName(String name) {
		if (name == null)
			return false;
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	public static boolean validateNumber(String name) {
		if (name == null)
			return false;
		Pattern pattern = Pattern.compile(numberPattern);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/*
	 * Validate if the string is Numeric
	 */
	public static boolean isNumeric(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	public static boolean validateFax(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	public static boolean validateTax(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	public static boolean validatePhone(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	/*
	 * This method will validate the email address.
	 * 
	 * @param expression : Email Address
	 * 
	 * @return boolean : true if the expression matches the pattern w@w.w format
	 */
	public static boolean validateEmail(String expression) {
		String REGEXP_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.[a-zA-Z]{1,20}+";
		Pattern p = Pattern.compile(REGEXP_EMAIL);
		Matcher m = p.matcher(expression);
		return m.matches();
	}
	
	public static final String numberOnlyPattern = "([0-9]+)";
	
	public static boolean validateNumberOnly(String name) {
		if (name == null)
			return false;
		Pattern pattern = Pattern.compile(numberOnlyPattern);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

}
