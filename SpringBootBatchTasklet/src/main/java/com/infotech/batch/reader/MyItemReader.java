package com.infotech.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
 
 
public class MyItemReader implements ItemReader<String>{
 
    private String[] files = {"MyFiles/test.txt"};
    public int  count=0;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
         
        if(count < files.length){
            return files[count++];
        }else{
            count=0;
        }
        return null;
    }
}	