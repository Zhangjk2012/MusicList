package com.musiclist.mongo;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    
    private static Logger logger = Logger.getLogger(MongoUtil.class);
    
    private MongoClient mongoClient;
    
    private MongoCredential mongoCredential;
    
    private Builder builder;
    
    private String hostname = "127.0.0.1";
    
    private int port = 27017;
    
    private String userName;
    
    private String password;
    
    private String databaseName;
    
    private int connectionsPerHost=8;
    
    private int threadsAllowedToBlockForConnectionMultiplier=4;
    
    private int connectTimeout=1000;
    
    private int maxWaitTime=1500;
    
    private int socketTimeout=1500;
    
    protected void init() {
        try {
            logger.info("Init the mongo db info.");
            mongoCredential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
            ServerAddress serverAddress = new ServerAddress(hostname, port); 
            builder = new Builder();
            builder.connectionsPerHost(connectionsPerHost);
            builder.connectTimeout(connectTimeout);
            builder.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);
            builder.socketTimeout(socketTimeout);
            builder.maxWaitTime(maxWaitTime);
            mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential),builder.build()); 
            logger.info("Init Succcess!");
        } catch (Exception e) {
            logger.error("Init Mongo db info error.",e);
        }
    }
    
    public Object getRecord() {
        MongoDatabase db = mongoClient.getDatabase(databaseName);
        db.getCollection("");
        return null;
    }
    
    protected void close() {
        logger.info("Close the mongo client.");
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(
            int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    
}
