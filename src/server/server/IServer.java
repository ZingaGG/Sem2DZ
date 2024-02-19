package server.server;

import server.client.Client;

import java.util.List;

public interface IServer {
    boolean connectUser(Client client);
    void disconnectUser(Client client);
    String getHistory();

    List<Client> getClientList();

    ServerWindow getServerWindow();
}
