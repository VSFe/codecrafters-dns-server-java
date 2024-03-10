package udp.util;

public class Pair<T, R> {
	private final T first;
	private final R second;

	public Pair(T first, R second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public R getSecond() {
		return second;
	}

	public T getLeft() {
		return first;
	}

	public R getRight() {
		return second;
	}

	public static <T, R> Pair<T, R> of(T first, R second) {
		return new Pair<>(first, second);
	}
}
