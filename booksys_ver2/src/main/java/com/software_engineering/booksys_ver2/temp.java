package com.software_engineering.booksys_ver2;

public class temp {

  public static void main(String[] args) {
  }

  public static class DifficultProduct {
    char[] nameChars = new char[10];
    public DifficultProduct() {
    }
    public void setFirstNameCharacter(char c) {
      nameChars[0] = c;
    }
    public void setSecondNameCharacter(char c) {
      nameChars[1] = c;
    }
    public void setThirdNameCharacter(char c) {
      nameChars[2] = c; }

    public String getName()
    {
      return new String(nameChars); }
  }
}
