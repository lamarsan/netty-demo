package com.lamarsan.netty.udp;

import java.net.InetSocketAddress;

/**
 * className: LogEvent
 * description: 事件消息，用于存储事件数据
 * Broadcaster负责广播并进行编码，只有一个EncoderHandler
 * Monitor负责接收广播数据，并打印，需要解码并打印，有两个Handler
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/10 21:27
 */
public class LogEvent {
    public static final byte SEPARATOR = (byte) '|';

    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    private final long received;

    public LogEvent(String logfile, String msg) {
        this(null, -1, logfile, msg);
    }

    public LogEvent(InetSocketAddress source, long received, String logfile, String msg) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }

    public InetSocketAddress getSource() {
        return source;
    }

    public String getLogfile() {
        return logfile;
    }

    public String getMsg() {
        return msg;
    }

    public long getReceived() {
        return received;
    }
}
