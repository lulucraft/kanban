package fr.cfai_lda.lbesson.kanban.util;

import java.util.Date;

public class DateUtil {

	private Date dateToConvert;

	public DateUtil(java.sql.Date date) {
		this.dateToConvert = date;
	}

	public DateUtil(Date date) {
		this.dateToConvert = date;
	}

	public java.sql.Date toSQLDate() {
		return new java.sql.Date(dateToConvert.getTime());
	}

	public Date toDate() {
		return new Date(dateToConvert.getTime());
	}
}
