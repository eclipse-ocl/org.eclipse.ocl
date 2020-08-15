package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.jdt.annotation.NonNull;

public class IndexVector
{
	private long longs[] = null;
	private int hashCode;

	public IndexVector() {}

	public IndexVector(int capacity) {
		setCapacity(capacity);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof IndexVector)) {
			return false;
		}
		IndexVector that = (IndexVector)obj;
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

	private int getLength() {
		return longs != null ? longs.length : 0;
	}

	@Override
	public int hashCode() {
		if (this.hashCode != 0) {
			int hashCode = getClass().hashCode();
			for (int i = longs.length; --i >= 0; ) {
				long word = longs[i];
				hashCode = (int)(3 * hashCode + word + (word >> 16));
			}
			this.hashCode = hashCode != 0 ? hashCode : 1;
		}
		return hashCode;
	}

	public void set(int bitIndex) {
		setCapacity(bitIndex);
		long mask = 1L << (bitIndex % Long.SIZE);
		longs[bitIndex / Long.SIZE] |= mask;
	}

	public void setAll(@NonNull IndexVector bits) {
		if (bits.longs != null) {
			setCapacity(Long.SIZE * bits.longs.length);
			for (int i = 0; i < bits.longs.length; i++) {
				longs[i] |= bits.longs[i];
			}
		}
	}

	private void setCapacity(int capacity) {
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
	}

	public boolean test(int bitIndex) {
		if (longs == null) {
			return false;
		}
		int newLength = (bitIndex + Long.SIZE - 1)/Long.SIZE;
		if (newLength > longs.length) {
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
		for (int i = 0; i < longs.length; i++) {
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
		return s.toString();
	}
}
