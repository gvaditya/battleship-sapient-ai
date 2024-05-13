package com.aditya.io;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.model.ship.ShipConfiguration;

import java.io.IOException;

public interface IShipConfigurationProvider {
    ShipConfiguration takeInput() throws IOException, InvalidInputException;
}
