package com.aditya.io;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.exceptions.InvalidPositionException;
import com.aditya.model.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SysInPositionProvider implements IPositionProvider {
    @Override
    public Position takeInput() throws IOException, InvalidInputException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();

        String[] input = line.split(" ");

        if(input.length != 2){
            throw new InvalidInputException();
        }

        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);

        return new Position(x,y);
    }
}
