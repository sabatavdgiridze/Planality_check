import java.util.Objects;

public class Pair {
	public final Integer first;
	public final Integer second;
	
	private final int hashCode;
	
	Pair(int first, int second) {
		this.first = Math.min(first, second);
		this.second = Math.max(first, second);
		this.hashCode = Objects.hash(this.first, this.second);
	}
	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;
		else {
			if (that == null || !(that instanceof Pair)) {
				return false;
			} else {
				if (this.first == ((Pair)that).first && this.second == ((Pair)that).second) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
	@Override
	public int hashCode() {
		return this.hashCode;
	}
}