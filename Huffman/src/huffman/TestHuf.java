package huffman;

import java.util.ArrayList;

public class TestHuf {
    public static void main(String[] args) {
        ArrayList<CharFreq> test = HuffmanCoding.makeSortedList("input4.txt");
        System.out.println(test.get(0).getProbOccurrence());
    }
}
