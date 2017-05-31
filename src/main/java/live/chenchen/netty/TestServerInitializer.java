package live.chenchen.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http2.Http2Codec;

/**
 * Created by Administrator on 2017/5/15.
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //对外部的请求和响应进行编解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //自定义代码
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());

    }
}
