import java.nio.CharBuffer;

public class BufferMatchReturn{
    /*
     * This class is created for the method MOBLIMA.charBufferMatch()
     * This method requires more than 1 return value
     * 1. A boolean to record whether the two buffers are equal or not
     * 2. A return buffer than returns the current position of the buffer
     */

    private boolean match;
    private CharBuffer buffer;

    public BufferMatchReturn(boolean m, CharBuffer buf){
        match = m;
        buffer = buf;
    }

    public boolean getMatch(){
        return match;
    }
    public CharBuffer getBuffer(){
        return buffer;
    }
}