package org.eclipse.ocl.examples.xtext.serializer;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class GrammarRuleVector implements Iterable<@NonNull Integer>, Comparable<@NonNull GrammarRuleVector>
{
	protected class IndexIterator implements Iterator<@NonNull Integer>
	{
		private int cursor;

		public IndexIterator() {
			cursor = doNext(-1);
		}

		public int doNext(int cursor) {
			while (++cursor < getCapacity()) {
				if (test(cursor)) {			// Faster in words
					return cursor;
				}
			}
			return -1;
		}

		@Override
		public boolean hasNext() {
			return cursor >= 0;
		}

		@Override
		public @NonNull Integer next() {
			try {
				return cursor;
			}
			finally {
				cursor = doNext(cursor);
			}
		}
	}

	private long longs[] = null;
	private @Nullable Integer hashCode;

	public GrammarRuleVector() {}

	public GrammarRuleVector(long @NonNull ... longs) {
		this.longs = longs;
	}

	public GrammarRuleVector(@NonNull Indexed @NonNull [] indexes) {
		this.longs = null;
		for (@NonNull Indexed index : indexes) {
			set(index.getIndex());
		}
	}

	public GrammarRuleVector(@NonNull Integer @NonNull [] indexes) {
		this.longs = null;
		for (@NonNull Integer index : indexes) {
			set(index);
		}
	}

	public GrammarRuleVector(@NonNull Iterable<@NonNull ? extends Indexed> indexes) {
		this.longs = null;
		for (@NonNull Indexed index : indexes) {
			set(index.getIndex());
		}
	}

	@Override
	public int compareTo(@NonNull GrammarRuleVector that) {
		int iThis = this.getLength();
		int iThat = that.getLength();
		int iCommon = Math.min(iThis, iThat);
		int i = Math.max(iThis, iThat);
		for ( ; (i > iCommon) && (i > iThat); ) {
			if (this.longs[--i] != 0) {
				return 1;
			}
		}
		for ( ; (i > iCommon) && (i > iThis); ) {
			if (that.longs[--i] != 0) {
				return -1;
			}
		}
		for ( ; --i >= 0; ) {
			int diff = Long.compareUnsigned(this.longs[i], that.longs[i]);
			if (diff != 0) {
				return diff;
			}
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof GrammarRuleVector)) {
			return false;
		}
		GrammarRuleVector that = (GrammarRuleVector)obj;
		int iThis = this.getLength();
		int iThat = that.getLength();
		int iCommon = Math.min(iThis, iThat);
		int i = 0;
		for ( ; i < iCommon; i++) {
			if (this.longs[i] != that.longs[i]) {
				return false;
			}
		}
		for ( ; i < iThis; i++) {
			if (this.longs[i] != 0) {
				return false;
			}
		}
		for ( ; i < iThat; i++) {
			if (that.longs[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public int getCapacity() {
		return Long.SIZE * getLength();
	}

	private int getLength() {
		return longs != null ? longs.length : 0;
	}

	@Override
	public final int hashCode() {
		Integer hashCode2 = hashCode;
		if (hashCode2 == null) {
			int hash = getClass().hashCode();
			boolean isMS = true;
			for (int i = longs.length; --i >= 0; ) {
				long word = longs[i];
				if (!isMS || (word != 0)) {
					hash = (int)(3 * hash + word + (word >> 16));
					isMS = false;
				}
			}
			this.hashCode = hashCode2 = hash;
		}
		return hashCode2.intValue();
	}

	@Override
	public @NonNull Iterator<@NonNull Integer> iterator() {
		return new IndexIterator();
	}

	public @NonNull GrammarRuleVector set(int bitIndex) {
		setCapacity(bitIndex+1);
		long mask = 1L << (bitIndex % Long.SIZE);
		longs[bitIndex / Long.SIZE] |= mask;
		return this;
	}

	public @NonNull GrammarRuleVector setAll(@NonNull GrammarRuleVector bits) {
		if (bits.longs != null) {
			setCapacity(Long.SIZE * bits.longs.length);
			for (int i = 0; i < bits.longs.length; i++) {
				longs[i] |= bits.longs[i];
			}
		}
		return this;
	}

	public @NonNull GrammarRuleVector setCapacity(int capacity) {
		int newLength = (capacity + Long.SIZE - 1)/Long.SIZE;
		if (longs == null) {
			longs = new long[newLength];
		}
		else if (newLength > longs.length) {
			long[] oldLongs = longs;
			longs = new long[newLength];
			for (int i = 0; i < oldLongs.length; i++) {
				longs[i] = oldLongs[i];
			}
		}
		return this;
	}

	public boolean test(int bitIndex) {
		int newLength = (bitIndex + Long.SIZE)/Long.SIZE;		// No -1 since inclusive
		if (newLength > getLength()) {
			return false;
		}
		long mask = 1L << (bitIndex % Long.SIZE);
		return (longs[bitIndex / Long.SIZE] & mask) != 0;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		boolean isFirst = true;
		for (int i = 0; i < getLength(); i++) {
			long word = longs[i];
			long mask = 1L;
			for (int j = 0; j < Long.SIZE; j++, mask <<= 1) {
				if ((word & mask) != 0) {
					if (!isFirst) {
						s.append(",");
					}
					s.append(Long.SIZE*i + j);
					isFirst = false;
				}
			}
		}
		s.append("]");
		@SuppressWarnings("null")
		@NonNull String castString = (@NonNull String)s.toString();
		return castString;
	}

	public @NonNull String toWordsString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;
		for (int i = 0; i < getLength(); i++) {
			if (!isFirst) {
				s.append(",");
			}
			s.append("0x");
			s.append(Long.toHexString(longs[i]));
			s.append("L");
			isFirst = false;
		}
		@SuppressWarnings("null")
		@NonNull String castString = (@NonNull String)s.toString();
		return castString;
	}
}
