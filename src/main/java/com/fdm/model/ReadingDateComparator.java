package com.fdm.model;

import java.util.Comparator;

public class ReadingDateComparator implements Comparator<Reading> {

	@Override
	public int compare(Reading o1, Reading o2) {
		return o2.getTimeStamp().compareTo(o1.getTimeStamp());

	}

}
