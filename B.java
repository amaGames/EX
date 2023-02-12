package com.amagames.vampire;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class B {

	static final String HOST = "127.0.0.1";
	static final int PORT = 30000;
	static String clientName;

	public static void main(String[] args) throws Exception {

		/*
		 * Get name of the user for this chat session.
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		if (scanner.hasNext()) {
			clientName = scanner.nextLine();
			System.out.println("Welcome " + clientName);
		}

		/*
		 * Configure the client.
		 */

		// Since this is client, it doesn't need boss group. Create single group.
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group) // Set EventLoopGroup to handle all eventsf for client.
					.channel(NioSocketChannel.class)// Use NIO to accept new connections.
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline p = ch.pipeline();
							/*
							 * Socket/channel communication happens in byte streams. String decoder &
							 * encoder helps conversion between bytes & String.
							 */
							p.addLast(new StringDecoder());
							p.addLast(new StringEncoder());

							// This is our custom client handler which will have logic for chat.
							p.addLast(new ChatClientHandler());

						}
					});

			// Start the client.
			ChannelFuture f = b.connect(HOST, PORT);

			/*
			 * Iterate & take chat message inputs from user & then send to server.
			 */
			boolean a = true;
			Channel channel = f.channel();
			while (scanner.hasNext()) {
				long time1 = System.nanoTime();
				String input = scanner.nextLine();
				channel.writeAndFlush(clientName + " " + input);
				String result = ChatClientHandler.getResult(clientName, "Default");
				System.out.println(result);
				long time2 = System.nanoTime();
				System.out.println(time2 - time1);
			}
			channel.flush();
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}

}
