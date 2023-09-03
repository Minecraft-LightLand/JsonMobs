package dev.xkmc.ymlmobs.content.skill.execution.sequence;

import javax.annotation.Nullable;

public interface ExecutionResult {


	record SingletonResult(boolean succeed, boolean isComplete) implements ExecutionResult {

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

		@Override
		public boolean succeed() {
			return true;
		}
	}

	ExecutionResult NO_TARGET = new SingletonResult(false, true);
	ExecutionResult COMPLETE = new SingletonResult(true, true);
	ExecutionResult PENDING = new SingletonResult(true, false);
	ExecutionResult ASYNC = new SingletonResult(true, true);

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

	boolean succeed();

	@Nullable
	ExecutionSequence next();

}
