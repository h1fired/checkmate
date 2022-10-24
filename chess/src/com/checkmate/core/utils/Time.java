package com.checkmate.core.utils;

public class Time {
	public static long SECOND = 1000000000l;
	
	public static long get() {
		return System.nanoTime();
	}
}
