
package com.mycompany.cashier;

import java.io.FileWriter;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
public class JsonRepository<T> implements Repository<T> {
    private String fileName;
    private Class<T[]> type;

    public JsonRepository(String fileName, Class<T[]> type) {
        this.fileName = fileName;
        this.type = type;
    }
    @Override
    public void save(List<T> data){
        try(FileWriter writer = new FileWriter(fileName)){
            new Gson().toJson(data,writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public List<T> load(){
        try(FileReader reader  = new FileReader(fileName)){
            T[] arr = new Gson().fromJson(reader, type);
            return new ArrayList<>(Arrays.asList(arr));
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
