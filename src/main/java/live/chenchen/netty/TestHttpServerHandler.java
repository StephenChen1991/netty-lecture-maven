package live.chenchen.netty;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Created by chenchen on 2017/5/15.
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{
    //读取请求，并返回响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
        	HttpRequest httpRequest = (HttpRequest)msg;
        	System.out.println("请求方法名：" + httpRequest.method().name());
        	URI uri = new URI(httpRequest.uri());
        	if("/favicon.ico".equals(uri.getPath())){
        		System.out.println("请求favicon.ico");
        		return;
        	}
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
    
    
}
