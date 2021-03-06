package me.squid.eonhomes.sql;

import me.squid.eonhomes.EonHomes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    EonHomes plugin;
    private String host, port, database, user, password;
    Connection connection;

    public MySQL(EonHomes plugin){
        this.plugin = plugin;
        host = plugin.getConfig().getString("SQLHost");
        port = plugin.getConfig().getString("SQLPort");
        database = plugin.getConfig().getString("SQLDatabase");
        user = plugin.getConfig().getString("SQLUser");
        password = plugin.getConfig().getString("SQLPassword");
    }

    public boolean isConnected() {
        return (connection != null);
    }

    public void connectToDatabase() throws SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", user, password);
            if (isConnected()) plugin.getLogger().info("Database has succesfully connected");
            else connectToDatabase();
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
