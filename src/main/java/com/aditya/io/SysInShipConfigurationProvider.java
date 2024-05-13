package com.aditya.io;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.model.Position;
import com.aditya.model.ship.ShipConfiguration;
import com.aditya.model.ship.ShipType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SysInShipConfigurationProvider implements IShipConfigurationProvider{
    @Override
    public ShipConfiguration takeInput() throws IOException, InvalidInputException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();

        String[] input = line.split(" ");

        if(input.length != 3){
            throw new InvalidInputException();
        }

        ShipType type = ShipType.valueOf(input[0]);
        int length = Integer.parseInt(input[1]);
        int width = Integer.parseInt(input[2]);

        return new ShipConfiguration(type,length,width);
    }
}
