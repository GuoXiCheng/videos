package com.guo.videos.Utils;

import lombok.Data;

import java.util.List;

@Data
public class PagedResult {
	
	private int page;
	private int total;
	private long records;
	private List<?> rows;
}
