package com.mountainbranch;

import java.util.LinkedList;
import java.util.List;

public class Dec9 {
	private enum State {GROUP, GARBAGE};
	private Group topGroup = null;
	
	public Dec9(String input) {
		parseGroups(input);
	}
	
	private void parseGroups(String input) {
		char[] chars = input.toCharArray();
		topGroup = new Group(null);
		Group currentGroup = topGroup;
		State state = State.GROUP;
		
		for (int i = 1; i < chars.length; i++) {
			switch (chars[i]) {
			case '{':
				if (state == State.GROUP) {
					// Beginning of new group
					Group newGroup = new Group(currentGroup);
					currentGroup.children.add(newGroup);
					currentGroup = newGroup;
				}
				break;
			case '}':
				if (state == State.GROUP) {
					// End of group
					currentGroup = currentGroup.parent;
				}
				break;
			case '<':
				state = State.GARBAGE;
				break;
			case '>':
				state = State.GROUP;
				break;
			case '!':
				// Skip next char
				i++;
				break;
			case ',':
				// Do nothing
				break;
			default:
				// Probably reading garbage here...
			}
		}
	}
	
	public int getTotalScore() {
		return topGroup.getTotalScore();
	}
	
	private class Group {
		public final Group parent;
		public List<Group> children = new LinkedList<Group>();
		public final int score;
		
		public Group(Group parent) {
			this.parent = parent;
			score = (parent == null) ? 1 : parent.score+1;
		}
		
		public int getTotalScore() {
			int totalScore = score;
			for (Group child : children) {
				totalScore += child.getTotalScore();
			}
			return totalScore;
		}
	}
}
