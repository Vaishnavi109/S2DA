package Netty.nettyproj;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
		ByteBuf in = (ByteBuf) msg;
		int sum =0;
        try{
        	while(in.isReadable()){
        		char ch = (char) in.readByte();
        		int n = Character.getNumericValue(ch);
        		System.out.print(ch);
        		
        		//sum = sum + Character.getNumericValue(ch);
        		//System.out.print(sum);
        	}
        	
        	//ctx.write(msg); // (1)
            ctx.flush(); 
        	
        }finally{
        	ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
	

}
