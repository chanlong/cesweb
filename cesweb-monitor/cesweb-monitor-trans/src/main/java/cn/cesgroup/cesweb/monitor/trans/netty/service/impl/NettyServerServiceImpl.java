package cn.cesgroup.cesweb.monitor.trans.netty.service.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cesgroup.cesweb.monitor.trans.config.ConfigReader;
import cn.cesgroup.cesweb.monitor.trans.framework.utils.Constants;
import cn.cesgroup.cesweb.monitor.trans.netty.handler.TxCoreServerHandler;
import cn.cesgroup.cesweb.monitor.trans.netty.service.NettyServerService;
import cn.cesgroup.cesweb.monitor.trans.netty.service.NettyService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author LCN on 2017/6/30.
 */
@Service
public class NettyServerServiceImpl implements NettyServerService, DisposableBean {

	private Logger logger = LoggerFactory.getLogger(NettyServerServiceImpl.class);

	private EventLoopGroup bossGroup;

	private EventLoopGroup workerGroup;

	private TxCoreServerHandler txCoreServerHandler;

	@Autowired
	private NettyService nettyService;

	@Autowired
	private Executor threadPool;

	@Autowired
	private ConfigReader configReader;

	@Override
	public void start() {
		final int heartTime = configReader.getTransactionNettyHeartTime() + 10;
		txCoreServerHandler = new TxCoreServerHandler(threadPool, nettyService);
		bossGroup = new NioEventLoopGroup(50);
		workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) {
							ch.pipeline().addLast("timeout",
									new IdleStateHandler(heartTime, heartTime, heartTime, TimeUnit.SECONDS));

							ch.pipeline().addLast(new LengthFieldPrepender(4, false));
							ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

							ch.pipeline().addLast(txCoreServerHandler);
						}
					});

			// Start the server.
			b.bind(Constants.socketPort);
			logger.info("Socket started on port(s): " + Constants.socketPort + " (socket)");

		}
		catch (Exception e) {
			// Shut down all event loops to terminate all threads.
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
		}
		if (bossGroup != null) {
			bossGroup.shutdownGracefully();
		}

	}

	@Override
	public void destroy() {
		close();
	}

}
