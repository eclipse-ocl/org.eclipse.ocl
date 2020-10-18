package org.eclipse.ocl.examples.xtext.build.clones;

import org.eclipse.jdt.annotation.NonNull;

/**
 * @since 1.13
 */
public class DebugTimestamp
{
	protected static final long originTime = System.nanoTime();

	protected final @NonNull String name;
	protected final long startTime;

	public DebugTimestamp(@NonNull String name) {
		this.name = name;
		this.startTime = System.nanoTime();
		String message = String.format("%12.6f %s Starting", 0.000000001 * (startTime- originTime), name);
		doLog(message);
	}

	protected void doLog(String message) {	// Override for a logger
		System.out.println(message);
	}

	public void log(@NonNull String event) {
		long nowTime = System.nanoTime();
		String message = String.format("%12.6f %s %s", 0.000000001 * (nowTime - startTime), name, event);
		doLog(message);
	}
}