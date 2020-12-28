package com.glintt.nexllence.countries;


public class Coisa {

 String nome;

 Coisa(String n) {

 nome = n;

 }

 public static void main(String[] args) {

 Coisa m1 = new Coisa("guitarra");

 Coisa m2 = new Coisa("carro");

 System.out.println(m2.equals(m1));

 }

 public boolean equals(Object o) {

 Coisa m = (Coisa) o;

 if (m.nome != null) {

  return true;

 }

 return false;

 }

}