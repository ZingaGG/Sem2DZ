package server.server;

import server.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server implements IServer{
    private ServerWindow serverWindow;
    private List<Client> clientList;

    public Server(ServerWindow serverView) {
        this.serverWindow = serverView;
        clientList = new ArrayList<>();
    }

    @Override
    public boolean connectUser(Client client) {
        if (!serverWindow.isWork()){
            return false;
        }
        clientList.add(client);
        return true;
    }

    @Override
    public List<Client> getClientList() {
        return clientList;
    }

    @Override
    public void disconnectUser(Client client) {
        clientList.remove(client);
        if (client != null){
            client.disconnectFromServer();
        }
    }

    @Override
    public String getHistory() {
        return serverWindow.getLog();
    }
    @Override
    public ServerWindow getServerWindow() {
        return serverWindow;
    }
}
