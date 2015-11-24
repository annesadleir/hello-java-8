package uk.co.littlestickyleaves.hello.split;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * from this StackOverflow question
 * http://stackoverflow.com/questions/28363323/partition-a-stream-by-a-discriminator-function?lq=1
 */
public class PartitionBySpliterator<E> extends Spliterators.AbstractSpliterator<List<E>> {

    private final Spliterator<E> spliterator;
    private final Function<? super E, ?> partitionBy;
    private HoldingConsumer<E> holder;
    private Comparator<List<E>> comparator;


    public PartitionBySpliterator(Spliterator<E> spliterator, Function<? super E, ?> partitionBy) {
        super(Long.MAX_VALUE, spliterator.characteristics() & ~SIZED | NONNULL);
        this.spliterator = spliterator;
        this.partitionBy = partitionBy;
    }

    public static <E> Stream<List<E>> partitionBy(Function<E, ?> partitionBy, Stream<E> in) {
        return StreamSupport.stream(new PartitionBySpliterator<>(in.spliterator(), partitionBy), false);
    }

    @Override
    public boolean tryAdvance(Consumer<? super List<E>> action) {
        final HoldingConsumer<E> h;
        if (holder == null) {
            h = new HoldingConsumer<>();
            if (!spliterator.tryAdvance(h)) {
                return false;
            }
            holder = h;
        } else h = holder;
        final ArrayList<E> partition = new ArrayList<>();
        final Object partitionKey = partitionBy.apply(h.value);
        boolean didAdvance;
        do {
            partition.add(h.value);
        } while ((didAdvance = spliterator.tryAdvance(h))
                && Objects.equals(partitionBy.apply(h.value), partitionKey));
        if (!didAdvance) {
            holder = null;
        }
        action.accept(partition);
        return true;
    }

    static final class HoldingConsumer<T> implements Consumer<T> {
        T value;

        @Override
        public void accept(T value) {
            this.value = value;
        }
    }

    @Override
    public Comparator<? super List<E>> getComparator() {
        final Comparator<List<E>> c = this.comparator;
        return c != null ? c : (this.comparator = comparator());
    }

    private Comparator<List<E>> comparator() {
        final Comparator<? super E> innerComparator =
                Optional.ofNullable(spliterator.getComparator())
                .orElse((Comparator) Comparator.naturalOrder());;
        return (left,right) -> {
            final int c = innerComparator.compare(left.get(0), right.get(0));
            return c != 0 ? c : innerComparator.compare(left.get(left.size() - 1),
                    right.get(right.size() - 1));
        };
    }
}