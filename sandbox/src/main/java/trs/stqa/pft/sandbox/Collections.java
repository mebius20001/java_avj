package trs.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args){
    String[] langs2 = new String[4];
    langs2[0] = "Java";
    langs2[1] = "C#";
    langs2[2] = "Python";
    langs2[3] = "PHP";

    String[] lang = {"Java", "C#", "Python", "PHP" };

    for (int i = 0; i < lang.length; i++ ){
      System.out.println("I like "+ lang[i]);
    }

    for (String l : lang){
      System.out.println("I like - "+ l);
    }

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");

    List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : languages){
      System.out.println("I like + "+ l);
    }

    for (int i =0; i < languages2.size();i++){
      System.out.println("I like ++ "+ languages2.get(i));
    }
  }
}
