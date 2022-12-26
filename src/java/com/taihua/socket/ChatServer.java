package com.taihua.socket;

import com.taihua.entity.ChatData;
import com.taihua.entity.User;
import com.taihua.utils.IOUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChatServer {
    private ServerSocket server;
    private Map<User, Socket> players;

    public ChatServer(){

        players = new HashMap<User, Socket>();
        try {
            server = new ServerSocket(10001);
            System.out.println("服务器启动");
            while (true){
                System.out.println("服务器等待客户端连接");
                Socket s = server.accept();
                ChatData data = (ChatData) IOUtil.readObject(s.getInputStream());
                players.put(data.getUser(), s);
                System.out.println("用户:" + data.getUser().getUsername() + "上线了.....");
                new Thread(new ChatServerThread(data.getUser(), s)).start();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    class ChatServerThread implements Runnable{

        private User user;
        private Socket s;

        public ChatServerThread(User user, Socket s){
            this.user = user;
            this.s = s;
        }

        @Override
        public void run() {
            while (true){
                try {
                    ChatData data = (ChatData) IOUtil.readObject(s.getInputStream());

                    Set<User> users = players.keySet();
//                    if (data.getMessage().getFlag() == 1){
//                    }
                    for (User temp : users){
                        if (temp.equals(user)) {
                            System.out.println("continue:" + temp.getUsername());
                            continue;
                        }
                        System.out.println("什么鬼");
                        IOUtil.writeObject(data, players.get(temp).getOutputStream());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}
