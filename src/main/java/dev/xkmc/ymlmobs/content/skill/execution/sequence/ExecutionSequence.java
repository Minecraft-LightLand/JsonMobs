package dev.xkmc.ymlmobs.content.skill.execution.sequence;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class ExecutionSequence implements ExecutableEntry {

	private static class Entry {

		private ExecutableEntry entry;

		private Entry(ExecutableEntry entry) {
			this.entry = entry;
		}

	}

	protected final MasterExecutor executor;

	private final Queue<Entry> entries = new ArrayDeque<>();

	protected ExecutionSequence(MasterExecutor executor) {
		this.executor = executor;
	}

	public ExecutionSequence subSequence(boolean forceSync) {
		ExecutionSequence ans;
		if (forceSync) {
			ans = new SubSequence(this);
		} else {
			ans = new MasterSequence(executor);
			executor.add(ans);
		}
		return ans;
	}

	public void schedule(ExecutableEntry e) {
		entries.add(new Entry(e));
	}

	@Override
	public ExecutionResult execute(ExecutionSequence seq) {
		while (!entries.isEmpty() && !blocking()) {
			Entry entry = entries.peek();
			if (entry != null) {
				ExecutionResult result = entry.entry.execute(this);
				ExecutionSequence next = result.next();
				if (next != null) {
					entry.entry = next;
					result = next.execute(this);
				}
				if (result.isComplete()) {
					entries.poll();
				} else {
					break;
				}
			}
		}
		if (entries.isEmpty() && !blocking()) {
			return ExecutionResult.complete();
		} else {
			return ExecutionResult.pending();
		}
	}

	protected abstract boolean blocking();

	public abstract void delay(int ticks);

	private static class MasterSequence extends ExecutionSequence {

		private int delayTick = 0;

		protected MasterSequence(MasterExecutor executor) {
			super(executor);
		}

		@Override
		public void delay(int ticks) {
			delayTick += ticks;
		}

		@Override
		protected boolean blocking() {
			return delayTick > 0;
		}

		@Override
		public ExecutionResult execute(ExecutionSequence seq) {
			ExecutionResult result = super.execute(seq);
			if (!result.isComplete()) {
				return ExecutionResult.async(result);
			}
			return result;
		}
	}

	private static class SubSequence extends ExecutionSequence {

		private final ExecutionSequence parent;

		protected SubSequence(ExecutionSequence parent) {
			super(parent.executor);
			this.parent = parent;
		}

		@Override
		public void delay(int ticks) {
			parent.delay(ticks);
		}

		@Override
		protected boolean blocking() {
			return parent.blocking();
		}

	}

}
