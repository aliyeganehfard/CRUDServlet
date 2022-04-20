package com.example.maktabservlet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SingleTonConnectionTest {
    @Test
    public void testConnection(){
        assertDoesNotThrow(SingleTonConnection::getSessionFactory);
    }
}