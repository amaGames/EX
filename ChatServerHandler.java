package com.amagames.vampire;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

	static final List<Channel> channels = new ArrayList<>();

	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		Logger.log("The connection was established.\nName: " + ctx.name(), LogLevel.LOG);
		channels.add(ctx.channel());
	}

	/*
	 * When a message is received from client, send that message to all channels.
	 * FOr the sake of simplicity, currently we will send received chat message to
	 * all clients instead of one specific client. This code has scope to improve to
	 * send message to specific client as per senders choice.
	 */
	@Override
	public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String[] args = msg.split(" ");
		Logger.log("The command was executed.\nCommand: " + msg, LogLevel.LOG);
		if (args[1].equalsIgnoreCase("SET")) {
			Vampire.getContainer().getRankTable().put(args[2], args[3]);
			ctx.channel().writeAndFlush("SET " + args[0]);
		}
		else if (args[1].equalsIgnoreCase("GET")) {
			ctx.channel().writeAndFlush(args[0] + " " + Vampire.getContainer().getRankTable().get(args[2]));
		}
	}

	/*
	 * In case of exception, close channel. One may chose to custom handle exception
	 * & have alternative logical flows.
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("Closing connection for client - " + ctx);
		ctx.close();
	}

}
