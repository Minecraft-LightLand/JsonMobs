package dev.xkmc.ymlmobs.content.skill.execution;

import javax.annotation.Nullable;

public interface ExecutionResult {

	record SingletonResult(boolean isComplete) implements ExecutionResult {

		@Nullable
		@Override
		public ExecutionSequence next() {
			return null;
		}

	}

	record PendingResult(ExecutionSequence next) implements ExecutionResult {

		@Override
		public boolean isComplete() {
			return false;
		}

	}

	ExecutionResult NO_TARGET = new SingletonResult(true);
	ExecutionResult COMPLETE = new SingletonResult(true);
	ExecutionResult PENDING = new SingletonResult(false);
	ExecutionResult ASYNC = new SingletonResult(false);

	static ExecutionResult noTarget() {
		return NO_TARGET;
	}

	static ExecutionResult additional(ExecutionSequence sub) {
		return new PendingResult(sub);
	}

	static ExecutionResult complete() {
		return COMPLETE;
	}

	static ExecutionResult pending() {
		return PENDING;
	}

	static ExecutionResult async(ExecutionResult result) {
		return ASYNC;
	}

	boolean isComplete();

	@Nullable
	ExecutionSequence next();

}
