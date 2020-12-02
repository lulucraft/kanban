package fr.cfai_lda.lbesson.kanban.util;

public class Checker {

	/**
	 * Check if input is an integer
	 * 
	 * @param check
	 * @return boolean
	 */
	public static boolean isInteger(String toCheck)
	{
		try {
			Integer.parseInt(toCheck);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
