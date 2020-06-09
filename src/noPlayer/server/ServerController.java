package noPlayer.server;

import java.io.IOException;
import java.sql.SQLException;

public class ServerController {
    SQLDriver sqlDriver;
    RMIDriver rmiDriver;
    RMIImpl rmiImpl;

    public ServerController() throws SQLException, IOException, ClassNotFoundException {
        sqlDriver = new SQLDriver();
        rmiImpl = new RMIImpl(this);
        rmiDriver = new RMIDriver(rmiImpl);
        System.out.println("Server Initialized");
    }

    public SQLDriver getSqlDriver() {
        return sqlDriver;
    }
}
