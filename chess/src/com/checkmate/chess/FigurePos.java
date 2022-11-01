package com.checkmate.chess;

import java.util.Arrays;

public class FigurePos {
	
	private static int backup_map[][] = {
			{25, 24, 23, 22, 21, 23, 24, 25},
			{26, 26, 26, 26, 26, 26, 26, 26},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{16, 16, 16, 16, 16, 16, 16, 16},
			{15, 14, 13, 12, 11, 13, 14, 15},
		};
	
	public static int map[][] = {
		{25, 24, 23, 22, 21, 23, 24, 25},
		{26, 26, 26, 26, 26, 26, 26, 26},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{16, 16, 16, 16, 16, 16, 16, 16},
		{15, 14, 13, 12, 11, 13, 14, 15},
	};
	
	public static void reset() {
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				map[i][j] = backup_map[i][j];
			}
			
		}
		
	}
	
	public static void print() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
