package uk.co.littlestickyleaves.hello.split;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Java 8 in Action, p. 177 & seq.
 */
public class WordCounterMain {

    public static final String SENTENCE = "There was no possibility of taking a walk that day.  We had been\n" +
            "wandering, indeed, in the leafless shrubbery an hour in the morning; but\n" +
            "since dinner (Mrs. Reed, when there was no company, dined early) the cold\n" +
            "winter wind had brought with it clouds so sombre, and a rain so\n" +
            "penetrating, that further out-door exercise was now out of the question.";

    public static void main(String[] args) {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream) + " words");

        Stream<Character> streamToParallelize = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(streamToParallelize.parallel()) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> streamSplit = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(streamSplit) + " words");

    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
}
