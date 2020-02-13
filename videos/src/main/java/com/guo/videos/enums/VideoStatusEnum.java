package com.guo.videos.enums;

public enum VideoStatusEnum {
	
	SUCCESS(0),		// 发布成功
	FORBID(1);		// 禁止播放，管理员操作
	
	public final int value;
	
	VideoStatusEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
