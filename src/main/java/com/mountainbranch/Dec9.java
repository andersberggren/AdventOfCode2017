package com.mountainbranch;

import java.util.LinkedList;
import java.util.List;

public class Dec9 {
	private enum State {GROUP, GARBAGE};
	private Group topGroup = new Group(null);
	private int totalGarbage = 0;
	
	public Dec9(String input) {
		parseGroups(input);
	}
	
	private void parseGroups(String input) {
		char[] chars = input.toCharArray();
		Group currentGroup = topGroup;
		State state = State.GROUP;
		
		for (int i = 1; i < chars.length; i++) {
			if (state == State.GROUP) {
				switch (chars[i]) {
				case '{':
					// Beginning of new group
					Group newGroup = new Group(currentGroup);
					currentGroup.children.add(newGroup);
					currentGroup = newGroup;
					break;
				case '}':
					// End of group
					currentGroup = currentGroup.parent;
					break;
				case '<':
					// Beginning of garbage
					state = State.GARBAGE;
					break;
				default:
				}
			} else if (state == State.GARBAGE) {
				switch (chars[i]) {
				case '>':
					// End of garbage
					state = State.GROUP;
					break;
				case '!':
					// Skip next char
					i++;
					break;
				default:
					totalGarbage++;
				}
			}
		}
	}
	
	public int getTotalScore() {
		return topGroup.getTotalScore();
	}
	
	public int getTotalGarbage() {
		return totalGarbage;
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
