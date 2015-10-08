package com.Bluetooth.base;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2015/10/8.
 */


public class SerialInterfaceBase {

    private ByteBuffer byteBuffer = ByteBuffer.allocate(2048);   //每次recive到数据的byteBuffer
    private ReceiveStatus status = ReceiveStatus.ReceiveStatus_ReadeyPipe;
    private ByteBuffer frameDataBuffer = ByteBuffer.allocate(256);;    //当前处理帧的数据缓存
    private int dataLen = 0;                    //当前处理帧的数据总 长度
    private int dataCurPos = 0;                    //当前已经接收到的数据长度

    public synchronized void pushByte(byte [] prameData){
        byteBuffer.put(prameData);
        this.executeBytes();
    }

    private void executeBytes(){
        switch (this.status){
            case ReceiveStatus_ReadeyPipe:{
                while (byteBuffer.hasRemaining()){
                    try {
                        if (byteBuffer.get() == (byte)0x5A){
                            if (byteBuffer.hasRemaining()){
//                                byteBuffer = ByteBuffer.wrap(byteBuffer.array(),1,byteBuffer.limit());
                                this.executeBytes();
                            }else {
                                byteBuffer.clear();
                            }
                            this.status = ReceiveStatus.ReceiveStatus_ReadeyPipeLen;
                            break;
                        }
                    }catch (java.nio.BufferOverflowException exception){
                        System.out.print("ReceiveStatus_ReadeyPipe ：  BufferOverflowException异常");
                        System.out.print(exception.toString());
                    }

                }
                break;
            }
            case ReceiveStatus_ReadeyPipeLen:{
                if (byteBuffer.hasRemaining()){
                    this.dataLen = byteBuffer.get();
                    this.status = ReceiveStatus.ReceiveStatus_ReadeyPipeData;
                    if (byteBuffer.hasRemaining()){
                        this.executeBytes();
                    }else {
                        byteBuffer.clear();
                    }
                }else {
                    byteBuffer.clear();
                }
                break;
            }
            case ReceiveStatus_ReadeyPipeData:
            case ReceiveStatus_ExecPipeData:{
                if((byteBuffer.limit() - byteBuffer.position()) >= (dataLen - dataCurPos)){  //数据完全

                    for(int i = 0;i<dataLen - dataCurPos;i++){
                        byte tempByte = byteBuffer.get();
                        frameDataBuffer.put(tempByte);
                    }
//                    FramesInCommunicationOpera abced = new FramesInCommunicationOpera(frameDataBuffer.array());

                    dataLen = 0;
                    dataCurPos = 0;
                    frameDataBuffer.clear();
                    byteBuffer = ByteBuffer.wrap(byteBuffer.array(),byteBuffer.position(),byteBuffer.limit()-byteBuffer.position());
                    status = ReceiveStatus.ReceiveStatus_ReadeyPipe;
                    if (byteBuffer.hasRemaining()){
                        this.executeBytes();
                    }
                }else {
                    while (byteBuffer.hasRemaining()){
                        byte tempByte = byteBuffer.get();
                        frameDataBuffer.put(tempByte);
                        dataCurPos++;
                    }
                    status = ReceiveStatus.ReceiveStatus_ExecPipeData;
                    byteBuffer.clear();
                }
                break;
            }
        }
    }

    enum ReceiveStatus{
        ReceiveStatus_ReadeyPipe,  //准备好接收新的通道帧
        ReceiveStatus_ReadeyPipeLen,  //准备接收通道帧数据长度
        ReceiveStatus_ReadeyPipeData,  //准备接收通道帧数据
        ReceiveStatus_ExecPipeData,    //正在接收通道帧数据
    };
}
