package com.aditya.io;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.exceptions.InvalidPositionException;
import com.aditya.model.Position;

import java.io.IOException;

public interface IPositionProvider {
    Position takeInput() throws IOException, InvalidInputException;
}
