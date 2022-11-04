//public static BufferMatchReturn charBufferMatch(CharBuffer txtbuffer, CharBuffer inputbuf){
    //     /*
    //      * Methodology:
    //      * 1. Compare buffer from .txt file against user input
    //      * 2. If passed, return match = true
    //      * 3. If failed, return match = false
    //      * 4. In both cases, also return txtbuffer to record the current position of the buffer
    //      * 5. BufferMatchReturn object will be used to contain the multiple return values
    //      * 
    //      * Apparently compareTo() only compares the buffer from the CURRENT position
    //      * So there is a need to reset the position to zero before comparing
    //      * By using clear() on inputbuf
    //      * But we need to retain the position of txtbuffer, so that one cannot clear()
    //      */

    //     CharBuffer rawtxtcomp = CharBuffer.allocate(1000); //substring of rawtxt used for comparison

    //     // at the end of do-while loop, rawtxtcomp should contain the comparison substring we need
    //     char c = txtbuffer.get();
    //     do{
    //         rawtxtcomp.append(c); //append individual characters into comparison buffer
    //         c = txtbuffer.get();
    //     }while(c != ','); //until reached the end of the password element
        
    //     inputbuf.clear(); //reset position to zero for comparison, if not already done
    //     rawtxtcomp.clear(); //reset position to zero for comparison
    //     if(rawtxtcomp.compareTo(inputbuf) == 0){
    //         BufferMatchReturn ret = new BufferMatchReturn(true, txtbuffer);
    //         return ret;
    //     } else {
    //         BufferMatchReturn ret = new BufferMatchReturn(false, txtbuffer);
    //         return ret;
    //     }
    // }
    //return null;
//}