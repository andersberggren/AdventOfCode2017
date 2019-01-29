from dec25.machine import TuringMachine

########
# Main #
########
if __name__ == "__main__":
	tm = TuringMachine("A")
	left = -1
	right = 1
	tm.add_rule("A", 0, 1, right, "B")
	tm.add_rule("A", 1, 0, left,  "C")
	tm.add_rule("B", 0, 1, left,  "A")
	tm.add_rule("B", 1, 1, right, "D")
	tm.add_rule("C", 0, 0, left,  "B")
	tm.add_rule("C", 1, 0, left,  "E")
	tm.add_rule("D", 0, 1, right, "A")
	tm.add_rule("D", 1, 0, right, "B")
	tm.add_rule("E", 0, 1, left,  "F")
	tm.add_rule("E", 1, 1, left,  "C")
	tm.add_rule("F", 0, 1, right, "D")
	tm.add_rule("F", 1, 1, right, "A")
	for i in range(12481997):
		tm.tick()
	print("Diagnostic checksum:", len([x for x in tm.tape.values() if x == 1]))
