package com.amagames.vampire;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Logger {

	public static void log(String message, LogLevel logLevel) {
		System.out.println(logLevel.getPrefix() + message);
	}

	public static void printLogo() {
		int height = 30;
		int width = 200;
		int startX = 2;
		int startY = 20;
		Font font = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30);
		StringBuilder stringBuilder1 = new StringBuilder("\n");
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics2D.setFont(font);
		graphics2D.drawString("VAMPIRE", startX, startY);
		for (int y = 0; y < height; y = y + 1) {
			StringBuilder stringBuilder2 = new StringBuilder();
			stringBuilder2.append(Color.PURPLE);
			for (int x = 0; x < width; x++) {
				stringBuilder2.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "/");
			}
			if (stringBuilder2.toString().trim().isEmpty()) {
				continue;
			}
			stringBuilder1.append("\n" + stringBuilder2);
		}
		stringBuilder1.append("\n");
		System.out.println(stringBuilder1);
	}

}
