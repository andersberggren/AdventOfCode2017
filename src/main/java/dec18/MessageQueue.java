package dec18;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessageQueue {
	private Map<Integer, List<Long>> messageQueues = new HashMap<Integer, List<Long>>();

	/** Sends value to program programId. */
	public void send(Integer programId, long value) {
		List<Long> messageQueue = messageQueues.get(programId);
		if (messageQueue == null) {
			messageQueue = new LinkedList<Long>();
			messageQueues.put(programId, messageQueue);
		}
		messageQueue.add((Long) value);
	}

	/**
	 * Returns the next value in the queue for programId.
	 * Returns null if there are no elements in the queue.
	 */
	public Long receive(Integer programId) {
		List<Long> messageQueue = messageQueues.get(programId);
		if (messageQueue != null && !messageQueue.isEmpty()) {
			return messageQueue.remove(0);
		} else {
			return null;
		}
	}
}
