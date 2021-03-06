/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeConexao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.junit.Test;
import util.MongoConnection;

/**
 *
 * @author tiago
 */
public class TesteConexaoMongo {
    private static MongoConnection uniqInstance;
    private static int mongoInstance = 1;
    
    private MongoClient mongo;
    private MongoDatabase db;
    private String host = "@ds241895.mlab.com:41895";
    private String user = "root";
    private String password = "root";
    private String dataBase = "proo";              
    
    CodecRegistry codecRegistry =
        CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new UuidCodec(UuidRepresentation.STANDARD)),
                                       MongoClient.getDefaultCodecRegistry());
    
    public static synchronized MongoConnection getInstance() {
        if (uniqInstance == null) {
            uniqInstance = new MongoConnection();
        }
        return uniqInstance;
    }
    @Test
    public void deveCriarUmaConexaoComOBancoDeDados() {
        if (mongo == null) {
            MongoClientURI uri = new MongoClientURI("mongodb://"+user+":"+password+host+"/"+dataBase);
            mongo = new MongoClient(uri);
            db = mongo.getDatabase("proo").withCodecRegistry(codecRegistry);
            System.out.println("Mongo instance equals :> " + mongoInstance++);
        }        
    }
}
