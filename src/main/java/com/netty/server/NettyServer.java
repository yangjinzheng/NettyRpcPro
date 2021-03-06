package com.netty.server;

import com.netty.constant.Contants;
import com.netty.factory.ZookeeperFactory;
import com.netty.handler.SimpleServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup parenGroup = new NioEventLoopGroup();
        EventLoopGroup childrenGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parenGroup, childrenGroup);
            bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, false)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(65535, Delimiters.lineDelimiter()[0]));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new IdleStateHandler(10,10,10, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new SimpleServerHandler());
                            ch.pipeline().addLast(new StringEncoder());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(8080).sync();
            CuratorFramework client = ZookeeperFactory.create();
            InetAddress address = InetAddress.getLocalHost();
            client.create().withMode(CreateMode.EPHEMERAL).forPath(Contants.SERVER_PATH + address.getHostAddress());
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            parenGroup.shutdownGracefully();
            childrenGroup.shutdownGracefully();
        }
    }
}
