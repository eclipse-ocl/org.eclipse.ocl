package org.eclipse.ocl.doc.ocl2025.tests.manual;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

public class ComplexLatexSummary extends SimpleLatexSummary
{
	public static class SummaryEntry
	{
		protected final @NonNull String key;
		protected final @NonNull String format;
		protected final @NonNull List<@NonNull Double> values = new ArrayList<>();

		public SummaryEntry(@NonNull String key, @NonNull String format) {
			this.key = key;
			this.format = format;
		}

		public void add(double value) {
			values.add(value);
		}

		public @NonNull String average() {
			double average = values.get(0);
			for (int i = 1; i < values.size(); i++) {
				double value = values.get(i);
				average += value;
			}
			return format(average / values.size());
		}

		private String format(double value) {
			if (format.contains("f")) {
				return String.format(format, value);
			}
			else {
				return String.format(format, (int)value);
			}
		}

		public @NonNull String get(int i) {
			return String.format(format, values.get(i));
		}

		public @NonNull String max() {
			double max = values.get(0);
			for (int i = 1; i < values.size(); i++) {
				double value = values.get(i);
				if (value > max) {
					max = value;
				}
			}
			return format(max);
		}

		public @NonNull String min() {
			double min = values.get(0);
			for (int i = 1; i < values.size(); i++) {
				double value = values.get(i);
				if (value < min) {
					min = value;
				}
			}
			return format(min);
		}

		public int size() {
			return values.size();
		}

		public @NonNull String getStatistic(int i) {
			switch(i) {
				case 0: return min();
				case 1: return average();
				case 2: return max();
			}
			throw new IllegalStateException("Statistic " + i);
		}
	}

	protected @NonNull Map<@NonNull Integer, @NonNull Map<@NonNull String, @NonNull SummaryEntry>> testSize2key2entry = new HashMap<>();
	//		protected @NonNull Map<@NonNull String, @NonNull SummaryEntry> key2entry = new HashMap<>();
	protected @NonNull List<@NonNull String> keys = new ArrayList<>();
	protected int valuesCount = 0;

	/*	public void average() {
		private int count = 0;
		private double min = 0;
		private double sum = 0;
		private double max = 0;
		// TODO Auto-generated method stub
		if (value < min) {
			min = value;
		}
		if (value > max) {
			max = value;
		}
		max += value;
		count++;
	} */

	public ComplexLatexSummary(String testName) throws IOException {
		super(testName);
	}

	public void flush(String title) {
		for (int iStat = 0; iStat < 3; iStat++) {
			String[] statisticNames = new String[] { "Minimum", "Average", "Maximum" };
			writer.append("\\begin{tabular}{ c");
			for (int i = 0; i < testSize2key2entry.size(); i++) {
				writer.append(" | c");
			}
			writer.append(" }\n");
			int keyIndex = 0;
			List<@NonNull Integer> testSizes = new ArrayList<>(testSize2key2entry.keySet());
			Collections.sort(testSizes);
			for (String key : keys) {
				writer.append(key);
				for (@NonNull Integer testSize : testSizes) {
					Map<@NonNull String, @NonNull SummaryEntry> key2entry = testSize2key2entry.get(testSize);
					assert key2entry != null;
					writer.append(" & ");
					SummaryEntry entry = key2entry.get(key);
					assert entry != null;
					writer.append(entry.getStatistic(iStat));
				}
				writer.append(" \\\\\n");
				if (keyIndex == 0) {
					writer.append("\\hline\n");
				}
				if (keyIndex == keys.size()-2) {
					writer.append("\\hline\\hline\n");
				}
				if (keyIndex == keys.size()-3) {
					writer.append("\\hline\n");
				}
				keyIndex++;
			}
			writer.append("\\end{tabular}\n");
			writer.append("\\caption{" + title + " " + statisticNames[iStat] + "}\n");
			writer.append("\\label{tab:" + title.replace(" ", "") + statisticNames[iStat] + "}\n");
		}
		testSize2key2entry.clear();
		keys.clear();
		valuesCount = 0;
	}

	public void keyedprintf(int testSize, @NonNull String key, @NonNull String format, double value) {
		Map<@NonNull String, @NonNull SummaryEntry> key2entry = testSize2key2entry.get(testSize);
		if (key2entry == null) {
			key2entry = new HashMap<>();
			testSize2key2entry.put(testSize, key2entry);
		}
		SummaryEntry entry = key2entry.get(key);
		if (entry == null) {
			entry = new SummaryEntry(key, format);
			key2entry.put(key, entry);
		}
		entry.add(value);
		if (!keys.contains(key)) {
			keys.add(key);
		}
		valuesCount = entry.size();
	}

	public @NonNull String average(int testSize) {
		StringBuilder s = new StringBuilder();
		s.append("---------------------------------------------------------------------------------\n");
		Map<@NonNull String, @NonNull SummaryEntry> key2entry = testSize2key2entry.get(testSize);
		assert key2entry != null;
		for (String key : keys) {
			SummaryEntry summaryEntry = key2entry.get(key);
			assert summaryEntry != null;
			s.append(summaryEntry.min());
			s.append(separator(key));
		}
		s.append("\n");
		for (String key : keys) {
			SummaryEntry summaryEntry = key2entry.get(key);
			assert summaryEntry != null;
			s.append(summaryEntry.average());
			s.append(separator(key));
		}
		s.append("\n");
		for (String key : keys) {
			SummaryEntry summaryEntry = key2entry.get(key);
			assert summaryEntry != null;
			s.append(summaryEntry.max());
			s.append(separator(key));
		}
		s.append("\n");
		s.append("=================================================================================\n");
		return s.toString();
	}

	public @NonNull String statistic(int testSize, int statistic) {
		StringBuilder s = new StringBuilder();
		Map<@NonNull String, @NonNull SummaryEntry> key2entry = testSize2key2entry.get(testSize);
		assert key2entry != null;
		for (String key : keys) {
			SummaryEntry summaryEntry = key2entry.get(key);
			assert summaryEntry != null;
			s.append(summaryEntry.getStatistic(statistic));
			s.append(",");
		}
		s.append("\n");
		return s.toString();
	}

	protected Object separator(String key) {
		if (key == keys.get(0)) {
			return " : ";
		}
		if (key == keys.get(keys.size()-3)) {
			return " = ";
		}
		if (key == keys.get(keys.size()-2)) {
			return "ns/e : ";
		}
		if (key == keys.get(keys.size()-1)) {
			return "s";
		}
		return " + ";
	}
}