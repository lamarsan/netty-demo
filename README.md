# netty-demo
java网络编程相关。
# 包结构说明
## BIO
本包主要展示BIO相关的编程代码，内容主要是计时器的打印。
## NIO
本包主要展示NIO相关的编程代码，内容主要是NIO组件的使用以及读写数据。
## Netty
本包主要展示netty相关的编程代码，具体分为四个小包：
1. official，本包主要是存放Netty官网入门代码，https://netty.io/wiki/user-guide-for-4.x.html。
2. socket，本包主要是存放Netty实战书上的WebSocket的相关代码，同时展示了Netty从Http切换到Https的方便之处。页面代码存放在resouces目录中。
3. transport，本包主要展示传统网络编程从阻塞改造成非阻塞的过程与Netty从阻塞改造成非阻塞的过程。
4. udp，本包主要展示Netty的udp编程，主要使用DatagramPacket类。
