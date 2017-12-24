package tree;

import com.google.common.collect.ComparisonChain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 利用字典树从日志文件中找出单词频率前10的单词
 */
public class Trie implements Comparable<Trie> {

    private static final Pattern WORD_PATTERN = Pattern.compile("\\w+");

    private char value;

    private int count;

    private Trie parent;

    private Map<Character,Trie> children;

    private boolean root;
    public Trie(Character value,Trie parent){
        this(value, parent, false);
    }
    public Trie(Character value,Trie parent,boolean root){
        if (value != null) {
            this.value = value;
        }

        this.root = root;
        this.count = 0;
        this.parent = parent;
        children = new HashMap<>();
    }


    public void traverse(Trie node,List<Trie> words){
        if (node.count > 0) {
            words.add(node);
        }

        node.children.values().stream().forEach(c -> traverse( c, words));

    }

    public String printWord(){
        Stack<Character> result = new Stack<>();
        Trie node = this;
        while (!node.root) {
            result.add(node.value);
            node = node.parent;
        }

        StringBuilder sb = new StringBuilder();

        while (!result.isEmpty()) {
            sb.append(result.pop());
        }
        return sb.toString();


    }


    public void insert(String word) {
        Objects.requireNonNull(word);

        Map<Character, Trie> currChild = this.children;

        Trie parent = this;
        for (int index=0;index<=word.length()-1;index++) {
            char value = word.charAt(index);
            Trie childTrie = currChild.get(value);

            if (childTrie == null) {
                childTrie = new Trie(value, parent);
                currChild.put(value, childTrie);
            }
            parent = childTrie;
            currChild = childTrie.children;

            if (index == word.length() - 1) {
                childTrie.count++;

            }
        }
    }

    public static void main(String[] args) throws IOException {
        String fileName = "/Users/chaojun/create.log";

        final Trie trie = new Trie(null,null,true);
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.flatMap(f -> Arrays.stream(f.split(" ")))
                    .map(String::trim)
                    .filter(w -> WORD_PATTERN.matcher(w).find())
                    .forEach(trie::insert);
        }

        final List<Trie> result = new LinkedList<>();
        trie.traverse(trie, result);

        result.sort((o1, o2) -> o2.count - o1.count);

        result.stream().limit(10)
                .forEach(t -> System.out.println("word:" + t.printWord() + "，count:" + t.count));

    }

    @Override
    public int compareTo(Trie o) {
        return ComparisonChain.start().compare(this.count, o.count).result();
    }
}
