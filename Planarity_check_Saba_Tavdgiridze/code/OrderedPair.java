import java.util.Objects;

public class OrderedPair {
	public final Integer first;
	public final Integer second;
	
	private final int hashCode;
	
	OrderedPair(int first, int second) {
		this.first = first;
		this.second = second;
		this.hashCode = Objects.hash(this.first, this.second);
	}
	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;
		else {
			if (that == null || !(that instanceof OrderedPair)) {
				return false;
			} else {
				if (this.first == ((OrderedPair)that).first && this.second == ((OrderedPair)that).second) {
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
	@Override
	public String toString() {
		return "(" + this.first + ", " + this.second + ")";
	}
}