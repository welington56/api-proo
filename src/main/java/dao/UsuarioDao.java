/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import util.MongoConnection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import vo.Usuario;
import static com.mongodb.client.model.Updates.combine;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author tiago
 */
public class UsuarioDao implements IDao{                
    
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    
    MongoCollection<Usuario> collection = MongoConnection.getInstance().getDB()
                    .getCollection("Usuarios", Usuario.class)
                    .withCodecRegistry(pojoCodecRegistry);
                                    
    protected MongoCollection getDbCollection() {
        return collection;
    }   
            
    public void salvar(Usuario usuario) {        
            
        collection.insertOne(usuario);
        System.out.println("Salvo:>"+usuario);
        
    }    
    
    public void salvarLista(List usuario){                        
        collection.insertMany(usuario);        
    }    
    
    public List<Usuario> listar() {
        
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();                        
        for (Usuario usuario : collection.find()) {
            listaUsuarios.add(usuario);
        }
        
        return listaUsuarios;
    }    
    
    public Usuario listarPorCpf(String cpf){
        Usuario usuario = null;
        usuario = collection.find(eq("cpf", cpf)).first();            
        
        Usuario usuarioRet = new Usuario();
                                    
        usuarioRet.setNomeCompleto(usuario.getNomeCompleto());
        usuarioRet.setEmail(usuario.getEmail());
        usuarioRet.setFone1(usuario.getFone1());
        usuarioRet.setFone2(usuario.getFone2());
        usuarioRet.setEndereco(usuario.getEndereco());
        usuarioRet.setCpf(usuario.getCpf());
        
        return usuarioRet;
    }
        
    public Usuario authenticationPorCpfEmail(String cpf, String senha){
        Usuario usuario = collection.find(and (eq("cpf", cpf),eq("senha", senha))).first();        
        return usuario;
    }
    
    //Fazer sobrecarga de metodos
    public void atualizarPorId(String id, String email, String nomeCompleto){        
        collection.updateOne(eq("_id",new ObjectId(id)), 
                combine(set("email", email), 
                        set("nomeCompleto", nomeCompleto)));                
    }
    
    public void atualizarPorCpf(String cpf, Usuario usuario){
        try{            
            collection.replaceOne(eq("cpf", cpf), usuario);            
        }catch(Exception ex){
            System.out.println("Não foi possível atualizar usuario "+ex.getMessage());
        }
    }
        
    public void removerPorCpf(String cpf){
        try{
            collection.deleteOne(eq("cpf", cpf));
        }catch(Exception ex){
            System.out.println("Não foi possível deletar usuario"+ex.getMessage());
        }
    }
        
    public void removerPorAtributo(String atributo, String valor){        
        try{
           collection.deleteMany(eq(atributo, valor));
        }catch(Exception ex){
            System.out.println("Atributo inválido"+ex.getMessage());
        }                
    }
    /*
    @Test
    public void deveAtualizarMultiplosUsuarios(){
        
        List<Usuario> usuario = new ArrayList<Usuario>();
        for(int i=0; i<100; i++){
            usuario.add(new Usuario("Tiago de Lima Alves","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007","")));
        }
        
        UpdateResult updateResult = collection.updateMany((eq( "email", "hotmail.com")), set("email", null));        
        System.out.println("Atualizando Multiplos documentos "+updateResult.getModifiedCount());
        
        collection.deleteMany(eq("email", null));
    }*/
                    
}
