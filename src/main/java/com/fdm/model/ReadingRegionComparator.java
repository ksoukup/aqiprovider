package com.fdm.model;

import java.util.Comparator;

public class ReadingRegionComparator implements Comparator<Reading> {

	@Override
	public int compare(Reading o1, Reading o2) {
		return o2.getRegionName().compareTo(o1.getRegionName());

	}

}
