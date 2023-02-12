package com.amagames.vampire;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.*;

public class DFAFA extends SimpleChannelInboundHandler<String> {
	// Key, Result
	static ConcurrentHashMap<String, String> a = new ConcurrentHashMap<String, String>();
	static ExecutorService b = Executors.newFixedThreadPool(4);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String[] args = msg.split(" ");
		if (args[0].equalsIgnoreCase("SET")) {
			return;
		}
		else {

		}
		a.put(args[0], msg.replaceAll(args[0], ""));
		Logger.log(msg, LogLevel.INFO);
	}

	public static String getResult(String key, String defaultValue) {
		try {
			for (int s = 1; s <= 100; s = s + 1) {
				final String value = a.get(key);
				if (value != null) {
					a.remove(key);
					return value;
				}
				Thread.sleep(5L);
			}
		}
		catch (Exception e) {

		}
		System.out.println("END");
		return defaultValue;
	}

}