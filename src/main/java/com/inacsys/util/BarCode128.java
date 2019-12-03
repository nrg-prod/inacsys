package com.inacsys.util;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.managedBean.EmployeePayrollFormMB;
/*import com.itextpdf.text.Document;
 import com.itextpdf.text.DocumentException;
 import com.itextpdf.text.PageSize;
 import com.itextpdf.text.Paragraph;
 import com.itextpdf.text.Rectangle;
 import com.itextpdf.text.pdf.Barcode128;
 import com.itextpdf.text.pdf.PdfWriter;*/
import com.lowagie.text.pdf.PdfDocument;

//Windows solution to view a PDF file
public class BarCode128 {
	static final Logger logger = LoggerFactory.getLogger(BarCode128.class);

	public static void main(String[] args)
			throws /* DocumentException, */IOException {

		try {
			String Str = "a.a";
			logger.debug("1");
			double i = 2;
			String d = "1.655499984741211";
			logger.debug("value----->" + (i / 100));
			logger.debug("");
			logger.debug("Return Value :" + d.substring(0, 2));
			for (String retval : Str.split("\\.")) {
				logger.debug(retval);
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

	}

}
