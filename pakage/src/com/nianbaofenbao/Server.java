package com.nianbaofenbao;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class Server {
	public static void main(String[] args) {
		//1.创建服务类
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		//2.创建缓冲池
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		//3.socket工厂
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
		
		//4.设置管道工厂
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
			ChannelPipeline pipeline = Channels.pipeline();
			//管道里面加入handler
			pipeline.addLast("handler1", new HelloHander());
			return pipeline;
			}
		});
		
		//5.绑定端口
        bootstrap.bind(new InetSocketAddress(10101));
		System.out.println("start!!!");
	}

}
