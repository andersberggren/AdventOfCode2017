import re

from aoclib.filereader import get_file_as_list_of_string
from dec25.machine import TuringMachine

def get_turing_machine_from_file(filename):
	"""
	Returns (TuringMachine, number_of_steps).
	"""
	direction_string_to_int = {"left": -1, "right": 1}
	initial_state = None
	number_of_steps = None
	rules = {}
	lines = get_file_as_list_of_string(filename)
	while lines:
		line = lines.pop(0)
		match_initial_state = re.match("Begin in state (.)", line)
		match_number_of_steps = re.match("Perform a diagnostic checksum after (\d+) step", line)
		match_state_rule = re.match("In state (.):", line)
		if match_initial_state:
			initial_state = match_initial_state.group(1)
		elif match_number_of_steps:
			number_of_steps = int(match_number_of_steps.group(1))
		elif match_state_rule:
			current_state = match_state_rule.group(1)
			while lines:
				line = lines.pop(0)
				if line.strip() == "":
					break
				match_current_value = re.match(" *If the current value is ([01]):", line)
				current_value = int(match_current_value.group(1))
				line = lines.pop(0)
				match_value_to_write = re.match(" *- Write the value ([01])", line)
				value_to_write = int(match_value_to_write.group(1))
				line = lines.pop(0)
				match_move = re.match(" *- Move one slot to the (.*)\.", line)
				move = direction_string_to_int[match_move.group(1)]
				line = lines.pop(0)
				match_new_state = re.match(" *- Continue with state (.)", line)
				new_state = match_new_state.group(1)
				rules[(current_state, current_value)] = (value_to_write, move, new_state)
	return (TuringMachine(rules, initial_state), number_of_steps)
