package com.newland.boss.kpi.util;

import java.util.Comparator;

import org.springframework.util.StringUtils;

import com.newland.boss.kpi.entity.Resource;

public class ResourceComparetor implements Comparator<Resource> {

	@Override
	public int compare(Resource src, Resource dest) {
		int i = src.getSuperResourceId() - dest.getSuperResourceId();
		if(i == 0) {
			if(StringUtils.isEmpty(src.getResourceOrder()) && StringUtils.isEmpty(dest.getResourceOrder())) {
				return src.getResourceId() - dest.getResourceId();
			}
			if(StringUtils.isEmpty(src.getResourceOrder()) || StringUtils.isEmpty(dest.getResourceOrder())) {
				return 1;
			}
			return Integer.parseInt(src.getResourceOrder()) - Integer.parseInt(dest.getResourceOrder());
		}
		return i;
	}

}
