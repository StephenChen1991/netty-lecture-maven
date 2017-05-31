package live.chenchen.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by chenchen on 2017/5/15.
 */
public class TestServer {
    public static void main(String[] args) throws  Exception{

        //事件循环组，基于NIO，boss接受连接 并发送给worker线程组
        //ServerBootstrap帮助简化服务端启动的class，
        // 1.定义好bossGroup，workerGroup
        //2.定义好一个通道，通过反射的方式进行创建
        //3.定义子处理器
        //绑定8899端口
        //优雅关闭
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("====invoke=====");
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
