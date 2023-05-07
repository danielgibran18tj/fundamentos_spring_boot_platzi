package com.fundamentos.springboot.fundamentos.bean;

public class  MybeanImplement implements MyBean{

    @Override
    public void print() {
        System.out.println("Hola desde mi implementacion propia del bean");
    }
}
